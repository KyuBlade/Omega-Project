package net.team.omega.core.network.communication.serialization.internal;

import net.team.omega.core.database.table.Account;
import net.team.omega.core.network.communication.serialization.MessageData;


public class InternalGameServerGenerateKey extends MessageData
{
    
    private Account account;

    public void setAccount(Account account)
    {
        this.account = account;
    }
    
}
