package com.team.omega.ui.base.grid;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.team.omega.ui.base.panel.Panel;


public class GridSelectionItem extends Panel
{
    
    public GridSelectionItem(Skin skin)
    {
	this(skin, "default");
    }
    
    public GridSelectionItem(Skin skin, String styleName)
    {
	super(skin, styleName);
	
	setTouchable(Touchable.enabled);
	setStyle(skin.get(styleName, GridSelectionItemStyle.class));
	
	debugTable();
    }
    
    public void setStyle(GridSelectionItemStyle style)
    {
	super.setStyle(new PanelStyle(style.background, style.disabled, style.selected, style.over));
    }
    
    public static class GridSelectionItemStyle
    {
	
	public Drawable background, disabled, selected, over;
	
	public GridSelectionItemStyle()
	{
	    
	}
	
    }
    
}
