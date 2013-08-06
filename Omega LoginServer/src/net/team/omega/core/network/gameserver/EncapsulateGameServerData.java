package net.team.omega.core.network.gameserver;

import net.team.omega.core.database.table.GameServer;

public class EncapsulateGameServerData
{

    private GameServer gameserver;

    public GameServer getGameServer()
    {
	return gameserver;
    }

    public void setGameServer(GameServer gameserver)
    {
	this.gameserver = gameserver;
    }

}
