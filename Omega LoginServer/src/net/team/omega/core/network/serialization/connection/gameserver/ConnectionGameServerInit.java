package net.team.omega.core.network.serialization.connection.gameserver;

import net.team.omega.core.network.loginserver.ClientConnection;
import net.team.omega.core.network.serialization.MessageData;


public class ConnectionGameServerInit extends MessageData
{

    @Override
    public void process(ClientConnection connection)
    {
	ConnectionGameServerList message = new ConnectionGameServerList();
	connection.sendTCP(message);
    }
    
}
