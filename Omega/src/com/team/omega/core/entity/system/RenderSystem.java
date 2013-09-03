package com.team.omega.core.entity.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Pool;
import com.esotericsoftware.spine.Skeleton;
import com.team.omega.core.entity.component.render.CameraComponent;
import com.team.omega.core.entity.component.render.MapRendererComponent;
import com.team.omega.core.entity.component.render.SkeletonComponent;
import com.team.omega.core.renderer.IsometricMapRenderer;


public class RenderSystem extends EntitySystem
{

    @Mapper
    private ComponentMapper<CameraComponent> camMapper;
    
    @Mapper
    private ComponentMapper<MapRendererComponent> mapMapper;
    
    @Mapper
    private ComponentMapper<SkeletonComponent> skelMapper;
    
    private OrthographicCamera cam;
    private IsometricMapRenderer map;
    private Skeleton skel;
    
    public RenderSystem()
    {
	super(Aspect.getAspectForOne(CameraComponent.class, SkeletonComponent.class, MapRendererComponent.class));
    }

    @Override
    public void initialize()
    {
	camMapper = world.getMapper(CameraComponent.class);
	mapMapper = world.getMapper(MapRendererComponent.class);
	skelMapper = world.getMapper(SkeletonComponent.class);
    }
    
    @Override
    protected boolean checkProcessing()
    {
	return true;
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entities)
    {
	for(int i = 0; i < entities.size(); i++)
	{
	    Entity _e = entities.get(i);
	}
	
	if(cam != null)
	{
	    cam.update();
	    map.setView(cam);
	}
	if(map != null)
	    map.render();
    }

    @Override
    protected void inserted(Entity e)
    {
	if (camMapper.has(e))
	    cam = camMapper.get(e).getCamera();
	else if (mapMapper.has(e))
	    map = mapMapper.get(e).getMapRenderer();
	else if (skelMapper.has(e))
	    skel = skelMapper.get(e).getSkeleton();
    }
    
    @Override
    protected void removed(Entity e)
    {
	if (camMapper.has(e))
	    cam = null;
	else if (mapMapper.has(e))
	    map = null;
	else if (skelMapper.has(e))
	    skel = null;
    }

}
