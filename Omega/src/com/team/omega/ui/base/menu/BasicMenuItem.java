package com.team.omega.ui.base.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class BasicMenuItem extends MenuItem
{
    
    public BasicMenuItem(String text, Skin skin)
    {
	this(text, "", skin, "default");
    }
    
    public BasicMenuItem(String text, String shortcut, Skin skin)
    {
	this(text, shortcut, skin, "default");
    }
    
    public BasicMenuItem(String text, String shortcut, Skin skin, String styleName)
    {
	super(skin, styleName);
	
	if(style.icon != null)
	    this.iconCell.setWidget(new Image(style.icon));
	
	if(!shortcut.isEmpty())
	    this.shortcutCell.setWidget(new Label(shortcut, skin));
	
	this.textCell.setWidget(new Label(text, skin));
    }
    
}
