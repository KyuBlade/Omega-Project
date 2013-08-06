package net.team.omega.network.serialization.connection.gameserver;

import net.team.omega.network.serialization.MessageData;

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
