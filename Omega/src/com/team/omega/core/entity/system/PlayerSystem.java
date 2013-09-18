package com.team.omega.core.entity.system;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.team.omega.core.entity.component.render.SkeletonComponent;
import com.team.omega.core.entity.manager.PlayerManager;


public class PlayerSystem extends EntityProcessingSystem
{

    private PlayerManager playerMgr;
    
    @SuppressWarnings("unchecked")
    public PlayerSystem()
    {
	super(Aspect.getAspectForAll(SkeletonComponent.class));
    }

    @Override
    public void initialize()
    {
	playerMgr = world.getManager(PlayerManager.class);
    }
    
    @Override
    protected void process(Entity e)
    {
	
    }
    
    @Override
    protected void inserted(Entity e)
    {
	Gdx.app.debug("PlayerSystem", "Added entity");
	playerMgr.add(e);
    }
    
    @Override
    protected void removed(Entity e)
    {
	Gdx.app.debug("PlayerSystem", "Removed entity");
	playerMgr.remove(e);
    }


}
