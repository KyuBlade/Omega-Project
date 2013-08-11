package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.team.omega.core.Constants;
import com.team.omega.ui.Panel;


public class DebugScreen extends BaseScreen
{

    private Label fps;
    private Label resolution;
    
    public DebugScreen(ScreenManager screenManager)
    {
	super(screenManager, Constants.DEBUG_SCREEN_DEPTH);
	
	this.fps = new Label("", skin);
	this.resolution = new Label("", skin);
	
	//layout.top().right();
	
	Panel _tableTopLeft = new Panel(skin, "black_alpha");
	
	Panel _tableTopRight = new Panel(skin, "black_alpha");
	_tableTopRight.top().right();
	_tableTopRight.add(fps).pad(5f).row();
	_tableTopRight.add(resolution).pad(5f);
	
	Panel _tableBottomLeft = new Panel(skin, "black_alpha");
	
	Panel _tableBottomRight = new Panel(skin, "black_alpha");
	
	layout.add(_tableTopLeft).expand();
	stage.addActor(_tableTopRight);
	layout.add().expand().row();
	//layout.add(_tableTopRight).expand().row();
	layout.add(_tableBottomLeft).expand();
	layout.add(_tableBottomRight).expand();
	layout.debug();
    }
    
    @Override
    public void render(float delta)
    {
	fps.setText("Fps : " + Gdx.graphics.getFramesPerSecond());
	fps.setText("Resolution : " + Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight());
	
	super.render(delta);
    }

}
