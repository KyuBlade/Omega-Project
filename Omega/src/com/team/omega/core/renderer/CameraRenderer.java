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
    public void render(float tpf)
    {
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
