package com.team.omega.core.entity.component.transform;

import com.artemis.Component;

public class ScaleComponent extends Component
{

    private float xScale;
    private float yScale;

    public ScaleComponent()
    {

    }

    public ScaleComponent(float xScale, float yScale)
    {
	this.xScale = xScale;
	this.yScale = yScale;
    }

    public float getScaleX()
    {
	return xScale;
    }

    public void setScaleX(float xScale)
    {
	this.xScale = xScale;
    }
    
    public void scaleX(float scale)
    {
	xScale *= scale;
    }

    public float getScaleY()
    {
	return yScale;
    }

    public void setScaleY(float yScale)
    {
	this.yScale = yScale;
    }
    
    public void scaleY(float scale)
    {
	yScale *= scale;
    }
    
    public void setScale(float xScale, float yScale)
    {
	this.xScale = xScale;
	this.yScale = yScale;
    }
    
    public void scale(float scale)
    {
	this.xScale = scale;
	this.yScale = scale;
    }

}
