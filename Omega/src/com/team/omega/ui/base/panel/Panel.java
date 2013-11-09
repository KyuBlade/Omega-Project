package com.team.omega.ui.base.panel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Panel extends Table
{
    
    private ClickListener clickListener;
    
    private PanelStyle style;

    public Panel(Skin skin)
    {
	this(skin, "default");
    }

    public Panel(Skin skin, String styleName)
    {
	super(skin);
	
	setStyle(skin.get(styleName, PanelStyle.class));
    }
    
    public void draw(SpriteBatch batch, float parentAlpha)
    {
	validate();
	
	super.draw(batch, parentAlpha);
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
