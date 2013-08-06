package net.team.omega.communication.serialization.connection.gameserver;

import net.team.omega.communication.serialization.MessageData;
import net.team.omega.communication.serialization.datas.GameServer;

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
