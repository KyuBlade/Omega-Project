package com.team.omega.ui.base.list;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;



public abstract class ListRow extends Table
{

    private boolean isSelected;
    private ListRowStyle style;
    
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
	this.style = style;
	
	setBackground(style.unselectedBackground);
    }
    
    public void setIsSelected(boolean isSelected)
    {
	this.isSelected = isSelected;
	
	if(style == null)
	    return;
	
	if(isSelected)
	    setBackground(style.selectedBackground);
	else
	    setBackground(style.unselectedBackground);
    }
    
    public boolean isSelected()
    {
	return isSelected;
    }
    
    public static class ListRowStyle
    {
	
	public Drawable selectedBackground, unselectedBackground;
	
	public ListRowStyle()
	{
	    
	}
	
    }
    
}
