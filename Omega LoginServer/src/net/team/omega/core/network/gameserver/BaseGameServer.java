package net.team.omega.core.network.gameserver;

import com.esotericsoftware.kryonet.Server;

public class BaseGameServer extends Server
{

    @Override
    protected GameServerConnection newConnection()
    {
	return new GameServerConnection();
    }

    /*public GameServerConnection[] getConnections()
    {
	return (GameServerConnection[]) super.getConnections();
    }*/

}
