package net.team.omega.core.network.communication.serialization.game;

import net.team.omega.core.ConnectionState;
import net.team.omega.core.network.communication.gameserver.ClientConnection;
import net.team.omega.core.network.communication.serialization.MessageData;
import net.team.omega.core.network.communication.serialization.internal.InternalGameServerDisconnect;


public class StartGame extends MessageData
{

    public StartGame()
    {
	
    }
    
    @Override
    public void preSend(ClientConnection connection)
    {
	    // Disconnect from LoginServer
	    InternalGameServerDisconnect _message = new InternalGameServerDisconnect();
	    /*_message.setAccount(MasterEntityData.getInstance().getComponent(connection.getAccount(), AccountComponent.class).getAccountId());
		    
	    InternalGameServerFactory.getClient().sendTCP(_message);*/
	    
	    connection.getClientData().setState(ConnectionState.CONNECTED);
    }
    
}
