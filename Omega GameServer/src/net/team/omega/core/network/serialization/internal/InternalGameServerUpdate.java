package net.team.omega.core.network.serialization.internal;

import net.team.omega.core.network.GameServerState;
import net.team.omega.core.network.serialization.MessageData;


public class InternalGameServerUpdate extends MessageData
{

    private GameServerState state;
    
    public InternalGameServerUpdate()
    {
	state = GameServerState.OFFLINE;
    }
    
    public InternalGameServerUpdate(GameServerState state)
    {
	this.state = state;
    }
    
}
