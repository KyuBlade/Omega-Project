package net.team.omega.core.network.serialization.connection.gameserver;

import com.team.omega.core.GameCore;
import com.team.omega.core.screen.GameServerSelectionScreen;

import net.team.omega.core.network.GameServerFactory;
import net.team.omega.core.network.serialization.MessageData;


public class ConnectionGameServerKey extends MessageData
{

    private String key;
    
    public String getKey()
    {
	return key;
    }
    
    @Override
    public void process()
    {
	GameServerFactory.getInstance().start(GameCore.getInstance().getScreenManager().getScreen(GameServerSelectionScreen.class).getSelectedGameServer(), key);
    }
    
}
