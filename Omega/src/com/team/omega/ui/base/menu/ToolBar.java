package com.team.omega.ui.base.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


public class ToolBar extends Table
{

    @SuppressWarnings("unused")
    private ToolBarStyle style;
    
    public ToolBar(Skin skin)
    {
	this(skin, "default");
    }
    
    public ToolBar(Skin skin, String styleName)
    {
	super(skin);
	
	setStyle(skin.get(styleName, ToolBarStyle.class));
    }
    
    public void setStyle(ToolBarStyle style)
    {
	this.style = style;
	
	this.setBackground(style.background);
	this.left();
    }
    
    public static class ToolBarStyle
    {
	
	public Drawable background;
	
	public ToolBarStyle()
	{

	}

	public ToolBarStyle(Drawable background)
	{
	    this.background = background;
	}

	public ToolBarStyle(ToolBarStyle style)
	{
	    this.background = style.background;
	}
	
    }
    
}
