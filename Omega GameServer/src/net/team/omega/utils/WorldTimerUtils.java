package net.team.omega.utils;


public class WorldTimerUtils
{

    private float deltaTime = 0;
    private long lastTime = System.nanoTime();
    
    public void updateDelta()
    {
	long time = System.nanoTime();
	deltaTime = (time - lastTime) / 1000000000.0f;
	lastTime = time;
    }
    
    public float getDelta()
    {
	return deltaTime;
    }
    
}
