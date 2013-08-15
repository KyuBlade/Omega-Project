package com.team.omega.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ProgressBar extends Slider
{

    private ProgressBarStyle style;

    public ProgressBar(Skin skin)
    {
	this(skin, "default");
    }
    
    public ProgressBar(Skin skin, String styleName)
    {
	super(0, 100, 1, false, skin);

	setStyle(skin.get(styleName, ProgressBarStyle.class));
    }

    public void setStyle(ProgressBarStyle style)
    {
	if (style == null)
	    throw new IllegalArgumentException("style cannot be null.");

	this.style = style;

	if (style.background == null)
	    throw new NullPointerException("background cannot be null");
	if (style.knobBefore == null)
	    throw new NullPointerException("knobBefore cannot be null");

	invalidateHierarchy();
    }

    public ProgressBarStyle getStyle()
    {
	return style;
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {
	final Drawable bg = style.background;
	final Drawable knobBefore = style.knobBefore;

	Color color = getColor();
	float x = getX();
	float y = getY();
	float width = getWidth();
	float height = getHeight();
	float percent = this.getValue() / 100;

	batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

	bg.draw(batch, x, y + bg.getMinHeight(), width, height + bg.getTopHeight() + bg.getBottomHeight());

	if (knobBefore != null)
	{
	    if(percent > 0f)
		knobBefore.draw(batch, x + bg.getLeftWidth(), y + bg.getMinHeight() + bg.getBottomHeight(), percent * (width - bg.getRightWidth() - bg.getLeftWidth()), height);
	}
    }

    @Override
    public float getPrefWidth()
    {
	return getWidth();
    }
    
    @Override
    public float getPrefHeight()
    {
	return getHeight();
    }

    /** The style for a {@link ProgressBar} */
    static public class ProgressBarStyle extends SliderStyle
    {

	public ProgressBarStyle()
	{
	}

	public ProgressBarStyle(Drawable background, Drawable progressBackground)
	{
	    this.background = background;
	    this.knobBefore = progressBackground;
	}

	public ProgressBarStyle(ProgressBarStyle style)
	{

	    super(style);
	}
    }

}
