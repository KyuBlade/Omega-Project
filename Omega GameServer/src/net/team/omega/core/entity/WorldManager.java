package net.team.omega.core.entity;

import net.team.omega.logging.LogHandler;
import net.team.omega.utils.WorldTimerUtils;

import com.artemis.World;


public class WorldManager extends World implements Runnable
{
    
    private Thread worldThread;
    private boolean isRunning = true;
    
    private WorldTimerUtils timer;
    
    private String name;
    
    public WorldManager(String name)
    {
	super();
	
	timer = new WorldTimerUtils();
	this.name = name;
	
	worldThread = new Thread(this);
    }
    
    public void start()
    {
	worldThread.start();
    }

    /**
     * Update the world
     * 
     * @param delta time since last loop
     */
    private void update(float delta)
    {
	setDelta(delta);
	process();
    }

    @Override
    public void run()
    {
	while(isRunning)
	{
	    // Update delta
	    timer.updateDelta();
	    
	    // Update the world
	    update(timer.getDelta());
	}
	
	LogHandler.info("World " + name + " is now closed");
    }
    
    public void close()
    {
	// TODO: save the world
	
	// Exit the world thread
	isRunning = false;
    }
    
}
