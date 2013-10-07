package com.team.omega.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.esotericsoftware.tablelayout.Value;
import com.team.omega.core.EditorCore;
import com.team.omega.core.project.resource.Resource;
import com.team.omega.ui.base.list.ListRow;


public class ResourceListRow extends ListRow
{
    
    private Image thumb;
    private String path;
    
    private Resource resource;
    
    public ResourceListRow(Resource resource, Skin skin)
    {
	super(skin);
	
	this.resource = resource;
	
	thumb = new Image(resource.getTextureRegion());
	path = EditorCore.getInstance().getExternalAssetManager().getAssetFileName(resource.getTextureRegion().getTexture());
	
	add(thumb).expandX();
	add(path).minWidth(Value.percentWidth(0.5f)).left();
    }
    
    public Resource getResource()
    {
	return resource;
    }

}
