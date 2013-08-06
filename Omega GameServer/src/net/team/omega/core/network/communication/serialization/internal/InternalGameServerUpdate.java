package net.team.omega.core.network.communication.serialization.internal;

import net.team.omega.Constants;
import net.team.omega.core.network.communication.serialization.MessageData;


public class InternalGameServerUpdate extends MessageData
{

    private byte state;
    
    public InternalGameServerUpdate()
    {
	state = Constants.GAME_SERVER_STATE_OFFLINE;
    }
    
    public InternalGameServerUpdate(byte state)
    {
	this.state = state;
    }
    
    public void setState(byte state)
    {
	this.state = state;
    }
    
}
