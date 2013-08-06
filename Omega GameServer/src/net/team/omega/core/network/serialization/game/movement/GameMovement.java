package net.team.omega.core.network.serialization.game.movement;

import net.team.omega.core.network.gameserver.ClientConnection;
import net.team.omega.core.network.serialization.MessageData;


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
