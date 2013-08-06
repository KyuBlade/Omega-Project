package net.team.omega.core.network.serialization.internal;

import java.util.UUID;

import net.team.omega.core.network.loginserver.InternalGameServerFactory;
import net.team.omega.core.network.queue.WaitingConnection;
import net.team.omega.core.network.queue.WaitingConnectionQueue;
import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.data.Account;


public class InternalGameServerGenerateKey extends MessageData
{
    
    private Account account;
    
    @Override
    public void process()
    {
	String _key = UUID.randomUUID().toString().replace("-", "");
	
	InternalGameServerGeneratedKey message = new InternalGameServerGeneratedKey(account, _key);

	WaitingConnection wCon = new WaitingConnection(account, _key);
	WaitingConnectionQueue.getInstance().addToQueue(wCon);
	
	InternalGameServerFactory.getInstance().getClient().sendTCP(message);
    }
    
}
