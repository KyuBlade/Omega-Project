package net.team.omega.core.network.communication.loginserver;

import java.io.IOException;

import net.team.omega.Constants;
import net.team.omega.core.network.communication.ClassRegister;
import net.team.omega.core.network.communication.serialization.MessageData;
import net.team.omega.core.network.communication.serialization.NetworkData;
import net.team.omega.core.network.communication.serialization.connection.gameserver.ConnectionGameServerList;
import net.team.omega.utils.BasicUtils;
import net.team.omega.utils.LogHandler;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class LoginServerFactory
{

    private BaseLoginServer server;

    private static LoginServerFactory instance;

    public LoginServerFactory()
    {
	try
	{
	    // Initialize server
	    server = new BaseLoginServer();

	    // Register all serializable classes
	    ClassRegister.loginRegister(server.getKryo());

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
		    LogHandler.info((ClientConnection) connection + " was disconnected");
		}

		@Override
		public void received(Connection connection, Object object)
		{
		    if (object instanceof NetworkData)
		    {
			LogHandler.system("Recv << " + object.toString());
			if (object instanceof MessageData)
			    ((MessageData) object).process((ClientConnection) connection);
		    }
		}

	    });

	    server.start();
	    server.bind(Constants.SERVER_TCP_PORT, Constants.SERVER_UDP_PORT);
	    
	    LogHandler.info("LoginServer is started");
	} catch (IOException e)
	{
	    LogHandler.severe("Unable to start server !");
	    LogHandler.severe("Cause : " + e.getMessage());

	    BasicUtils.onErrorExit();
	}
    }

    public static LoginServerFactory getInstance()
    {
	if (instance == null)
	    instance = new LoginServerFactory();

	return instance;
    }
    
    public BaseLoginServer getServer()
    {
	return server;
    }
    
    public void updateGameserverState()
    {
	Connection[] _connections = server.getConnections();
	
	for(Connection _con :  _connections)
	    _con.sendTCP(new ConnectionGameServerList());
    }

}
