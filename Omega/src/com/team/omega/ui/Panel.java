package com.team.omega.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Panel extends Table
{
    
    private PanelStyle style;

    public Panel(Skin skin)
    {
	this(skin, "default");
    }

    public Panel(Skin skin, String styleName)
    {
	setStyle(skin.get(styleName, PanelStyle.class));
	setWidth(getPrefWidth());
	setHeight(getPrefHeight());
    }

    public void setStyle(PanelStyle style)
    {
	if (style == null)
	    throw new IllegalArgumentException("style cannot be null");
	
	this.style = style;
	this.setBackground(style.background);
	
	invalidateHierarchy();
    }

    public PanelStyle getStyle()
    {
	return style;
    }

    
    @Override
    public void draw (SpriteBatch batch, float parentAlpha)
    {
	/*if(style.background != null)
	    drawBackground(batch, parentAlpha);*/
	
	super.draw(batch, parentAlpha);
}
    
    protected void drawBackground (SpriteBatch batch, float parentAlpha)
    {
	Gdx.app.debug("Bg", "x : " + getX() + " - y : " + getY() 
		+ " - width : " + getWidth() + " - height : " + getHeight());
	
	style.background.draw(batch, getX(), getY(), getWidth(), getHeight());
    }

    static public class PanelStyle
    {

	/** Optional. */
	public Drawable background;

	public PanelStyle()
	{
	}

	public PanelStyle(Drawable background)
	{
	    this.background = background;
	}

	public PanelStyle(PanelStyle style)
	{
	    this.background = style.background;
	}
	
    }

}
