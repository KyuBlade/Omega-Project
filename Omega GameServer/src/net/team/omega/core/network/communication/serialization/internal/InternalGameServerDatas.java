package net.team.omega.core.network.communication.serialization.internal;

import net.team.omega.Constants;
import net.team.omega.core.network.communication.serialization.MessageData;


public class InternalGameServerDatas extends MessageData
{

    private int tcpPort;
    private int udpPort;
    
    public InternalGameServerDatas()
    {
	tcpPort = Constants.SERVER_TCP_PORT;
	udpPort = Constants.SERVER_UDP_PORT;
    }
    
}
