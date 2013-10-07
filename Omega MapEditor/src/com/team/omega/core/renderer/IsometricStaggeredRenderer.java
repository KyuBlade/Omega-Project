package com.team.omega.core.renderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;


public class IsometricStaggeredRenderer implements MapRenderer, Disposable
{

    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;
    private Rectangle viewBounds; 
    
    public IsometricStaggeredRenderer()
    {
	
    }
    
    @Override
    public void setView(OrthographicCamera camera)
    {
	spriteBatch.setProjectionMatrix(camera.combined);
	float width = camera.viewportWidth * camera.zoom;
	float height = camera.viewportHeight * camera.zoom;
	viewBounds.set(camera.position.x - width / 2, camera.position.y - height / 2, width, height);
    }

    @Override
    public void setView(Matrix4 projectionMatrix, float viewboundsX, float viewboundsY, float viewboundsWidth, float viewboundsHeight)
    {
	spriteBatch.setProjectionMatrix(projectionMatrix);
	viewBounds.set(viewboundsX, viewboundsY, viewboundsWidth, viewboundsHeight);
    }

    @Override
    public void render()
    {
    }

    @Override
    public void render(int[] layers)
    {
    }

    @Override
    public void dispose()
    {
	spriteBatch.dispose();
    }

}
