package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.team.omega.core.Constants;


public class CharacterCreationScreen extends BaseScreen
{

    public CharacterCreationScreen(ScreenManager screenManager)
    {
	super(screenManager, Constants.CHARACTER_SELECTION_SCREEN_DEPTH);
	
	Gdx.input.setInputProcessor(stage);
    }
    
}
