package com.team.omega.core.project.resource;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class WallResource implements Resource
{

    private TextureRegion texture;
    
    public WallResource(Texture texture)
    {
	this.texture = new TextureRegion(texture);
    }

    @Override
    public TextureRegion getTextureRegion()
    {
	return texture;
    }
    
}
