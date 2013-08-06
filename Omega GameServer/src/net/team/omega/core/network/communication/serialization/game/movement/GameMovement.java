package net.team.omega.core.network.communication.serialization.game.movement;

import net.team.omega.core.network.communication.gameserver.ClientConnection;
import net.team.omega.core.network.communication.serialization.MessageData;


public class GameMovement extends MessageData
{

    //protected EntityId entityId;
    
    public GameMovement()
    {
	
    }
    
    @Override
    public boolean onDebugDisplay()
    {
	return false;
    }
    
    @Override
    public void process(ClientConnection connection)
    {
	
    }
    
    /*public EntityId getEntityId()
    {
	return entityId;
    }*/
    
}
