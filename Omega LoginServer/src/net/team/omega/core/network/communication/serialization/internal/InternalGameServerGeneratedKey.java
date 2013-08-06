package net.team.omega.core.network.communication.serialization.internal;

import net.team.omega.core.network.communication.gameserver.GameServerConnection;
import net.team.omega.core.network.communication.loginserver.ClientConnection;
import net.team.omega.core.network.communication.loginserver.LoginServerFactory;
import net.team.omega.core.network.communication.serialization.MessageData;
import net.team.omega.core.network.communication.serialization.connection.gameserver.ConnectionGameServerKey;


public class InternalGameServerGeneratedKey extends MessageData
{

    private WaitingConnection waitingCon;
    
    @Override
    public void process(GameServerConnection connection)
    {
	ConnectionGameServerKey message = new ConnectionGameServerKey();
	message.setKey(waitingCon.getKey());
	
	for(ClientConnection _con : LoginServerFactory.getInstance().getServer().getClientConnections())
	{
	    if(_con.getClientDatas().getAccount().getId() == waitingCon.getAccount().getId())
	    {
		_con.sendTCP(message);
		
		break;
	    }
	}
    }
    
}
