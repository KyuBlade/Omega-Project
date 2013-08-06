package net.team.omega.core.network.communication.serialization.data;

import net.team.omega.core.network.communication.serialization.RawData;


public class Account extends RawData
{

    private int id;
    private String name;
    private String password;
    private String email;
    private short rights;
    private boolean isBanned;
    
    public Account()
    {
	
    }
    
    public int getId()
    {
	return id;
    }
    
    public String getName()
    {
	return name;
    }
    
    public boolean isBanned()
    {
	return isBanned;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public short getRights()
    {
        return rights;
    }
    
}
