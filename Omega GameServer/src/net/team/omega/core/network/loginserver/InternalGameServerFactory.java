package net.team.omega.core.network.loginserver;

import java.io.IOException;

import net.team.omega.Constants;
import net.team.omega.core.network.ClassRegister;
import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.NetworkData;
import net.team.omega.core.network.serialization.internal.InternalGameServerDatas;
import net.team.omega.core.network.serialization.internal.InternalGameServerUpdate;
import net.team.omega.logging.LogHandler;
import net.team.omega.utils.BasicUtils;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class InternalGameServerFactory
{

    private Client client;

    private static InternalGameServerFactory instance;

    public InternalGameServerFactory()
    {

    }

    public void start()
    {
	// Initialize server
	client = new Client() {

	    @Override
	    public int sendTCP(Object object)
	    {
		if (object instanceof NetworkData)
		    LogHandler.system("[Internal]Send(TCP) >> " + object.toString());

		return super.sendTCP(object);
	    }

	    @Override
	    public int sendUDP(Object object)
	    {
		if (object instanceof NetworkData)
		    LogHandler.system("[Internal]Send(UDP) >> " + object.toString());

		return super.sendUDP(object);
	    }

	};

	// Register all serializable classes
	ClassRegister.internalRegister(client.getKryo());

	// Register event listener
	client.addListener(new Listener() {

	    @Override
	    public void connected(Connection connection)
	    {
		LogHandler.info("[Internal]Connected to LoginServer (" + connection.getRemoteAddressTCP().getHostName() + ")");

		// Update GameServer data on LoginServer
		InternalGameServerDatas _dataMessage = new InternalGameServerDatas();

		client.sendTCP(_dataMessage);

		// Update GameServer state on LoginServer
		InternalGameServerUpdate _updateMessage = new InternalGameServerUpdate(Constants.GAME_SERVER_STATE_ONLINE);
		client.sendTCP(_updateMessage);
	    }

	    @Override
	    public void disconnected(Connection connection)
	    {
		LogHandler.info("[Internal]Disconnected from LoginServer");

		BasicUtils.onErrorExit();
	    }

	    @Override
	    public void received(Connection connection, Object object)
	    {
		if (object instanceof NetworkData)
		{
		    LogHandler.system("[Internal]Recv << " + object.toString());
		    if (object instanceof MessageData)
			((MessageData) object).process();
		}
	    }

	});

	client.start();
	connect();
    }

    private void connect()
    {
	try
	{
	    if (client.getUpdateThread() == null || client.getUpdateThread().isInterrupted())
		client.start();

	    client.connect(Constants.LOGIN_SERVER_TIMEOUT, Constants.LOGIN_SERVER_HOST, Constants.LOGIN_SERVER_TCP_PORT, Constants.LOGIN_SERVER_UDP_PORT);
	} catch (IllegalStateException | IOException e)
	{
	    LogHandler.warning("[Internal]Unable to reach LoginServer, retry in " + Constants.LOGIN_SERVER_RETRY / 1000 + " seconds !");
	    try
	    {
		Thread.sleep(Constants.LOGIN_SERVER_RETRY);
	    } catch (InterruptedException e1)
	    {
		LogHandler.severe("[Internal]Unable to wait until next try !");
		LogHandler.severe("Cause : " + e1.getMessage());
	    }
	    connect();
	}
    }

    public static InternalGameServerFactory getInstance()
    {
	if (instance == null)
	    instance = new InternalGameServerFactory();

	return instance;
    }

    public Client getClient()
    {
	return client;
    }

}
