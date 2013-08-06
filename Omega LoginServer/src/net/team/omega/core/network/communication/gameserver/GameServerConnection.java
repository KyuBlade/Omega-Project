package net.team.omega.core.network.communication.gameserver;

import net.team.omega.core.network.communication.serialization.NetworkData;
import net.team.omega.utils.LogHandler;

import com.esotericsoftware.kryonet.Connection;


public class GameServerConnection extends Connection
{

    private EncapsulateGameServerData gameserverDatas;
    
    public GameServerConnection()
    {
	super();
	
	// Create new storage space for gameserver
	gameserverDatas = new EncapsulateGameServerData();
    }
    
    @Override
    public int sendTCP(Object object)
    {
	if(object instanceof NetworkData)
	    LogHandler.system("[Internal]Send(TCP) >> " + object.toString());
	
	return super.sendTCP(object);
    }
    
    @Override
    public int sendUDP(Object object)
    {
	if(object instanceof NetworkData)
	    LogHandler.system("[Internal]Send(UDP) >> " + object.toString());
	
	return super.sendUDP(object);
    }
    
    public EncapsulateGameServerData getGameserverDatas()
    {
        return gameserverDatas;
    }
    
}
