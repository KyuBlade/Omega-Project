package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


public class DebugScreen extends BaseScreen
{

    private Label fps;
    
    public DebugScreen()
    {
	super();
	
	this.fps = new Label("", skin);
	layout.debug();
	//Table _table = new Table();
	//_table.setFillParent(true);
	//_table.debug();
	//_table.add(fps);
	layout.add(fps).top();
    }
    
    @Override
    public void render(float delta)
    {
	super.render(delta);
	
	fps.setText("Fps : " + Gdx.graphics.getFramesPerSecond());
    }

}
