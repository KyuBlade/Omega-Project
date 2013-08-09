package net.team.omega.core.network.serialization.internal;

import net.team.omega.core.network.queue.WaitingConnection;
import net.team.omega.core.network.serialization.MessageData;


public class InternalGameServerGeneratedKey extends MessageData
{

    private WaitingConnection waitingCon;
    
    public InternalGameServerGeneratedKey()
    {
	
    }
    
    public InternalGameServerGeneratedKey(WaitingConnection waitCon)
    {
	this.waitingCon = waitCon;
    }
    
}
