package com.team.omega.core;

import java.util.Locale;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.team.omega.core.screen.IdentificationScreen;
import com.team.omega.core.screen.ScreenManager;

public class GameCore extends BaseGame
{

    private AssetManager assetManager;
    private ScreenManager screenManager;
    
    private static GameCore instance;
    
    public GameCore()
    {
	
    }
    
    public static GameCore getInstance()
    {
	if(instance == null)
	    instance = new GameCore();
	
	return instance;
    }

    @Override
    public void create()
    {
	Gdx.app.setLogLevel(Application.LOG_DEBUG);
	assetManager = new AssetManager();
	screenManager = new ScreenManager();
	
	LocalizationHandler.getInstance().setLanguage(Locale.US);
	
	Texture.setEnforcePotImages(false);
	// Load resources
	assetManager.load("data/skins/default/default-skin.atlas", TextureAtlas.class);
	assetManager.load("data/textures/tilesets/atlas-textures.atlas", TextureAtlas.class);
	
	assetManager.finishLoading();
	    
	screenManager.addScreen(new IdentificationScreen());
    }

    @Override
    public void render()
    {
	super.render();
	
	Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	
	screenManager.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height)
    {
	super.resize(width, height);
	
	screenManager.resize(width, height);
    }

    @Override
    public void pause()
    {
	super.pause();
	
	screenManager.pause();
    }

    @Override
    public void resume()
    {
	super.resume();
	
	screenManager.resume();
    }
    
    @Override
    public void dispose()
    {
	super.dispose();
	
	Gdx.app.log("GameCore", "Dispose");
	assetManager.dispose();
	screenManager.dispose();
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
