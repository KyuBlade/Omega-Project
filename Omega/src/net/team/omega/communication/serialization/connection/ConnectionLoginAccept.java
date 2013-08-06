package net.team.omega.communication.serialization.connection;

import net.team.omega.communication.core.LoginServerFactory;
import net.team.omega.communication.serialization.MessageData;
import net.team.omega.communication.serialization.connection.gameserver.ConnectionGameServerInit;


public class ConnectionLoginAccept extends MessageData
{

    public ConnectionLoginAccept()
    {
	
    }
    
    @Override
    public void process()
    {
        LoginServerFactory.getInstance().send(new ConnectionGameServerInit());
    }
    
}
