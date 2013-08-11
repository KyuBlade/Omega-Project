package net.team.omega.core.network.serialization.connection.gameserver;

import net.team.omega.core.network.GameServerFactory;
import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.character.CharacterList;

import com.team.omega.core.GameCore;
import com.team.omega.core.screen.CharacterCreationScreen;
import com.team.omega.core.screen.GameServerSelectionScreen;


public class ConnectionGameServerAccept extends MessageData
{
    
    private boolean needCharacterCreation;
    
    @Override
    public void process()
    {
        if(needCharacterCreation)
            GameCore.getInstance().getScreenManager().addScreen(CharacterCreationScreen.class).setRequestFrom(GameServerSelectionScreen.class);
        else
            GameServerFactory.getInstance().sendTCP(new CharacterList());
    }
    
}
