package net.team.omega.core.network.serialization.internal;

import net.team.omega.core.network.gameserver.GameServerConnection;
import net.team.omega.core.network.serialization.MessageData;


public class InternalGameServerUpdate extends MessageData
{

    private byte state;
    
    @Override
    public void process(GameServerConnection connection)
    {
	connection.getGameserverDatas().getGameServer().setState(state);
    }
    
}
