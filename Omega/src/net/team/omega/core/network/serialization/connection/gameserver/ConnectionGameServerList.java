package net.team.omega.core.network.serialization.connection.gameserver;

import java.util.ArrayList;

import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.datas.GameServer;

public class ConnectionGameServerList extends MessageData
{

    private ArrayList<GameServer> gameservers;

    @Override
    public void process()
    {
        for(GameServer _gs : gameservers)
        {
            // Add to list
        }
        
        // Go to game server selection screen
    }

}
