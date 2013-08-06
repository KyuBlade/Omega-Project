package net.team.omega.core.network.communication.serialization.connection.gameserver;

import net.team.omega.core.network.communication.serialization.MessageData;


public class ConnectionGameServerKey extends MessageData
{

    private String key;
    
    public void setKey(String key)
    {
	this.key = key;
    }
    
}
