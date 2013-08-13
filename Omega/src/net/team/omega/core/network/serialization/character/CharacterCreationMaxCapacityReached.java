package net.team.omega.core.network.serialization.character;

import com.team.omega.core.GameCore;
import com.team.omega.core.LocalizationHandler;
import com.team.omega.core.screen.CharacterCreationScreen;

import net.team.omega.core.network.serialization.MessageData;


public class CharacterCreationMaxCapacityReached extends MessageData
{

    public CharacterCreationMaxCapacityReached()
    {
	
    }
    
    @Override
    public void process()
    {
        GameCore.getInstance().getScreenManager().getScreen(CharacterCreationScreen.class).showPopup("", LocalizationHandler.getInstance().getDialog("character.creation.error.capacity"));
    }
    
}
