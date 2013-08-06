package net.team.omega.core.network.serialization.internal;

import net.team.omega.core.network.gameserver.GameServerConnection;
import net.team.omega.core.network.loginserver.ClientConnection;
import net.team.omega.core.network.loginserver.LoginServerFactory;
import net.team.omega.core.network.serialization.MessageData;

import com.esotericsoftware.kryonet.Connection;


public class InternalGameServerDisconnect extends MessageData
{

    private int id;
    
    @Override
    public void process(GameServerConnection connection)
    {
	for(Connection con : LoginServerFactory.getInstance().getServer().getConnections())
	{
	    ClientConnection _con = (ClientConnection) con;
	    
	    if(_con.getClientDatas().getAccount().getId() == id)
	    {
		_con.close();
		
		break;
	    }
	}
    }
    
}
