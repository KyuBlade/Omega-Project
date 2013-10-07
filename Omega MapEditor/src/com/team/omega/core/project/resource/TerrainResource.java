package com.team.omega.core.project.resource;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class TerrainResource implements Resource
{

    private TextureRegion texture;
    
    public TerrainResource(Texture texture)
    {
	this.texture = new TextureRegion(texture);
    }

    @Override
    public TextureRegion getTextureRegion()
    {
	return texture;
    }

    
}
