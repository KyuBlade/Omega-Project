package net.team.omega.communication.serialization.connection;

import net.team.omega.communication.core.LoginServerFactory;
import net.team.omega.communication.serialization.MessageData;


public class ConnectionLoginRefuse extends MessageData
{

    @Override
    public void process()
    {
        // TODO: Display popup
        LoginServerFactory.getInstance().disconnect();
    }
    
}
