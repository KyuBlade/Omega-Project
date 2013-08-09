package net.team.omega.core.network.loginserver;

import net.team.omega.utils.LogHandler;

import com.esotericsoftware.kryonet.Server;

public class BaseLoginServer extends Server
{

    @Override
    protected ClientConnection newConnection()
    {
	return new ClientConnection();
    }

    @Override
    public void sendToAllTCP(Object object)
    {
	LogHandler.system("Send[ALL] >> " + object.toString());

	super.sendToAllTCP(object);
    }

}
