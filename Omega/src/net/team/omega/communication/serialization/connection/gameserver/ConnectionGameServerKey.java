package net.team.omega.communication.serialization.connection.gameserver;

import net.team.omega.communication.core.GameServerFactory;
import net.team.omega.communication.serialization.MessageData;


public class ConnectionGameServerKey extends MessageData
{

    private String key;
    
    public String getKey()
    {
	return key;
    }
    
    @Override
    public void process()
    {
	GameServerFactory.getInstance().sendTCP(new ConnectionGameServerConnect(key));
    }
    
}
