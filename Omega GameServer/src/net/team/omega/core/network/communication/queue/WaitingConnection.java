package net.team.omega.core.network.communication.queue;

import net.team.omega.core.network.communication.serialization.data.Account;


public class WaitingConnection
{

    private Account account;
    private String key;
    
    public WaitingConnection()
    {
	
    }
    
    public WaitingConnection(Account account, String key)
    {
	this.account = account;
	this.key = key;
    }
    
    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }    
    
}
