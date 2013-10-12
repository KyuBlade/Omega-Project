package com.team.omega.ui.base.list;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.team.omega.ui.base.panel.Panel;



public abstract class ListRow extends Panel
{
    
    public ListRow(Skin skin)
    {
	this(skin, "default");
    }
    
    public ListRow(Skin skin, String styleName)
    {
	super(skin);
	
	setTouchable(Touchable.enabled);
	setStyle(skin.get(styleName, ListRowStyle.class));
    }
    
    public void setStyle(ListRowStyle style)
    {
	super.setStyle(new PanelStyle(style.background, style.disabled, style.selected, style.over));
    }
    
    public static class ListRowStyle
    {
	
	public Drawable background, disabled, selected, over;
	
	public ListRowStyle()
	{
	    
	}
	
    }
    
}
