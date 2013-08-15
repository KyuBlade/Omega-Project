package com.team.omega.core.screen;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.team.omega.core.Constants;
import com.team.omega.ui.ProgressBar;


public class LoadingScreen extends BaseScreen
{

    private ProgressBar progressBar;
    private float currentProgress;
    private Label progressText;
    
    public LoadingScreen(ScreenManager screenManager)
    {
	super(screenManager, Constants.LOADING_SCREEN_DEPTH);
	
	progressBar = new ProgressBar(skin, "loading");
	progressBar.setWidth(500f);
	progressBar.setHeight(10f);
	layout.add(progressBar);
    }
    
    @Override
    public void render(float delta)
    {
	progressBar.setValue(currentProgress);
	
	if(currentProgress < 100)
	    currentProgress += 20 * delta;
	
	super.render(delta);
    }

}
