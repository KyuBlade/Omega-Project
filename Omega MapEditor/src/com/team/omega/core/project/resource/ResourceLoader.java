package com.team.omega.core.project.resource;

import java.util.LinkedList;


public class ResourceLoader
{

    private LinkedList<Runnable> queue = new LinkedList<>();
    
    public ResourceLoader()
    {
	
    }
    
    public void addToQueue(Runnable task)
    {
	queue.add(task);
    }
    
    public void update()
    {
	if(queue.isEmpty())
	    return;
	
	Runnable _task = queue.getFirst();
	queue.removeFirst();
	
	_task.run();
    }
    
}
