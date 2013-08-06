package net.team.omega.core.network.communication.serialization.internal;

import net.team.omega.core.network.communication.gameserver.GameServerConnection;
import net.team.omega.core.network.communication.serialization.MessageData;


public class InternalGameServerUpdate extends MessageData
{

    private byte state;
    
    @Override
    public void process(GameServerConnection connection)
    {
	connection.getGameserverDatas().getGameServer().setState(state);
    }
    
}
