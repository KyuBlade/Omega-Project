package com.team.omega.core;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.team.omega.core.project.resource.ResourceLoader;
import com.team.omega.core.screen.EditorScreen;
import com.team.omega.core.screen.InterfaceScreen;
import com.team.omega.core.screen.ScreenManager;

public class EditorCore extends ApplicationAdapter
{

    private AssetManager internalAssetManager;
    private AssetManager externalAssetManager;
    private ScreenManager screenManager;
    
    private ResourceLoader resourceLoader;

    private static EditorCore instance;

    public EditorCore()
    {
	
    }

    public static EditorCore getInstance()
    {
	if (instance == null)
	    instance = new EditorCore();

	return instance;
    }

    @Override
    public void create()
    {
	Gdx.app.setLogLevel(Application.LOG_DEBUG);

	Texture.setEnforcePotImages(false);
	
	externalAssetManager = new AssetManager(new AbsoluteFileHandleResolver());
	//externalAssetManager.getLogger().setLevel(Logger.DEBUG);
	
	internalAssetManager = new AssetManager();
	//internalAssetManager.getLogger().setLevel(Logger.DEBUG);
	internalAssetManager.load("skin/skin.atlas", TextureAtlas.class);
	internalAssetManager.finishLoading();
	
	screenManager = new ScreenManager();
	screenManager.addScreen(EditorScreen.class);
	screenManager.addScreen(InterfaceScreen.class);
	
	resourceLoader = new ResourceLoader();
    }

    @Override
    public void render()
    {
	super.render();
	
	Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	
	resourceLoader.update();
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
	
	internalAssetManager.dispose();
	externalAssetManager.dispose();
	screenManager.dispose();
    }

    public AssetManager getInternalAssetManager()
    {
	return internalAssetManager;
    }
    
    public AssetManager getExternalAssetManager()
    {
	return externalAssetManager;
    }

    public ScreenManager getScreenManager()
    {
	return screenManager;
    }
    
    public ResourceLoader getResourceLoader()
    {
	return resourceLoader;
    }
    
    public ProjectInstance getCurrentProject()
    {
	return screenManager.getScreen(InterfaceScreen.class).getCurrentProject();
    }

}
