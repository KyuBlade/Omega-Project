package net.team.omega.core.network.communication.gameserver;

import net.team.omega.core.ConnectionState;
import net.team.omega.core.network.communication.serialization.MessageData;
import net.team.omega.core.network.communication.serialization.NetworkData;
import net.team.omega.logging.LogHandler;

import com.esotericsoftware.kryonet.Connection;


public class ClientConnection extends Connection
{
    
    private EncapsulateClientData clientData;
    
    public ClientConnection()
    {
	super();
	
	clientData = new EncapsulateClientData();
	clientData.setState(ConnectionState.CONNECTING);
    }
    
    @Override
    public int sendTCP(Object object)
    {
	boolean _display = false;
	
	if(object instanceof NetworkData)
	{
	    if(object instanceof MessageData)
		((MessageData)object).preSend(this);
	    
	    _display = ((NetworkData) object).onDebugDisplay();
	}
	
	int byteCount = super.sendTCP(object);
	
	if(_display)
	    LogHandler.system("Send(TCP) [" + byteCount + "] >> " + object.getClass().getSimpleName());
	
	return byteCount;
    }
    
    @Override
    public int sendUDP(Object object)
    {
	boolean _display = false;
	
	if(object instanceof NetworkData)
	{
	    if(object instanceof MessageData)
		((MessageData)object).preSend(this);
	    
	    _display = ((NetworkData) object).onDebugDisplay();
	}
	
	int byteCount = super.sendUDP(object);
	
	if(_display)
	    LogHandler.system("Send(UDP) [" + byteCount + "] >> " + object.getClass().getSimpleName());
	
	return byteCount;
    }

    public EncapsulateClientData getClientData()
    {
        return clientData;
    }
    
}
