package net.team.omega.core.network.communication.serialization.game.movement;

import net.team.omega.core.network.communication.gameserver.ClientConnection;
import net.team.omega.core.network.communication.gameserver.GameServerFactory;

/**
 * Define movement of the character
 */
public class GameMovementWalk extends GameMovement
{

    /**
     * Moving forward
     */
    private boolean isForward;
    
    /**
     * Moving backward
     */
    private boolean isBackward;
    
    /**
     * Strafe left
     */
    private boolean isLeft;
    
    /**
     * Strafe right
     */
    private boolean isRight;
    
    public GameMovementWalk()
    {
	super();
    }
    
    @Override
    public void process(ClientConnection connection)
    {
	//TODO:
	// Update character on the server
	/*MoveControl _c = MasterEntityData.getInstance().getComponent(connection.getPlayer(), ModelComponent.class).getModel().getControl(MoveControl.class);
	_c.setIsMovingBackward(isBackward);
	_c.setIsMovingForward(isForward);
	_c.setIsMovingLeft(isLeft);
	_c.setIsMovingRight(isRight);*/
	
	GameServerFactory.getInstance().sendToAllInGameExceptUDP(connection.getID(), this);
    }

}
