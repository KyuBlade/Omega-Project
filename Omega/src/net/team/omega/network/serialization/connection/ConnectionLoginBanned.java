package net.team.omega.network.serialization.connection;

import net.team.omega.network.core.LoginServerFactory;
import net.team.omega.network.serialization.MessageData;


public class ConnectionLoginBanned extends MessageData
{
    
    @Override
    public void process()
    {
        // TODO: Display popup
        LoginServerFactory.getInstance().disconnect();
    }
        
}
