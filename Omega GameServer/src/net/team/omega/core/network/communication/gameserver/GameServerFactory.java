package net.team.omega.core.network.communication.gameserver;

import java.io.IOException;

import net.team.omega.Constants;
import net.team.omega.core.ConnectionState;
import net.team.omega.core.network.communication.ClassRegister;
import net.team.omega.core.network.communication.serialization.MessageData;
import net.team.omega.core.network.communication.serialization.NetworkData;
import net.team.omega.logging.LogHandler;
import net.team.omega.utils.BasicUtils;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class GameServerFactory
{

    private Server server;

    private static GameServerFactory instance = null;

    public GameServerFactory()
    {

    }

    public static GameServerFactory getInstance()
    {
	if (instance == null)
	    instance = new GameServerFactory();

	return instance;
    }

    public void start()
    {
	try
	{
	    // Initialize server
	    server = new Server() {

		@Override
		protected ClientConnection newConnection()
		{
		    return (ClientConnection) new ClientConnection();
		}
		
	    };

	    // Register all serializable classes
	    ClassRegister.clientRegister(server.getKryo());

	    // Register event listener
	    server.addListener(new Listener() {

		@Override
		public void connected(Connection connection)
		{
		    LogHandler.info("New connection from " + connection.getRemoteAddressTCP().getAddress().getHostAddress());
		}

		@Override
		public void disconnected(Connection connection)
		{
		    ClientConnection _connection = (ClientConnection) connection;
		    
		    LogHandler.info("Connection " + connection.getID() + " was disconected");
		}

		@Override
		public void received(Connection connection, Object object)
		{
		    if (object instanceof MessageData)
		    {
			MessageData _m = (MessageData) object;
			if (_m.onDebugDisplay())
			    LogHandler.system("Recv << " + object.toString());

			_m.process((ClientConnection) connection);
		    }
		}

	    });

	    server.start();
	    server.bind(Constants.SERVER_TCP_PORT, Constants.SERVER_UDP_PORT);
	} catch (IOException e)
	{
	    LogHandler.severe("Unable to start server !");
	    LogHandler.severe("Cause : " + e.getMessage());

	    BasicUtils.onErrorExit();
	}
    }
    
    public Server getServer()
    {
        return server;
    }
    
    public void sendToAllInGameExceptUDP(int connectionId, NetworkData data)
    {
	for(Connection con : server.getConnections())
	{
	    ClientConnection _cCon = (ClientConnection) con;
	    
	    if((_cCon.getClientData().getState() == ConnectionState.CONNECTED) && (_cCon.getID() != connectionId))
	    {
		_cCon.sendUDP(data);
	    }
	}
    }
    
    public void sendToAllInGameExceptTCP(int connectionId, NetworkData data)
    {
	for(Connection con : server.getConnections())
	{
	    ClientConnection _cCon = (ClientConnection) con;
	    
	    if((_cCon.getClientData().getState() == ConnectionState.CONNECTED) && (_cCon.getID() != connectionId))
	    {
		_cCon.sendTCP(data);
	    }
	}
    }

}
