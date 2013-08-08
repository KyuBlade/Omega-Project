package com.team.omega.core.screen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;


public class ScreenManager implements Disposable
{
    
    private Map<Class<? extends Screen>, Screen> screens = new HashMap<>();
    private List<Class<? extends Screen>> activeScreens = new CopyOnWriteArrayList<>();
    
    public ScreenManager()
    {
	
    }
    
    public void render(float delta)
    {
	MasterScreen.masterRender(delta);

	for (Class<? extends Screen> _clazz : activeScreens)
	{
	    Screen _screen = getScreen(_clazz);
	    if (_screen == null)
		throw new NullPointerException("[Render] Active screen is not in the global list of screens");
	    else
		_screen.render(delta);
	}
    }
    
    public void resize(int width, int height)
    {
	MasterScreen.masterResize(width, height);

	for (Screen _screen : screens.values())
	    _screen.resize(width, height);
    }
    
    public void pause()
    {
	for (Class<? extends Screen> _clazz : activeScreens)
	{
	    Screen _screen = getScreen(_clazz);
	    if (_screen == null)
		throw new NullPointerException("[Pause] Active screen is not in the global list of screens");
	    else
		_screen.pause();
	}
    }

    public void resume()
    {
	for (Class<? extends Screen> _clazz : activeScreens)
	{
	    Screen _screen = getScreen(_clazz);
	    if (_screen == null)
		throw new NullPointerException("[Resume] Active screen is not in the global list of screens");
	    else
		_screen.resume();
	}
    }

    public void dispose()
    {
	MasterScreen.masterDispose();

	for (Screen _screen : screens.values())
	    _screen.dispose();
    }
    
    public synchronized <T extends Screen> T addScreen(Class<T> screen)
    {
	if(screen == null)
	    throw new NullPointerException("Can't add the screen");
	
	    if (screens.containsKey(screen)) // Already added
	    {
		if (!activeScreens.contains(screen)) // Already active
		    activeScreens.add(screen);

		return (T) screens.get(screen);
	    }
	    else
	    {
		// Instanciate screen
		Screen _screen;
		try
		{
		    _screen = screen.newInstance();
		} catch (InstantiationException | IllegalAccessException e)
		{
		    Gdx.app.error("ScreenManager", "Unable to instanciate screen " + screen.getSimpleName());

		    return null;
		}

		screens.put(screen, _screen);
		activeScreens.add(screen);

		return (T) _screen;
	    }
    }
    
    public void removeScreen(Class<? extends Screen> screen)
    {
	if(activeScreens.contains(screen))
	    activeScreens.remove(screen);
    }
    
    public <T extends Screen> T getScreen(Class<T> screen)
    {
	return ((T) screens.get(screen));
    }

}
