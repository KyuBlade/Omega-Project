package net.team.omega.core.network.serialization.connection;

import net.team.omega.core.network.LoginServerFactory;
import net.team.omega.core.network.serialization.MessageData;


public class ConnectionLoginRefuse extends MessageData
{

    @Override
    public void process()
    {
        // TODO: Display popup
        LoginServerFactory.getInstance().disconnect();
    }
    
}
