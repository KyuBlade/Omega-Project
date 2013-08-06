package com.team.omega.core.screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;


public class ScreenManager implements Disposable
{
    
    private Map<Class<? extends Screen>, Screen> screens = new HashMap<Class<? extends Screen>, Screen>();
    private List<Class<? extends Screen>> activeScreens = new ArrayList<>();
    
    public ScreenManager()
    {
	
    }
    
    public void render(float delta)
    {
	for(Class<? extends Screen> _clazz : activeScreens)
	{
	    Screen _screen = getScreen(_clazz);
	    if(_screen == null)
		throw new NullPointerException("[Render] Active screen is not in the global list of screens");
	    else
		_screen.render(delta);
	}
    }
    
    public void resize(int width, int height)
    {
	for(Screen _screen : screens.values())
	    _screen.pause();
    }
    
    public void pause()
    {
	for(Class<? extends Screen> _clazz : activeScreens)
	{
	    Screen _screen = getScreen(_clazz);
	    if(_screen == null)
		throw new NullPointerException("[Pause] Active screen is not in the global list of screens");
	    else
		_screen.pause();
	}
    }
    
    public void resume()
    {
	for(Class<? extends Screen> _clazz : activeScreens)
	{
	    Screen _screen = getScreen(_clazz);
	    if(_screen == null)
		throw new NullPointerException("[Resume] Active screen is not in the global list of screens");
	    else
		_screen.resume();
	}
    }
    
    public void dispose()
    {
	for(Screen _screen : screens.values())
	    _screen.dispose();
    }
    
    public void addScreen(Screen screen)
    {
	if(screen == null)
	    throw new NullPointerException("Can't add the screen");
	
	if(screens.containsKey(screen.getClass())) // Already added
	{
	    if(activeScreens.contains(screen.getClass())) // Already active
		return;
	    else
		activeScreens.add(screen.getClass());
	}
	else
	{
	    screens.put(screen.getClass(), screen);
	    activeScreens.add(screen.getClass());
	}
    }
    
    public void removeScreen(Class<? extends Screen> screen)
    {
	if(activeScreens.contains(screen))
	    activeScreens.remove(screen);
    }
    
    public Screen getScreen(Class<? extends Screen> screen)
    {
	return screens.get(screen);
    }

}
