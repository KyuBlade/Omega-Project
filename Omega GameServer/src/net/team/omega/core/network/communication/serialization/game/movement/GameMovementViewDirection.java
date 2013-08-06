package net.team.omega.core.network.communication.serialization.game.movement;

import net.team.omega.core.network.communication.gameserver.ClientConnection;
import net.team.omega.core.network.communication.gameserver.GameServerFactory;


public class GameMovementViewDirection extends GameMovement
{

    //private Vector3f viewDirection;
    
    public GameMovementViewDirection()
    {
	super();
    }
    
    @Override
    public void process(ClientConnection connection)
    {
	GameServerFactory.getInstance().sendToAllInGameExceptUDP(connection.getID(), this);
    }

    /*public Vector3f getViewDirection()
    {
	return viewDirection;
    }	

    public void setViewDirection(Vector3f viewDirection)
    {
	this.viewDirection = viewDirection;
    }*/

}
