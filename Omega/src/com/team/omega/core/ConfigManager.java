package com.team.omega.core;

import com.badlogic.gdx.Gdx;


public class ConfigManager
{
    
    private float initialWidth;
    private float initialHeight;
    
    public ConfigManager()
    {
	this.initialWidth = Gdx.graphics.getWidth();
	this.initialHeight = Gdx.graphics.getHeight();
    }

    public float getInitialWidth()
    {
        return initialWidth;
    }
    
    public void setInitialWidth(float initialWidth)
    {
        this.initialWidth = initialWidth;
    }
    
    public float getInitialHeight()
    {
        return initialHeight;
    }
    
    public void setInitialHeight(float initialHeight)
    {
        this.initialHeight = initialHeight;
    }

}
