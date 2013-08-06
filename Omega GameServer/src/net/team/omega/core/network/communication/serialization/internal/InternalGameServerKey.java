package net.team.omega.core.network.communication.serialization.internal;

import net.team.omega.core.network.communication.serialization.MessageData;


public class InternalGameServerKey extends MessageData
{

    private String key;
    
    public void setKey(String key)
    {
	this.key = key;
    }
    
}
