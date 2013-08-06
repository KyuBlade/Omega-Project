package com.team.omega.core.screen;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.team.omega.core.GameCore;


public class ScreenManager
{
    
    private GameCore core;
    
    private Map<Class<? extends Screen>, Screen> screens = new HashMap<Class<? extends Screen>, Screen>();
    
    public ScreenManager(GameCore core)
    {
	this.core = core;
    }
    
    public void addScreen(Screen screen)
    {
	if(screen == null)
	    throw new NullPointerException("Screen is null");
	
	screens.put(screen.getClass(), screen);
    }
    
    public <T extends Screen> void setScreen(Class<T> clazz)
    {
	if(clazz == null)
	    throw new NullPointerException("Screen class is null");
	    
	Screen _screen = screens.get(clazz);
	if(_screen == null)
	    throw new NullPointerException("Can't find screen. Maybe it's not added first");
	
	core.setScreen(screens.get(clazz));
    }

}
