package net.team.omega.core.network.communication.serialization.connection.gameserver;

import java.util.List;

import net.team.omega.core.database.table.GameServer;
import net.team.omega.core.network.communication.LoadingFactory;
import net.team.omega.core.network.communication.loginserver.ClientConnection;
import net.team.omega.core.network.communication.serialization.MessageData;


public class ConnectionGameServerList extends MessageData
{
    
    private List<GameServer> gameservers;
    
    public ConnectionGameServerList()
    {
	gameservers = LoadingFactory.getInstance().getGameServers();
    }
    
    @Override
    public void process(ClientConnection connection)
    {
	
    }
    
}

