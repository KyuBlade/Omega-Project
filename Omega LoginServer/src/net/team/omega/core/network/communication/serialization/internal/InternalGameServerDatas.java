package net.team.omega.core.network.communication.serialization.internal;

import net.team.omega.core.network.communication.gameserver.GameServerConnection;
import net.team.omega.core.network.communication.serialization.MessageData;


public class InternalGameServerDatas extends MessageData
{

    private int tcpPort;
    private int udpPort;
    
    @Override
    public void process(GameServerConnection connection)
    {
	connection.getGameserverDatas().getGameServer().setTcpPort(tcpPort);
	connection.getGameserverDatas().getGameServer().setUdpPort(udpPort);
    }

}
