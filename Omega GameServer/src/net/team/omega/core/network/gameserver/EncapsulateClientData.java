package net.team.omega.core.network.gameserver;

import net.team.omega.core.ConnectionState;
import net.team.omega.core.network.serialization.data.Account;

public class EncapsulateClientData
{

    private Account account;
    private ConnectionState state;

    public EncapsulateClientData()
    {
	
    }
    
    public ConnectionState getState()
    {
        return state;
    }

    public void setState(ConnectionState state)
    {
        this.state = state;
    }
    
    public Account getAccount()
    {
        return account;
    }


    
    public void setAccount(Account account)
    {
        this.account = account;
    }

}
