package net.team.omega.core.network.communication.loginserver;

import net.team.omega.utils.LogHandler;

import com.esotericsoftware.kryonet.Server;

public class BaseLoginServer extends Server
{

    @Override
    protected ClientConnection newConnection()
    {
	return new ClientConnection();
    }

    public ClientConnection[] getClientConnections()
    {
	return (ClientConnection[]) super.getConnections();
    }

    @Override
    public void sendToAllTCP(Object object)
    {
	LogHandler.system("Send[ALL] >> " + object.toString());

	super.sendToAllTCP(object);
    }

}
