package com.team.omega.core.screen;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.team.omega.core.Constants;


public class WaitingScreen extends BaseScreen
{

    private Dialog modal;
    
    public WaitingScreen(ScreenManager screenManager)
    {
	super(screenManager, Constants.WAITING_SCREEN_DEPTH);
	
	modal = new Dialog("Please wait ...", skin, "no-background");
	modal.setModal(true);
	modal.setMovable(false);
	layout.add(modal);
    }
    
}
