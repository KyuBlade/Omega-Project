package net.team.omega.core.network.communication.serialization.gameserver.connection;

import net.team.omega.core.network.communication.gameserver.ClientConnection;
import net.team.omega.core.network.communication.queue.WaitingConnection;
import net.team.omega.core.network.communication.queue.WaitingConnectionQueue;
import net.team.omega.core.network.communication.serialization.MessageData;
import net.team.omega.logging.LogHandler;


public class ConnectionGameServerConnect extends MessageData
{

    private String key;
    
    @Override
    public void process(ClientConnection connection)
    {
	WaitingConnection _wCon = WaitingConnectionQueue.getInstance().getInQueue(key);
	
	if(_wCon != null) // Can connect
	{
	    connection.getClientData().setAccount(_wCon.getAccount());
	    WaitingConnectionQueue.getInstance().removeFromQueue(_wCon);
	    
	    ConnectionGameServerAccept _gMessage = new ConnectionGameServerAccept();
	    connection.sendTCP(_gMessage);
	}
	else // Not normal
	{
	    LogHandler.warning("Someone with key " + key + " try to connect to gameserver but was not in waiting connection queue !");
	    connection.close();
	}
    }
    
}
