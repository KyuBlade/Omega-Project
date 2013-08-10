package com.team.omega.core.entity;

import com.artemis.World;
import com.badlogic.gdx.Gdx;


public class WorldManager extends World implements Runnable
{
    
    private Thread worldThread;
    private boolean isRunning = true;
    
    private String name;
    
    public WorldManager(String name)
    {
	super();
	
	this.name = name;
	
	worldThread = new Thread(this);
    }
    
    /**
     * Start the world process.
     */
    public void start()
    {
	worldThread.start();
    }

    /**
     * Update the world
     * 
     * @param delta time since last loop.
     */
    private void update(float delta)
    {
	setDelta(delta);
	process();
    }

    /**
     * It's the world loop, all is processed in it.
     */
    @Override
    public void run()
    {
	while(isRunning)
	{
	    // Update the world
	    update(Gdx.graphics.getDeltaTime());
	}
	
	Gdx.app.debug("WorldManager", "World " + name + " is now closed");
    }
    
    /**
     * Close the world.
     * When world is closed, you can't reactivate it without create a new one.
     */
    public void close()
    {
	
	// Exit the world thread
	isRunning = false;
    }
    
}
