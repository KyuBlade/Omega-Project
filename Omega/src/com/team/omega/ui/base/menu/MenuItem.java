package com.team.omega.ui.base.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.esotericsoftware.tablelayout.Cell;


public class MenuItem extends Button
{
    
    protected MenuItemStyle style;
    
    protected Cell<Actor> iconCell;
    protected Cell<Actor> textCell;
    protected Cell<Actor> shortcutCell;
    protected Cell<Actor> subCell;
    
    public MenuItem(Skin skin)
    {
	this(skin, "default");
    }
    
    @SuppressWarnings("unchecked")
    public MenuItem(Skin skin, String styleName)
    {
	super(skin);
	
	setStyle(skin.get(styleName, MenuItemStyle.class));
	this.defaults().minHeight(10f);
	iconCell = this.add().minWidth(16f);
	textCell = this.add().minWidth(150f).padLeft(5f);
	shortcutCell = this.add().minWidth(50f);
	subCell = this.add().minWidth(8f);
    }
    
    public void setStyle(MenuItemStyle style)
    {
	this.style = style;
    }

    public static class MenuItemStyle
    {

	/** Optional. */
	public Drawable up, down, over, disabled, icon, sub;

	public MenuItemStyle()
	{

	}

	public MenuItemStyle(Drawable up, Drawable down, Drawable over, Drawable disabled, Drawable icon, Drawable sub)
	{
	    this.up = up;
	    this.down = down;
	    this.over = over;
	    this.disabled = disabled;
	    this.icon = icon;
	    this.sub = sub;
	}

	public MenuItemStyle(MenuItemStyle style)
	{
	    this.up = style.up;
	    this.down = style.down;
	    this.over = style.over;
	    this.disabled = style.disabled;
	    this.icon = style.icon;
	    this.sub = style.sub;
	}

    }
    
}
