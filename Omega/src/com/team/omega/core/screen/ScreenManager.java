package com.team.omega.core.screen;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;


public class ScreenManager implements Disposable
{
    
    private Map<Class<? extends Screen>, BaseScreen> screens = new ConcurrentHashMap<>();
    private List<BaseScreen> depthList = new ArrayList<>();
    
    public ScreenManager()
    {
	
    }
    
    public synchronized void render(float delta)
    {
	for (Screen _screen : depthList)
	    _screen.render(delta);
    }
    
    public void resize(int width, int height)
    {
	for (Screen _screen : screens.values())
	    _screen.resize(width, height);
    }
    
    public void pause()
    {
	for (Class<? extends Screen> _clazz : screens.keySet())
	{
	    Screen _screen = screens.get(_clazz);
	    if (_screen == null)
		throw new NullPointerException("[Pause] Active screen is not in the global list of screens");
	    else
		_screen.pause();
	}
    }

    public void resume()
    {
	for (Class<? extends Screen> _clazz : screens.keySet())
	{
	    Screen _screen = screens.get(_clazz);
	    if (_screen == null)
		throw new NullPointerException("[Resume] Active screen is not in the global list of screens");
	    else
		_screen.resume();
	}
    }

    public void dispose()
    {
	for (BaseScreen _screen : screens.values())
	    _screen.dispose();
    }
    
    public synchronized <T extends BaseScreen> T addScreen(Class<T> screen)
    {
	if (screen == null)
	    throw new NullPointerException("Can't add the screen");

	BaseScreen _screen;
	if (screens.containsKey(screen)) // Already added
	    _screen = screens.get(screen);
	else
	{
	    // Instanciate screen
	    try
	    {
		_screen = screen.getConstructor(ScreenManager.class).newInstance(this);
	    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
	    {
		Gdx.app.error("ScreenManager", "Unable to instanciate screen " + screen.getSimpleName(), e);

		return null;
	    }

	    screens.put(screen, _screen);
	}

	((BaseScreen) _screen).show();;
	
	updateScreens();

	return (T) _screen;
    }
    
    public void removeScreen(Class<? extends BaseScreen> screen)
    {
	BaseScreen _screen = screens.get(screen);
	
	if(_screen != null)
	    _screen.hide();
	
	updateScreens();
    }
    
    public <T extends BaseScreen> T getScreen(Class<T> screen)
    {
	return ((T) screens.get(screen));
    }

    /**
     * Called internally when add, remove or modify screens.
     * Call it only if you know what you do.
     */
    public synchronized void updateScreens()
    {
	depthList.clear();
	for(BaseScreen _screen : screens.values())
	{
	    if(_screen.isActive)
		depthList.add(_screen);
	}
	
	Collections.sort(depthList);
    }
    
}
