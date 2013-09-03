package com.team.omega.core.entity.component.transform;

import com.artemis.Component;

public class RotationComponent extends Component
{

    private float rotation;

    public RotationComponent()
    {
	
    }
    
    public RotationComponent(float rotation)
    {
	this.rotation = rotation;
    }
    
    public float getRotation()
    {
	return rotation;
    }

    public void setRotation(float rotation)
    {
	this.rotation = rotation;
    }

    public void addRotation(float angle)
    {
	rotation = (rotation + angle) % 360;
    }

    public float getRotationAsRadians()
    {
	return (float) Math.toRadians(rotation);
    }

}
