package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.team.omega.ui.base.panel.Panel;


public class DebugScreen extends BaseScreen
{

    private Label fps;
    private Label resolution;
    
    private Panel screens;
    
    public DebugScreen(ScreenManager screenManager)
    {
	super(screenManager, 10);
	
	fps = new Label("", skin);
	resolution = new Label("", skin);
	
	Table _graphicsTable = new Table();
	Table _screensTable = new Table();
	
	Panel _graphics = new Panel(skin, "black_alpha");
	_graphics.defaults().left();
	_graphics.add(resolution).row();
	_graphics.add(fps);
	_graphicsTable.top().left().add(_graphics);
	
	screens = new Panel(skin, "black_alpha");
	screens.defaults().left();
	_screensTable.bottom().right().add(screens).right();
	
	layout.stack(_graphicsTable, _screensTable).size(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    
    @Override
    public void render(float delta)
    {
	fps.setText("Fps : " + Gdx.graphics.getFramesPerSecond());
	resolution.setText("Resolution : " + Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight());
	
	screens.clear();
	synchronized(screenManager.getActiveScreen())
	{
	    for(BaseScreen _screen : screenManager.getActiveScreen())
		screens.add(_screen.getDepth() + " : " + _screen.getClass().getSimpleName()).left().row();
	}
	
	super.render(delta);
    }

}
