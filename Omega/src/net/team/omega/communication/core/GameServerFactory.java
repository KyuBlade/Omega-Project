package net.team.omega.communication.core;

import java.io.IOException;

import net.team.omega.communication.serialization.MessageData;
import net.team.omega.communication.serialization.connection.gameserver.ConnectionGameServerSelect;
import net.team.omega.communication.serialization.datas.GameServer;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.team.omega.core.Constants;
import com.team.omega.utils.LogHandler;

public class GameServerFactory
{

    private Client client;
    
    private static GameServerFactory instance;

    public GameServerFactory()
    {
        
    }
    
    public static GameServerFactory getInstance()
    {
        if (instance == null)
            instance = new GameServerFactory();

        return instance;
    }

    public void start(final GameServer gameserver)
    {
        try
        {
            client = new Client();

            ClassRegister.gameRegister(client.getKryo());

            client.addListener(new Listener() {
                
                @Override
                public void connected(Connection connection)
                {
                    LogHandler.info("Connected to gameserver");
                    
                    // Fake mode here
                    
                    ConnectionGameServerSelect message = new ConnectionGameServerSelect(gameserver);
                    LoginServerFactory.getInstance().send(message);
                }

                @Override
                public void disconnected(Connection connection)
                {
                    LogHandler.info("Disconnected from gameserver.");
                    
                }

                @Override
                public void received(Connection connection, Object object)
                {
                    if (object instanceof MessageData)
                    {
                	MessageData _message = (MessageData) object;
                        if(_message.onDebugDisplay())
                            LogHandler.system("Recv << " + object.toString());
                        
                        _message.process();;
                    }
                }
                
            });

            client.start();
            client.connect(Constants.GAME_SERVER_TIMEOUT, gameserver.getIp(), gameserver.getTcpPort(), gameserver.getUdpPort());
        } catch (IOException ex)
        {
            LogHandler.severe("Cannot connect to the gameserver (" + gameserver.getName() + ") ! ");
            LogHandler.severe("Cause : " + ex.getMessage());
        }
    }

    public void sendTCP(Object object)
    {
        int byteCount = client.sendTCP(object);
        
        if(object instanceof MessageData)
        {
            if(((MessageData) object).onDebugDisplay())
                LogHandler.system("Send(TCP) [" + byteCount + "] >> " + object.getClass().getSimpleName());
        }
    }
    
    public void sendUDP(Object object)
    {
        int byteCount = client.sendUDP(object);
        
        if(object instanceof MessageData)
        {
            if(((MessageData) object).onDebugDisplay())
                LogHandler.system("Send(UDP) [" + byteCount + "] >> " + object.getClass().getSimpleName());
        }
    }
    
    public Client getClient()
    {
        return client;
    }

    public void disconnect()
    {
        if(client != null && client.isConnected())
            client.close();
    }
}
