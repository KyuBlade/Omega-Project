package net.team.omega.core.network;

import java.io.IOException;

import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.connection.gameserver.ConnectionGameServerConnect;
import net.team.omega.core.network.serialization.datas.GameServer;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.team.omega.core.Constants;

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

    public void start(final GameServer gameserver, final String key)
    {
        try
        {
            client = new Client();

            ClassRegister.gameRegister(client.getKryo());

            client.addListener(new Listener() {
                
                @Override
                public void connected(Connection connection)
                {
                    Gdx.app.debug("GameServerFactory", "Connected to gameserver");
                    
                    // Fake mode here
                    
                    client.sendTCP(new ConnectionGameServerConnect(key));
                }

                @Override
                public void disconnected(Connection connection)
                {
                    Gdx.app.debug("GameServerFactory", "Disconnected from gameserver");
                    
                }

                @Override
                public void received(Connection connection, Object object)
                {
                    if (object instanceof MessageData)
                    {
                	MessageData _message = (MessageData) object;
                        if(_message.onDebugDisplay())
                            Gdx.app.debug("GameServerFactory", "Recv << " + object.toString());
                        
                        _message.process();;
                    }
                }
                
            });

            client.start();
            client.connect(Constants.GAME_SERVER_TIMEOUT, gameserver.getIp(), gameserver.getTcpPort(), gameserver.getUdpPort());
        } catch (IOException ex)
        {
            Gdx.app.error("GameServerFactory", "Cannot connect to the gameserver (" + gameserver.getName() + ") ! ", ex);
        }
    }

    public void sendTCP(Object object)
    {
	if(object == null)
	{
	    Gdx.app.error("GameServerFactory", "Unable to send over tcp because object is null");
	    
	    return;
	}
	    
        int byteCount = client.sendTCP(object);
        
        if(object instanceof MessageData)
        {
            if(((MessageData) object).onDebugDisplay())
                Gdx.app.debug("GameServerFactory", "Send(TCP) [" + byteCount + "] >> " + object.getClass().getSimpleName());
        }
    }
    
    public void sendUDP(Object object)
    {
	if(object == null)
	{
	    Gdx.app.error("GameServerFactory", "Unable to send over udp because object is null");
	    
	    return;
	}
	
        int byteCount = client.sendUDP(object);
        
        if(object instanceof MessageData)
        {
            if(((MessageData) object).onDebugDisplay())
                Gdx.app.debug("GameServerFactory", "Send(UDP) [" + byteCount + "] >> " + object.getClass().getSimpleName());
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
