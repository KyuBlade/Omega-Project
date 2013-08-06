package net.team.omega.network.serialization.connection;

import net.team.omega.network.core.LoginServerFactory;
import net.team.omega.network.serialization.MessageData;
import net.team.omega.network.serialization.connection.gameserver.ConnectionGameServerInit;


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
