package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.team.omega.ui.base.panel.Panel;


public class DebugScreen extends BaseScreen
{

    private Label fps;
    private Label resolution;
    
    private Panel screens;
    private Panel graphics;
    
    private Stack stack;
    
    public DebugScreen(ScreenManager screenManager)
    {
	super(screenManager, 10);
	
	fps = new Label("", skin);
	resolution = new Label("", skin);
	fps.setTouchable(Touchable.disabled);
	resolution.setTouchable(Touchable.disabled);
	
	Table _graphicsTable = new Table();
	Table _screensTable = new Table();
	
	graphics = new Panel(skin, "black_alpha");
	graphics.defaults().left();
	graphics.add(resolution).row();
	graphics.add(fps);
	_graphicsTable.top().left().add(graphics);
	
	screens = new Panel(skin, "black_alpha");
	screens.defaults().left();
	_screensTable.bottom().right().add(screens).right();
	
	stack = new Stack();
	stack.setFillParent(true);
	stack.add(_graphicsTable);
	stack.add(_screensTable);
	layout.add(stack);
	layout.bottom().left();
    }
    
    @Override
    public void resize(int width, int height)
    {
	super.resize(width, height);
	
        stack.size(width, height);
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
