package com.team.omega.core.screen;

import com.team.omega.core.Constants;


public class GameScreen extends BaseScreen
{

    public GameScreen(ScreenManager screenManager)
    {
	super(screenManager, Constants.GAME_SCREEN_DEPTH);
    }
    
    @Override
    public void show()
    {
	super.show();
	
	screenManager.removeScreen(WaitingScreen.class);
    }
    
}
