package net.team.omega.core.network.serialization.internal;

import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.data.Account;


public class InternalGameServerGeneratedKey extends MessageData
{

    private Account account;
    private String key;
    
    public InternalGameServerGeneratedKey()
    {
	
    }
    
    public InternalGameServerGeneratedKey(Account account, String key)
    {
	this.account = account;
	this.key = key;
    }
    
    public void setAccount(Account account)
    {
        this.account = account;
    }
    
    public void setKey(String key)
    {
        this.key = key;
    }
    
}
