package com.team.omega.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ProgressBar extends Widget
{

    private ProgressBarStyle style;
    
    private float percent;

    public ProgressBar(Skin skin)
    {
	this(skin, "default");
    }
    
    public ProgressBar(Skin skin, String styleName)
    {
	setStyle(skin.get(styleName, ProgressBarStyle.class));
    }

    public void setStyle(ProgressBarStyle style)
    {
	if (style == null)
	    throw new IllegalArgumentException("style cannot be null.");

	this.style = style;

	if (style.background == null)
	    throw new NullPointerException("background cannot be null");
	if (style.progressBackground == null)
	    throw new NullPointerException("progressBackground cannot be null");

	invalidateHierarchy();
    }

    public ProgressBarStyle getStyle()
    {
	return style;
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {
	validate();
	
	Color color = getColor();

	batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

	style.background.draw(batch, getX(), getY(), getWidth(), getHeight());

	if(percent > 0f)
	    style.progressBackground.draw(batch, getX() + style.background.getLeftWidth(), getY() + style.background.getBottomHeight(), percent * (getWidth() - style.background.getRightWidth() - style.background.getLeftWidth()), getHeight() - style.background.getTopHeight() - style.background.getBottomHeight());
    }

    public void setPercent(float percent)
    {
	this.percent = percent;
    }
    
    public float getPercent()
    {
	return percent;
    }

    /** The style for a {@link ProgressBar} */
    static public class ProgressBarStyle
    {

	private Drawable background;
	
	private Drawable progressBackground;
	
	public ProgressBarStyle()
	{
	}

	public ProgressBarStyle(Drawable background, Drawable progressBackground)
	{
	    this.background = background;
	    this.progressBackground = progressBackground;
	}

	public ProgressBarStyle(ProgressBarStyle style)
	{
	    this.background = style.background;
	    this.progressBackground = style.progressBackground;
	}
    }

}
