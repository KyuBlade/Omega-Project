package net.team.omega.core.network.serialization.connection.gameserver;

import net.team.omega.Constants;
import net.team.omega.core.database.table.GameServer;
import net.team.omega.core.network.gameserver.GameServerConnection;
import net.team.omega.core.network.gameserver.InternalLoginServerFactory;
import net.team.omega.core.network.loginserver.ClientConnection;
import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.internal.InternalGameServerGenerateKey;


public class ConnectionGameServerSelect extends MessageData
{

    private GameServer gameserver;
    
    @Override
    public void process(ClientConnection connection)
    {
	if(gameserver.getState() != Constants.GAME_SERVER_STATE_ONLINE)
	    return;
	
	for(GameServerConnection con : InternalLoginServerFactory.getInstance().getServer().getConnections())
	{
	    if(((GameServerConnection) con).getGameserverDatas().getGameServer().getId() == gameserver.getId())
	    {
		InternalGameServerGenerateKey message = new InternalGameServerGenerateKey();
		message.setAccount(connection.getClientDatas().getAccount());
		((GameServerConnection) con).sendTCP(message);
		
		return;
	    }
	}
    }
    
}
