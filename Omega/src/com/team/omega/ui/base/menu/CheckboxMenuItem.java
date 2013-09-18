package com.team.omega.ui.base.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class CheckboxMenuItem extends MenuItem
{
    
    private Image checkImage;
    
    public CheckboxMenuItem(String text ,Skin skin)
    {
	this(text, "", skin, "default-checkbox");
    }
    
    public CheckboxMenuItem(String text, String shortcut ,Skin skin)
    {
	this(text, shortcut, skin, "default-checkbox");
    }
    
    public CheckboxMenuItem(String text, String shortcut, Skin skin, String styleName)
    {
	super(skin, styleName);
	
	if(style.icon != null)
	{
	    checkImage = new Image(style.icon);
	    this.iconCell.setWidget(checkImage);
	}
	if(!shortcut.isEmpty())
	    this.shortcutCell.setWidget(new Label(shortcut, skin));
	this.textCell.setWidget(new Label(text, skin));
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {
	if(style.icon != null)
	    checkImage.setVisible(rules());
	    
	super.draw(batch, parentAlpha);
    }
    
    /**
     * Method to override to define when menu should be checked/unchecked
     */
    public boolean rules()
    {
	return false;
    }
    
}
