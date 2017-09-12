package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.team.omega.core.renderer.GridRenderer;
import com.team.omega.util.IsometricUtil;




public class EditorScreen extends BaseScreen
{
    
    private boolean isGridDisplay;
    
    private GridRenderer gridRenderer;
    private OrthographicCamera cam;
    
    private Vector2 camMove = new Vector2();

    public EditorScreen(ScreenManager screenManager)
    {
	super(screenManager, 1);
	
	cam = new OrthographicCamera();
	cam.setToOrtho(true);
	cam.position.x = 0f;
	cam.position.y = 0f;
	gridRenderer = new GridRenderer(cam);
	
	stage.addListener(new InputListener() {
	    
	    @Override
	    public boolean scrolled(InputEvent event, float x, float y, int amount)
	    {
	        if(event.getStage().getScrollFocus() != null)
	            return false;
	        
	        zoom(amount);
	        
	        return true;
	    }
	    
	});
    }
    
    public boolean isGridDisplay()
    {
	return isGridDisplay;
    }

    public void setGridDisplay(boolean isGridDisplay)
    {
	this.isGridDisplay = isGridDisplay;
    }
    
    @Override
    public void render(float delta)
    {
	if(Gdx.input.isKeyPressed(Keys.Q))
	    camMove.x = -1024f;
	if(Gdx.input.isKeyPressed(Keys.D))
	    camMove.x = 1024f;
	if(Gdx.input.isKeyPressed(Keys.Z))
	    camMove.y = -1024f;
	if(Gdx.input.isKeyPressed(Keys.S))
	    camMove.y = 1024f;
	
	camMove.scl(cam.zoom * delta);
	cam.translate(camMove);
	cam.update();
	cam.apply(Gdx.gl10);
	
	//if(isGridDisplay)
	    gridRenderer.render(delta);
    }
    
    public void zoom(float zoom)
    {
	float _smoothScrool = (float) zoom / 2; 
	if(cam.zoom + _smoothScrool < 0)
	    return;

	cam.zoom += _smoothScrool + 0.1;
    }

    public void click(float x, float y)
    {
	Vector3 _vect = new Vector3(x, y, 0f);
	cam.unproject(_vect);
	Gdx.app.debug("World", "" + _vect);
	//IsometricUtil.worldToIso(_vect, cam.combined).getTranslation(_vect);
	Gdx.app.debug("Iso", "" + _vect);
    }
    
}
