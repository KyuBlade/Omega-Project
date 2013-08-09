package com.team.omega.core.screen;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;


public class WaitingScreen extends BaseScreen
{

    private Dialog modal;
    
    public WaitingScreen()
    {
	modal = new Dialog("Please wait ...", skin, "no-background");
	modal.setModal(true);
	modal.setMovable(false);
	layout.add(modal);
    }
    
}
