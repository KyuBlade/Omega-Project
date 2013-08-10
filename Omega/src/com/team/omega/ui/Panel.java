package com.team.omega.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Pools;

public class Panel extends Table
{
    
    private ClickListener clickListener;
    private boolean isChecked;
    
    private PanelStyle style;

    public Panel(Skin skin)
    {
	this(skin, "default");
    }

    public Panel(Skin skin, String styleName)
    {
	setStyle(skin.get(styleName, PanelStyle.class));
	initialize();
    }

    private void initialize()
    {
	setWidth(getPrefWidth());
	setHeight(getPrefHeight());
	
	if (!isAnimate())
	    return;

	setTouchable(Touchable.enabled);
	addListener(clickListener = new ClickListener() {

	    public void clicked(InputEvent event, float x, float y)
	    {
		Gdx.app.debug("PanelListener", "Set isChecked : " + isChecked);
		setChecked(!isChecked);
	    }
	});
    }

    public void setChecked(boolean isChecked)
    {
	/*if (this.isChecked == isChecked)
	    return;*/

	this.isChecked = isChecked;
	
	ChangeEvent changeEvent = Pools.obtain(ChangeEvent.class);
	if (fire(changeEvent))
	    this.isChecked = !isChecked;
	
	Pools.free(changeEvent);
	Gdx.app.debug("PanelListener", "Set isChecked : " + this.isChecked);
    }

    public boolean isAnimate()
    {
	return getTouchable().equals(Touchable.enabled);
	   
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
	
	/** Optional */
	public Drawable backgroundOver;

	public PanelStyle()
	{
	}

	public PanelStyle(Drawable background, Drawable backgroundOver)
	{
	    this.background = background;
	}

	public PanelStyle(PanelStyle style)
	{
	    this.background = style.background;
	    this.backgroundOver = style.backgroundOver;
	}
	
    }

}
