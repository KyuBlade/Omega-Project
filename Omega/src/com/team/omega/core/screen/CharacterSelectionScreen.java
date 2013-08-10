package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.team.omega.core.Constants;


public class CharacterSelectionScreen extends BaseScreen
{

    
    public CharacterSelectionScreen(ScreenManager screenManager)
    {
	super(screenManager, Constants.CHARACTER_SELECTION_SCREEN_DEPTH);
	
	Gdx.input.setInputProcessor(stage);
    }
    
}
