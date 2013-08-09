package net.team.omega.core.network.gameserver;

import java.io.IOException;

import net.team.omega.Constants;
import net.team.omega.core.database.table.GameServer;
import net.team.omega.core.network.ClassRegister;
import net.team.omega.core.network.LoadingFactory;
import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.NetworkData;
import net.team.omega.utils.BasicUtils;
import net.team.omega.utils.LogHandler;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;


public class InternalLoginServerFactory
{

    private BaseGameServer server;

    private static InternalLoginServerFactory instance;

    public InternalLoginServerFactory()
    {
	try
	{
	    // Initialize server
	    server = new BaseGameServer();

	    // Register all serializable classes
	    ClassRegister.gameserverRegister(server.getKryo());

	    // Register event listener
	    server.addListener(new Listener() {

		@Override
		public void connected(Connection connection)
		{
		    // TODO: Mask
		    // http://docs.oracle.com/cd/E19575-01/821-0053/adyrv/index.html
		    for(GameServer gameserver : LoadingFactory.getInstance().getGameServers())
		    {
			if(gameserver.getIp().equals(connection.getRemoteAddressTCP().getAddress().getHostAddress()))
			{
			    gameserver.setTcpPort(((GameServerConnection) connection).getRemoteAddressTCP().getPort());
			    gameserver.setUdpPort(((GameServerConnection) connection).getRemoteAddressUDP().getPort());
			    ((GameServerConnection) connection).getGameserverDatas().setGameServer(gameserver);
			    
			    break;
			}
		    }
		    
		    if(((GameServerConnection) connection).getGameserverDatas().getGameServer() == null)
		    {
			LogHandler.warning(connection.getRemoteAddressTCP().getAddress().getHostAddress() + " try to connect to GameServer registration server but was not one of them !");
			connection.close();
			
			return;
		    }
		    
		    LogHandler.info("[Internal]Connection of GameServer " + ((GameServerConnection) connection).getGameserverDatas().getGameServer().getName());
		}

		@Override
		public void disconnected(Connection connection)
		{
		    GameServerConnection _connection = ((GameServerConnection) connection);
		    
		    if(_connection.getGameserverDatas().getGameServer() == null) // GameServer failed to login
		    {
			LogHandler.info("[Internal]GameServer " + connection + " was disconnected");
			
			return;
		    }
		    
		    _connection.getGameserverDatas().getGameServer().setState(GameServerState.OFFLINE);
		    
		    LogHandler.info("[Internal]GameServer " + ((GameServerConnection) connection).getGameserverDatas().getGameServer().getName() + " was disconnected");
		}

		@Override
		public void received(Connection connection, Object object)
		{
		    if (object instanceof NetworkData)
		    {
			LogHandler.system("[Internal]Recv(" + ((GameServerConnection) connection).getGameserverDatas().getGameServer().getName() + ") << " + object.toString());
			if (object instanceof MessageData)
			    ((MessageData) object).process((GameServerConnection) connection);
		    }
		}

	    });

	    server.start();
	    server.bind(Constants.INTERNAL_SERVER_TCP_PORT, Constants.INTERNAL_SERVER_UDP_PORT);
	    
	    LogHandler.info("InternalLoginServer is started");
	} catch (IOException e)
	{
	    LogHandler.severe("Unable to start InternalServer !");
	    LogHandler.severe("Cause : " + e.getMessage());

	    BasicUtils.onErrorExit();
	}
    }

    public static InternalLoginServerFactory getInstance()
    {
	if (instance == null)
	    instance = new InternalLoginServerFactory();

	return instance;
    }
    
    public BaseGameServer getServer()
    {
	return server;
    }
    
}
