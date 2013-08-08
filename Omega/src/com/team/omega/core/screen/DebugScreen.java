package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.team.omega.ui.Panel;


public class DebugScreen extends BaseScreen
{

    private Label fps;
    
    public DebugScreen()
    {
	super();
	
	this.fps = new Label("", skin);
	layout.top().right();
	Panel _table = new Panel(skin, "black_alpha");
	_table.add(fps).pad(5f);
	
	layout.add(_table);
    }
    
    @Override
    public void render(float delta)
    {
	super.render(delta);
	
	fps.setText("Fps : " + Gdx.graphics.getFramesPerSecond());
    }

}
