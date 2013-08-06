package net.team.omega.core.network.serialization.game.movement;

import net.team.omega.core.network.gameserver.ClientConnection;
import net.team.omega.core.network.gameserver.GameServerFactory;


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
