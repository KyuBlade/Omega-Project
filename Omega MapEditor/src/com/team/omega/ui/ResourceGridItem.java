package com.team.omega.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.team.omega.ui.base.grid.GridSelectionItem;


public class ResourceGridItem extends GridSelectionItem
{

    private Image thumb;
    
    public ResourceGridItem(Skin skin, TextureRegion texture)
    {
	super(skin);
	
	thumb = new Image(texture);
	add(thumb);
    }

}
