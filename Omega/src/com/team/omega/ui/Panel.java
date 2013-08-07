package com.team.omega.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

public class Panel extends Table
{

    private PanelStyle style;
    private float offsetX;
    private float offsetY;

    public Panel(Skin skin)
    {
	this(skin, "default");
    }

    public Panel(Skin skin, String styleName)
    {
	super(skin);
	initialize();
	setStyle(skin.get(styleName, PanelStyle.class));
	setWidth(getPrefWidth());
	setHeight(getPrefHeight());
    }

    public Panel(Actor child, Skin skin, String styleName)
    {
	this(child, skin.get(styleName, PanelStyle.class));
    }

    public Panel(Actor child, PanelStyle style)
    {
	initialize();
	add(child);
	setStyle(style);
	setWidth(getPrefWidth());
	setHeight(getPrefHeight());
    }

    public Panel(PanelStyle style)
    {
	initialize();
	setStyle(style);
	setWidth(getPrefWidth());
	setHeight(getPrefHeight());
    }
    
    public Panel()
    {
	initialize();
    }

    private void initialize()
    {	
	setTouchable(Touchable.enabled);
    }

    public Panel(Drawable background)
    {
	this(new PanelStyle(background));
    }

    public Panel(Actor child, Skin skin)
    {
	this(child, skin.get(PanelStyle.class));
    }

    public void setStyle(PanelStyle style)
    {
	if (style == null)
	    throw new IllegalArgumentException("style cannot be null.");
	this.style = style;
	
	invalidateHierarchy();
    }

    public PanelStyle getStyle()
    {
	return style;
    }

    public void draw(SpriteBatch batch, float parentAlpha)
    {
	validate();
	
	Drawable _background = style.background;
	
	if (_background != null)
	{
	    Color color = getColor();
	    batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
	    _background.draw(batch, getX(), getY(), getWidth(), getHeight());
	}

	Array<Actor> children = getChildren();
	for (int i = 0; i < children.size; i++)
	    children.get(i).translate(offsetX, offsetY);
	super.draw(batch, parentAlpha);
	for (int i = 0; i < children.size; i++)
	    children.get(i).translate(-offsetX, -offsetY);
    }

    protected void drawBackground(SpriteBatch batch, float parentAlpha)
    {
    }

    public float getPrefWidth()
    {
	float width = super.getPrefWidth();
	
	if (style.background != null)
	    width = Math.max(width, style.background.getMinWidth());
	
	return width;
    }

    public float getPrefHeight()
    {
	float height = super.getPrefHeight();
	
	if (style.background != null)
	    height = Math.max(height, style.background.getMinHeight());
	
	return height;
    }

    public float getMinWidth()
    {
	return getPrefWidth();
    }

    public float getMinHeight()
    {
	return getPrefHeight();
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
