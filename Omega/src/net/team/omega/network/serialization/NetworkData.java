package net.team.omega.network.serialization;


public class NetworkData
{

    public NetworkData()
    {
	
    }
    
    public boolean onDebugDisplay()
    {
	return true;
    }
    
    @Override
    public String toString()
    {
        return this.getClass().getSimpleName();
    }
    
}
