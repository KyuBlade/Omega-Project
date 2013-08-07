package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


public class DebugScreen extends BaseScreen
{

    private Label fps;
    
    public DebugScreen()
    {
	super();
	
	this.fps = new Label("", skin);
	
	Table _table = new Table(skin);
	_table.right().top().padRight(10f).add(fps);
	
	layout.add(_table);
	
	stage.addActor(layout);
    }
    
    @Override
    public void render(float delta)
    {
	super.render(delta);
	
	fps.setText("Fps : " + Gdx.graphics.getFramesPerSecond());
    }

}
