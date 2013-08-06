package net.team.omega.core.network.communication.serialization.internal;

import net.team.omega.core.network.communication.serialization.MessageData;


public class InternalGameServerDisconnect extends MessageData
{

    private int id;
    
    public void setAccount(int id)
    {
	this.id = id;
    }
    
}
