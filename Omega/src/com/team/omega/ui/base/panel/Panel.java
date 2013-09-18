package com.team.omega.ui.base.panel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pools;

public class Panel extends Table
{
    
    private static int autoIncrement;
    
    private int id;
    private ClickListener clickListener;
    private PanelGroup panelGroup;
    private boolean isChecked, isDisabled;
    
    private PanelStyle style;

    public Panel(Skin skin)
    {
	this(skin, "default");
    }

    public Panel(Skin skin, String styleName)
    {
	super(skin);
	
	this.id = autoIncrement++;
	setStyle(skin.get(styleName, PanelStyle.class));
	initialize();
    }

    private void initialize()
    {
	setWidth(getPrefWidth());
	setHeight(getPrefHeight());

	addListener(clickListener = new ClickListener() {

	    public void clicked(InputEvent event, float x, float y)
	    {
		setChecked(!isChecked);
	    }
	});
    }

    public boolean isDisabled()
    {
	return isDisabled;
    }

    public void setDisabled(boolean isDisabled)
    {
	this.isDisabled = isDisabled;
    }

    public void toggle()
    {
	setChecked(!isChecked);
    }

    public boolean isChecked()
    {
	return isChecked;
    }

    public void setChecked(boolean isChecked)
    {
	if (this.isChecked == isChecked)
	    return;
	
	if (panelGroup != null && !panelGroup.canCheck(this, isChecked)) return;

	this.isChecked = isChecked;

	ChangeEvent changeEvent = Pools.obtain(ChangeEvent.class);
	if (fire(changeEvent))
	    this.isChecked = !isChecked;
	
	Pools.free(changeEvent);
    }

    public boolean isPressed()
    {
	return clickListener.isPressed();
    }

    public boolean isOver()
    {
	return clickListener.isOver();
    }

    public boolean isAnimate()
    {
	return getTouchable().equals(Touchable.enabled);
    }
    
    public void draw(SpriteBatch batch, float parentAlpha)
    {
	validate();

	Drawable _background = null;
	if(!isAnimate() && style.background != null)
	    _background = style.background;
	/*else if (isPressed() && !isDisabled)
	    _background = style.background == null ? style.backgroundChecked : style.background;*/
	else
	{
	    if (isDisabled && style.backgroundDisabled != null)
		_background = style.backgroundDisabled;
	    else if (isChecked && style.backgroundChecked != null)
		_background = (isOver() && style.backgroundOver != null) ? style.backgroundOver : style.backgroundChecked;
	    else if (isOver() && style.backgroundOver != null)
		_background = style.backgroundOver;
	    else
		_background = style.background;
	}

	if (_background != null)
	    setBackground(_background);
	
	super.draw(batch, parentAlpha);

	/*Array<Actor> children = getChildren();
	for (int i = 0; i < children.size; i++)
	    children.get(i).translate(offsetX, offsetY);
	super.draw(batch, parentAlpha);
	for (int i = 0; i < children.size; i++)
	    children.get(i).translate(-offsetX, -offsetY);*/
    }
    
    public PanelGroup getPanelGroup()
    {
	return panelGroup;
    }
    
    public void setPanelGroup(PanelGroup panelGroup)
    {
	this.panelGroup = panelGroup;
    }
    
    public int getId()
    {
	return id;
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
	
	/** Optional */
	public Drawable backgroundChecked;
	
	/** Optional */
	public Drawable backgroundDisabled;

	public PanelStyle()
	{
	}

	public PanelStyle(Drawable background)
	{
	    this(background, null, null, null);
	}
	
	public PanelStyle(Drawable background, Drawable backgroundDisabled)
	{
	    this(background, backgroundDisabled, null);
	}
	
	public PanelStyle(Drawable background, Drawable backgroundDisabled, Drawable backgroundChecked)
	{
	    this(background, backgroundDisabled, backgroundChecked, null);
	}
	
	public PanelStyle(Drawable background, Drawable backgroundDisabled, Drawable backgroundChecked, Drawable backgroundOver)
	{
	    this.background = background;
	    this.backgroundDisabled = backgroundDisabled;
	    this.backgroundChecked = backgroundChecked;
	    this.backgroundOver = backgroundOver;
	}

	public PanelStyle(PanelStyle style)
	{
	    this.background = style.background;
	    this.backgroundDisabled = style.backgroundDisabled;
	    this.backgroundChecked = style.backgroundChecked;
	    this.backgroundOver = style.backgroundOver;
	}
	
    }

}
