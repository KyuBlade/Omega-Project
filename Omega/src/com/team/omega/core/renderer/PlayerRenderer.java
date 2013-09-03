package com.team.omega.core.renderer;

import com.artemis.ComponentMapper;
import com.artemis.annotations.Mapper;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.esotericsoftware.spine.SkeletonRendererDebug;
import com.team.omega.core.entity.component.render.SkeletonComponent;


public class PlayerRenderer implements Renderer
{

    private SpriteBatch batch;
    
    private SkeletonRenderer renderer;
    private SkeletonRendererDebug debugRenderer;
    
    @Mapper
    private ComponentMapper<SkeletonComponent> skeletonMap;
    
    public PlayerRenderer()
    {
	batch = new SpriteBatch();
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

}
