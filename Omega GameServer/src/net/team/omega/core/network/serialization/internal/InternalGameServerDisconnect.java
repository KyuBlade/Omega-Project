package net.team.omega.core.network.serialization.internal;

import net.team.omega.core.network.serialization.MessageData;


public class InternalGameServerDisconnect extends MessageData
{

    private int id;
    
    public void setAccount(int id)
    {
	this.id = id;
    }
    
}
