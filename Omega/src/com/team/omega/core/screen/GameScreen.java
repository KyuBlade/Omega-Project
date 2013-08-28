package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.team.omega.core.Constants;
import com.team.omega.core.GameCore;
import com.team.omega.core.renderer.IsometricMapRenderer;


public class GameScreen extends BaseScreen
{

    private OrthographicCamera camera;
    private TiledMap map;
    private IsometricMapRenderer renderer;
    
    public GameScreen(ScreenManager screenManager)
    {
	super(screenManager, Constants.GAME_SCREEN_DEPTH);
	
	GameCore.getInstance().getAssetManager().setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
	GameCore.getInstance().getAssetManager().load("data/maps/big.tmx", TiledMap.class);
	
	GameCore.getInstance().getAssetManager().finishLoading();
	
	map = GameCore.getInstance().getAssetManager().get("data/maps/big.tmx");
	
	camera = new OrthographicCamera();
	camera.setToOrtho(false, 1280f, 720f);
	renderer = new IsometricMapRenderer(map, 1f);
	
	inputProcessor.addProcessor(new InputProcessor(){

	    @Override
	    public boolean keyDown(int keycode)
	    {
		switch(keycode)
		{
		    case Keys.Z:
			camera.translate(0f, 10f);
			
			break;
			
		    case Keys.S:
			camera.translate(0f, -10f);
			
			break;
			
		    case Keys.Q:
			camera.translate(-10f, 0f);
			
			break;
			
		    case Keys.D:
			camera.translate(10f, 0f);
			
			break;
		}
		
		return true;
	    }

	    @Override
	    public boolean keyUp(int keycode)
	    {
		return false;
	    }

	    @Override
	    public boolean keyTyped(char character)
	    {
		return false;
	    }

	    @Override
	    public boolean touchDown(int screenX, int screenY, int pointer, int button)
	    {
		return true;
	    }

	    @Override
	    public boolean touchUp(int screenX, int screenY, int pointer, int button)
	    {
		return false;
	    }

	    @Override
	    public boolean touchDragged(int screenX, int screenY, int pointer)
	    {
		if(screenX < 100f) // left
		{
		    camera.translate(-10f, 0f);
		}
		else if(screenX > Gdx.graphics.getWidth() -100f) // right
		{
		    camera.translate(10f, 0f);
		}
		if(screenY < 100f) // bottom
		{
		    camera.translate(0f, 10f);
		}
		else if(screenY > Gdx.graphics.getHeight() -100f) // top
		{
		    camera.translate(0f, -10f);
		}
		return false;
	    }

	    @Override
	    public boolean mouseMoved(int screenX, int screenY)
	    {
		return false;
	    }

	    @Override
	    public boolean scrolled(int amount)
	    {
		camera.zoom += amount * 0.1f;
		
		return true;
	    }
	    
	});
    }
    
    @Override
    public void show()
    {
	super.show();
	
	screenManager.removeScreen(WaitingScreen.class);
    }
    
    @Override
    public void render(float delta)
    {
	camera.update();
	renderer.setView(camera);	
	
	renderer.render();
    }
    
}
