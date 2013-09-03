package com.team.omega.core.entity.component;

import com.artemis.Component;


public class VelocityComponent extends Component
{
    
    private float velocity;

    public VelocityComponent()
    {
    }
    
    public VelocityComponent(float velocity)
    {
            this.velocity = velocity;
    }
    
    public float getVelocity()
    {
            return velocity;
    }
    
    public void setVelocity(float velocity)
    {
            this.velocity = velocity;
    }
    
    public void addVelocity(float velocity)
    {
            this.velocity += velocity;
    }
    
}
