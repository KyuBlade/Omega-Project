package com.team.omega.core;

import java.util.Locale;
import java.util.concurrent.Callable;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.team.omega.core.screen.DebugScreen;
import com.team.omega.core.screen.GameScreen;
import com.team.omega.core.screen.LoadingScreen;
import com.team.omega.core.screen.ScreenManager;

public class GameCore extends BaseGame
{

    private AssetManager assetManager;
    private ScreenManager screenManager;
    private ConfigManager configManager;
    
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
	
	configManager = new ConfigManager();
	assetManager = new AssetManager();
	screenManager = new ScreenManager();
	
	LocalizationHandler.getInstance().setLanguage(Locale.US);
	
	Texture.setEnforcePotImages(false);
	// Load resources
	assetManager.load("data/skins/default/default-skin.atlas", TextureAtlas.class);
	assetManager.load("data/textures/tilesets/atlas-textures.atlas", TextureAtlas.class);
	assetManager.load("data/skins/default/backgrounds/login_bg.jpg", Texture.class);
	
	assetManager.finishLoading();
	
	//screenManager.addScreen(IdentificationScreen.class);
	screenManager.addScreen(GameScreen.class);
	screenManager.addScreen(DebugScreen.class);
    }

    @Override
    public void render()
    {
	super.render();
	
	Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
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
    
    public ConfigManager getConfigManager()
    {
	return configManager;
    }
    
}
