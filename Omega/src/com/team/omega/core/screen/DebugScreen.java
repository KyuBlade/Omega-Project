package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.team.omega.core.Constants;
import com.team.omega.ui.panel.Panel;
import com.team.omega.utils.FormatUtils;


public class DebugScreen extends BaseScreen
{

    private Label fps;
    private Label resolution;
    
    private Panel performances;
    private Label javaHeap;
    private Label nativeHeap;
    
    private Panel screens;
    
    private long lastTime = System.currentTimeMillis();
    
    public DebugScreen(ScreenManager screenManager)
    {
	super(screenManager, Constants.DEBUG_SCREEN_DEPTH);
	
	this.fps = new Label("", skin);
	this.resolution = new Label("", skin);
	
	Table _graphicsTable = new Table();
	Table _screensTable = new Table();
	
	Panel _graphics = new Panel(skin, "black_alpha");
	_graphics.defaults().left();
	_graphics.add(resolution).row();
	_graphics.add(fps);
	_graphicsTable.top().right().add(_graphics);
	
	performances = new Panel(skin,"black_alpha");
	performances.defaults().left();
	javaHeap = new Label("", skin);
	nativeHeap = new Label("", skin);
	performances.add(javaHeap).row();
	performances.add(nativeHeap);
	
	_screensTable.add(performances).minWidth(190f).right().row();
	
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
	
	long _currentTime = System.currentTimeMillis();
	if(_currentTime - lastTime > 500)
	{
	    javaHeap.setText("Java Heap : " + FormatUtils.byteFormat(Gdx.app.getJavaHeap(), true));
	    nativeHeap.setText("Native Heap : " + FormatUtils.byteFormat(Gdx.app.getNativeHeap(), true));
	    
	    lastTime = _currentTime;
	}
	
	screens.clear();
	synchronized(screenManager.getActiveScreen())
	{
	    for(BaseScreen _screen : screenManager.getActiveScreen())
		screens.add(_screen.getDepth() + " : " + _screen.getClass().getSimpleName()).left().row();
	}
	
	super.render(delta);
    }

}
