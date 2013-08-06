package net.team.omega.network.core;

import java.io.IOException;

import net.team.omega.network.serialization.MessageData;
import net.team.omega.network.serialization.connection.ConnectionLogin;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.team.omega.core.Constants;
import com.team.omega.core.GameCore;
import com.team.omega.core.screen.IdentificationScreen;
import com.team.omega.utils.LogHandler;

public class LoginServerFactory
{

    private Client client;
    private ConnectionState state = ConnectionState.IDLE;

    private static LoginServerFactory instance;
    
    private enum ConnectionState
    {
	CONNECTING, CONNECTED, IDLE
    }

    public LoginServerFactory()
    {
	client = new Client();

	ClassRegister.loginRegister(client.getKryo());

	client.addListener(new Listener() {

	    @Override
	    public void connected(Connection connection)
	    {
		state = ConnectionState.CONNECTED;
		LogHandler.info("Connected to loginserver.");
	    }

	    @Override
	    public void disconnected(Connection connection)
	    {
		LogHandler.info("Disconnected from loginserver.");
	    }

	    @Override
	    public void received(Connection connection, Object object)
	    {
		if (object instanceof MessageData)
		{
		    LogHandler.system("Recv << " + object.toString());
		    ((MessageData) object).process();
		}
	    }

	});
    }
    
    public static LoginServerFactory getInstance()
    {
        if(instance == null)
            instance = new LoginServerFactory();
        
        return instance;
    }
    
    public void connect(String user, String pass)
    {
	if(!state.equals(ConnectionState.IDLE))
	    return;
	
	state = ConnectionState.CONNECTING;
	
	try
	{
	    client.start();
	    client.connect(Constants.LOGIN_SERVER_TIMEOUT, Constants.LOGIN_SERVER_HOST, Constants.LOGIN_SERVER_TCP_PORT, Constants.LOGIN_SERVER_UDP_PORT);

	    ConnectionLogin message = new ConnectionLogin(user, pass);
	    send(message);
	} catch (IOException ex)
	{
	    state = ConnectionState.IDLE;
	    GameCore.getInstance().getScreenManager().getScreen(IdentificationScreen.class).showPopup("Unable to connect to server");
	    LogHandler.severe("Cannot connect to the loginserver ! ");
	    LogHandler.severe("Cause : " + ex.getMessage());

	}
    }
    
    public void send(Object object)
    {
        if(client == null || !client.isConnected())
            return;
        
        LogHandler.system("Send >> " + object.getClass().getSimpleName());
        client.sendTCP(object);
    }
    
    public Client getClient()
    {
        return client;
    }
    
    public void disconnect()
    {
        client.close();
    }
    
}
