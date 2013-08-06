package net.team.omega.core.network.loginserver;

import net.team.omega.core.network.serialization.NetworkData;
import net.team.omega.utils.LogHandler;

import com.esotericsoftware.kryonet.Connection;


public class ClientConnection extends Connection
{
    
    private EncapsulateClientData clientDatas;

    public ClientConnection()
    {
	super();
	
	// Create new storage space for client
	clientDatas = new EncapsulateClientData();
    }
    
    @Override
    public int sendTCP(Object object)
    {
	if(object instanceof NetworkData)
	    LogHandler.system("Send(TCP) >> " + object.toString());
	
	return super.sendTCP(object);
    }
    
    @Override
    public int sendUDP(Object object)
    {
	if(object instanceof NetworkData)
	    LogHandler.system("Send(UDP) >> " + object.toString());
	
	return super.sendUDP(object);
    }

    public EncapsulateClientData getClientDatas()
    {
        return clientDatas;
    }
    
}
