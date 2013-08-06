package net.team.omega.core.network.serialization.internal;

import net.team.omega.core.network.gameserver.GameServerConnection;
import net.team.omega.core.network.loginserver.ClientConnection;
import net.team.omega.core.network.loginserver.LoginServerFactory;
import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.connection.gameserver.ConnectionGameServerKey;


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
