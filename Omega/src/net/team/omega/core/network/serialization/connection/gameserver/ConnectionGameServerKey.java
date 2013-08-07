package net.team.omega.core.network.serialization.connection.gameserver;

import net.team.omega.core.network.GameServerFactory;
import net.team.omega.core.network.serialization.MessageData;


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
