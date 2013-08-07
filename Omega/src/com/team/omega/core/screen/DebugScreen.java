package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


public class DebugScreen extends BaseScreen
{
    
    private Table layout;
    private Label fps;
    
    public DebugScreen()
    {
	super();
	
	this.layout = new Table(skin).right().top().padRight(10f);
	layout.setFillParent(true);
	this.fps = new Label("", skin);
	layout.add(fps);
	
	stage.addActor(layout);
    }
    
    @Override
    public void render(float delta)
    {
	super.render(delta);
	
	fps.setText("Fps : " + Gdx.graphics.getFramesPerSecond());
    }

}
