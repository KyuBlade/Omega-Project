package net.team.omega.core.network.serialization.connection;

import net.team.omega.core.network.LoginServerFactory;
import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.connection.gameserver.ConnectionGameServerInit;


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
