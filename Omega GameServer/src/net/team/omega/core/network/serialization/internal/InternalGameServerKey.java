package net.team.omega.core.network.serialization.internal;

import net.team.omega.core.network.serialization.MessageData;


public class InternalGameServerKey extends MessageData
{

    private String key;
    
    public void setKey(String key)
    {
	this.key = key;
    }
    
}
