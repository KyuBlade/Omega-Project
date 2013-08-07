package net.team.omega.core.network.serialization.connection.gameserver;

import net.team.omega.core.network.serialization.MessageData;

public class ConnectionGameServerConnect extends MessageData
{
    
    private String key;
    
    public ConnectionGameServerConnect(String key)
    {
	this.key = key;
    }
    
    public void setKey(String key)
    {
        this.key = key;
    }
    
}
