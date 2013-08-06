package net.team.omega.core.network.communication.queue;

import java.util.ArrayList;
import java.util.List;


public class WaitingConnectionQueue
{
    
    private static List<WaitingConnection> queue = new ArrayList<WaitingConnection>();
    
    private static WaitingConnectionQueue instance;
    
    public WaitingConnectionQueue()
    {
	
    }
    
    public static WaitingConnectionQueue getInstance()
    {
	if(instance == null)
	    instance = new WaitingConnectionQueue();
	
	return instance;
    }
    
    public void addToQueue(WaitingConnection wCon)
    {
	queue.add(wCon);
    }
    
    public WaitingConnection getInQueue(String key)
    {
	for(WaitingConnection _wCon : queue)
	{
	    if(_wCon.getKey().equals(key))
		return _wCon;
	}
	
	return null;
    }
    
    public void removeFromQueue(WaitingConnection wCon)
    {
	queue.remove(wCon);
    }
    
    public void purgeQueue()
    {
	queue.clear();
    }
    
}
