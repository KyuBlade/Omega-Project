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
	WaitingConnection _wCon;
	String _key = UUID.randomUUID().toString().replace("-", "");

	WaitingConnection _usedCon = WaitingConnectionQueue.getInstance().getInQueue(account);
	if (_usedCon != null)
	    _wCon = _usedCon;
	else
	    _wCon = new WaitingConnection(account, _key);
	
	WaitingConnectionQueue.getInstance().addToQueue(_wCon);
	    
	InternalGameServerGeneratedKey message = new InternalGameServerGeneratedKey(_wCon);
	InternalGameServerFactory.getInstance().getClient().sendTCP(message);
    }
    
}
