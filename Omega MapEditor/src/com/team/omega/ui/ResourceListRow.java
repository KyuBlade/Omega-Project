package com.team.omega.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.esotericsoftware.tablelayout.Value;
import com.team.omega.core.EditorCore;
import com.team.omega.core.project.resource.Resource;
import com.team.omega.ui.base.list.ListRow;


public class ResourceListRow extends ListRow
{
    
    private Image thumb;
    private String path;
    
    private float thumbMaxSize = 200f;
    
    private Resource resource;
    
    public ResourceListRow(final Resource resource, Skin skin)
    {
	super(skin);
	
	this.resource = resource;
	
	thumb = new Image(resource.getTextureRegion())
	{
	    
	    @Override
	    public float getWidth()
	    {
		if(thumbMaxSize <= resource.getTextureRegion().getRegionWidth())
		    return thumbMaxSize;
		else
		    return resource.getTextureRegion().getRegionWidth();
	    }
	    
	    @Override
	    public float getHeight()
	    {
		if(thumbMaxSize <= resource.getTextureRegion().getRegionHeight())
		    return thumbMaxSize;
		else
		    return resource.getTextureRegion().getRegionHeight();
	    }
	    
	};
	path = EditorCore.getInstance().getExternalAssetManager().getAssetFileName(resource.getTextureRegion().getTexture());
	
	left();
	add(thumb).minWidth(thumbMaxSize).maxSize(thumbMaxSize);
	add(path).left();
    }
    
    public Resource getResource()
    {
	return resource;
    }

}
