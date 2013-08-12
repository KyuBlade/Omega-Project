package net.team.omega.core.network;

import java.io.IOException;

import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.connection.ConnectionLogin;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.team.omega.core.Constants;
import com.team.omega.core.GameCore;
import com.team.omega.core.LocalizationHandler;
import com.team.omega.core.screen.IdentificationScreen;

public class LoginServerFactory
{

    private Client client;
    private ConnectionState state = ConnectionState.IDLE;

    private static LoginServerFactory instance;

    public LoginServerFactory()
    {
	client = new Client();

	ClassRegister.loginRegister(client.getKryo());

	client.addListener(new Listener() {

	    @Override
	    public void connected(Connection connection)
	    {
		state = ConnectionState.CONNECTED;
		Gdx.app.debug("LoginServerFactory", "Connected to loginserver.");
	    }

	    @Override
	    public void disconnected(Connection connection)
	    {
		state = ConnectionState.IDLE;
		Gdx.app.debug("LoginServerFactory", "Disconnected from loginserver.");
	    }

	    @Override
	    public void received(Connection connection, Object object)
	    {
		if (object instanceof MessageData)
		{
		    Gdx.app.debug("LoginServerFactory", "Recv << " + object.toString());
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
	    GameCore.getInstance().getScreenManager().getScreen(IdentificationScreen.class).showPopup("", LocalizationHandler.getInstance().getDialog("login.error.unreachable"));
	    Gdx.app.error("LoginServerFactory", "Cannot connect to the loginserver !");
	}
    }
    
    public void send(Object object)
    {
        if(client == null || !client.isConnected())
            return;
        
        Gdx.app.debug("LoginServerFactory", "Send >> " + object.getClass().getSimpleName());
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
