package net.team.omega.network.serialization.connection.gameserver;

import net.team.omega.network.core.GameServerFactory;
import net.team.omega.network.serialization.MessageData;


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
