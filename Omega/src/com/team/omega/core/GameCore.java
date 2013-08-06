package com.team.omega.core;

import java.util.Locale;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.team.omega.core.screen.IdentificationScreen;
import com.team.omega.core.screen.ScreenManager;

public class GameCore extends Game
{

    private AssetManager assetManager;
    private ScreenManager screenManager;
    
    private boolean isLoading = true;
    
    public GameCore()
    {
	
    }

    @Override
    public void create()
    {
	Gdx.app.setLogLevel(Gdx.app.LOG_DEBUG);
	assetManager = new AssetManager();
	screenManager = new ScreenManager(this);
	
	LocalizationHandler.getInstance().setLanguage(Locale.US);
	
	// Load resources
	assetManager.load("data/skins/default/default-skin.atlas", TextureAtlas.class);
	assetManager.load("data/textures/tilesets/atlas-textures.atlas", TextureAtlas.class);
    }

    @Override
    public void dispose()
    {
	super.dispose();
	
	Gdx.app.log("GameCore", "Dispose");
	assetManager.dispose();
    }

    @Override
    public void render()
    {
	super.render();
	
	if(!isLoading)
	    return;
	
	if(assetManager.update())
	{
	    isLoading = false;
	    
	    screenManager.addScreen(new IdentificationScreen(this));
	    screenManager.setScreen(IdentificationScreen.class);
	}
    }

    @Override
    public void resize(int width, int height)
    {
	super.resize(width, height);
    }

    @Override
    public void pause()
    {
	super.pause();
    }

    @Override
    public void resume()
    {
	super.resume();
    }
    
    public AssetManager getAssetManager()
    {
	return assetManager;
    }
    
    public ScreenManager getScreenManager()
    {
	return screenManager;
    }
    
}
