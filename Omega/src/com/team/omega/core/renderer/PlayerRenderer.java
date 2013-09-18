package com.team.omega.core.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.esotericsoftware.spine.SkeletonRendererDebug;


public class PlayerRenderer implements Renderer
{
    
    private SpriteBatch batch;
    
    private SkeletonRenderer renderer;
    private SkeletonRendererDebug debugRenderer;
    
    public PlayerRenderer(SpriteBatch batch)
    {
	this.batch = batch;
	
	renderer = new SkeletonRenderer();
	debugRenderer = new SkeletonRendererDebug();
    }
    
    @Override
    public void preRender()
    {
    }

    @Override
    public void render(float delta)
    {
	
    }
    
    public void render(Skeleton skeleton, float delta)
    {
	renderer.draw(batch, skeleton);
	debugRenderer.draw(skeleton);
    }

}
