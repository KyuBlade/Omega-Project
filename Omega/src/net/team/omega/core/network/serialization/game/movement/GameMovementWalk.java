package net.team.omega.core.network.serialization.game.movement;


/**
 * Define the player walk movement to the server
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
    public void process()
    {
        
    }

    public void setIsForward(boolean isForward)
    {
        this.isForward = isForward;
    }

    public void setIsBackward(boolean isBackward)
    {
        this.isBackward = isBackward;
    }

    public void setIsLeft(boolean isLeft)
    {
        this.isLeft = isLeft;
    }

    public void setIsRight(boolean isRight)
    {
        this.isRight = isRight;
    }
    
}
