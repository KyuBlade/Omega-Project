package net.team.omega.core.network.communication.serialization.connection.gameserver;

import net.team.omega.core.network.communication.loginserver.ClientConnection;
import net.team.omega.core.network.communication.serialization.MessageData;


public class ConnectionGameServerInit extends MessageData
{

    @Override
    public void process(ClientConnection connection)
    {
	ConnectionGameServerList message = new ConnectionGameServerList();
	connection.sendTCP(message);
    }
    
}
