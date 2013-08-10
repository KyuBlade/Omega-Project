package net.team.omega.core.network.serialization.connection.gameserver;

import net.team.omega.core.network.serialization.MessageData;

import com.team.omega.core.GameCore;
import com.team.omega.core.screen.CharacterCreationScreen;
import com.team.omega.core.screen.CharacterSelectionScreen;


public class ConnectionGameServerAccept extends MessageData
{
    
    private boolean needCharacterCreation;
    
    @Override
    public void process()
    {
        if(needCharacterCreation)
            GameCore.getInstance().getScreenManager().addScreen(CharacterCreationScreen.class);
        else
            GameCore.getInstance().getScreenManager().addScreen(CharacterSelectionScreen.class);
    }
    
}
