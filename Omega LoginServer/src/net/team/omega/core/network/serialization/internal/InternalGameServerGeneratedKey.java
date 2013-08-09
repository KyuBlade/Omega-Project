package net.team.omega.core.network.serialization.internal;

import net.team.omega.core.network.gameserver.GameServerConnection;
import net.team.omega.core.network.loginserver.ClientConnection;
import net.team.omega.core.network.loginserver.LoginServerFactory;
import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.connection.gameserver.ConnectionGameServerKey;

import com.esotericsoftware.kryonet.Connection;


public class InternalGameServerGeneratedKey extends MessageData
{

    private WaitingConnection waitingCon;
    
    @Override
    public void process(GameServerConnection connection)
    {
	ConnectionGameServerKey message = new ConnectionGameServerKey();
	message.setKey(waitingCon.getKey());
	
	for(Connection _con : LoginServerFactory.getInstance().getServer().getConnections())
	{
	    ClientConnection _cCon = (ClientConnection) _con;
	    if(_cCon.getClientDatas().getAccount().getId() == waitingCon.getAccount().getId())
	    {
		_cCon.sendTCP(message);
		
		break;
	    }
	}
    }
    
}
