package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.team.omega.core.Constants;

public class WaitingScreen extends BaseScreen
{

    private Dialog modal;
    
    public WaitingScreen(ScreenManager screenManager)
    {
	super(screenManager, Constants.WAITING_SCREEN_DEPTH);
	
	stage.setViewport(Gdx.graphics.getWidth() + 10f, Gdx.graphics.getHeight(), true);
	modal = new Dialog("Please wait ...", skin, "no-background");
	modal.setMovable(false);
	layout.add(modal);
    }
    
    @Override
    public void show()
    {
	Gdx.input.setInputProcessor(stage);
	setActive(true);
    }
    
    @Override
    public void hide()
    {
	setActive(false);
	Gdx.input.setInputProcessor(inputProcessor);
    }

}
