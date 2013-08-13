package net.team.omega.core.network.serialization.game;

import net.team.omega.core.network.serialization.MessageData;

import com.team.omega.core.GameCore;
import com.team.omega.core.screen.CharacterCreationScreen;
import com.team.omega.core.screen.CharacterSelectionScreen;
import com.team.omega.core.screen.GameScreen;
import com.team.omega.core.screen.ScreenManager;

public class StartGame extends MessageData
{
 
    @Override
    public void process()
    {
        ScreenManager _screenManager = GameCore.getInstance().getScreenManager();
        _screenManager.removeScreen(CharacterCreationScreen.class);
        _screenManager.removeScreen(CharacterSelectionScreen.class);
        _screenManager.addScreen(GameScreen.class);
    }
    
}
