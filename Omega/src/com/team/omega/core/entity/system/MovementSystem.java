package com.team.omega.core.entity.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.team.omega.core.entity.component.VelocityComponent;
import com.team.omega.core.entity.component.transform.PositionComponent;
import com.team.omega.core.entity.component.transform.RotationComponent;


public class MovementSystem extends EntityProcessingSystem
{

    @Mapper
    private ComponentMapper<PositionComponent> positionMapper;
    
    public MovementSystem()
    {
	super(Aspect.getAspectForAll(VelocityComponent.class, PositionComponent.class, RotationComponent.class));
    }

    @Override
    protected void process(Entity e)
    {
	PositionComponent _pos = positionMapper.get(e);
    }

}
