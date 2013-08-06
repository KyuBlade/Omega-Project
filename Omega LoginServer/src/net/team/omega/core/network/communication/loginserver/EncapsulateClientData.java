package net.team.omega.core.network.communication.loginserver;

import net.team.omega.core.database.table.Account;

public class EncapsulateClientData
{

    private Account account;

    public Account getAccount()
    {
	return account;
    }

    public void setAccount(Account account)
    {
	this.account = account;
    }

}
