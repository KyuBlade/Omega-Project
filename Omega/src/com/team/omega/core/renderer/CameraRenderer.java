package com.team.omega.core.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.team.omega.core.screen.MasterScreen;

public class CameraRenderer implements Renderer
{

    private OrthographicCamera camera;

    public CameraRenderer(boolean yDown, float width, float height)
    {
	camera = new OrthographicCamera();
	camera.setToOrtho(yDown, width, height);
	
	MasterScreen.getInputProcessor().addProcessor(new InputProcessor(){

	    @Override
	    public boolean keyDown(int keycode)
	    {
		return false;
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
		return false;
	    }

	    @Override
	    public boolean touchUp(int screenX, int screenY, int pointer, int button)
	    {
		return false;
	    }

	    @Override
	    public boolean touchDragged(int screenX, int screenY, int pointer)
	    {
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
		camera.zoom += amount * (1 + Gdx.graphics.getDeltaTime());
		
		return true;
	    }
	    
	});
    }

    @Override
    public void render(float delta)
    {
	if(Gdx.input.isKeyPressed(Keys.Z))
	{
	    camera.translate(0f, 10f * (1 + delta));
	}
	else if(Gdx.input.isKeyPressed(Keys.S))
	{
	    camera.translate(0f, -10f * (1 + delta));
	}
	
	if(Gdx.input.isKeyPressed(Keys.Q))
	{
	    camera.translate(-10f * (1 + delta), 0f);
	}
	else if(Gdx.input.isKeyPressed(Keys.D))
	{
	    camera.translate(10f * (1 + delta), 0f);
	}
	
	if(Gdx.input.getX() < 100f) // left
	{
	    camera.translate(-10f * (1 + delta), 0f);
	}
	else if(Gdx.input.getX() > Gdx.graphics.getWidth() -100f) // right
	{
	    camera.translate(10f * (1 + delta), 0f);
	}
	if(Gdx.input.getY() < 100f) // bottom
	{
	    camera.translate(0f, 10f * (1 + delta));
	}
	else if(Gdx.input.getY() > Gdx.graphics.getHeight() -100f) // top
	{
	    camera.translate(0f, -10f * (1 + delta));
	}
	
	camera.update();
    }

    public OrthographicCamera getCamera()
    {
	return camera;
    }

    public void setCamera(OrthographicCamera camera)
    {
	this.camera = camera;
    }

    @Override
    public void preRender()
    {
	
    }

}
