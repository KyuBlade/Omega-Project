package net.team.omega.network.serialization.connection.gameserver;

import net.team.omega.network.serialization.MessageData;
import net.team.omega.network.serialization.datas.GameServer;

public class ConnectionGameServerSelect extends MessageData
{
    
    private GameServer gameserver;
    
    public ConnectionGameServerSelect(GameServer gameserver)
    {
        this.gameserver = gameserver;
    }
    
    public void setGameServer(GameServer gameserver)
    {
        this.gameserver = gameserver;
    }
    
}
