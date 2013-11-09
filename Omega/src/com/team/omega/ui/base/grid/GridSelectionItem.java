package com.team.omega.ui.base.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pools;
import com.team.omega.ui.base.SelectionMode;
import com.team.omega.ui.base.group.ActorGroup;
import com.team.omega.ui.base.group.Groupable;
import com.team.omega.ui.base.panel.Panel;


public class GridSelectionItem extends Panel implements Groupable
{
    
    private ActorGroup<GridSelectionItem> gridItemGroup;
    private boolean isDisabled, isChecked, isOver;
    private GridSelectionItemStyle style;
    private GridSelection<? extends GridSelectionItem> grid;
    
    public GridSelectionItem(Skin skin)
    {
	this(skin, "default");
    }
    
    public GridSelectionItem(Skin skin, String styleName)
    {
	super(skin, styleName);
	
	setTouchable(Touchable.enabled);
	
	setStyle(skin.get(styleName, GridSelectionItemStyle.class));
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
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);
        
        Drawable _background = null;
        if(style.background != null && isDisabled)
            _background = style.disabled;
        else if(style.checked != null && isChecked)
            _background = style.checked;
        else if(style.over != null && isOver)
            _background = style.over;
        else if(style.background != null)
            _background = style.background;
        
        setBackground(_background);
    }
    
    public void setStyle(GridSelectionItemStyle style)
    {
	this.style = style;
    }
    
    public static class GridSelectionItemStyle
    {
	
	public Drawable background, disabled, checked, over;
	
	public GridSelectionItemStyle()
	{
	    
	}
	
    }

    @Override
    public ActorGroup<? extends Groupable> getActorGroup()
    {
	return gridItemGroup;
    }
    
    @Override
    public void setActorGroup(ActorGroup<? extends Groupable> group)
    {
	gridItemGroup = (ActorGroup<GridSelectionItem>) group;
    }

    @Override
    public boolean isChecked()
    {
	return isChecked;
    }

    @Override
    public void setChecked(boolean isChecked)
    {	
        if (this.isChecked == isChecked)
            return;
        
        if (gridItemGroup != null && !gridItemGroup.canCheck(this, isChecked)) return;

        this.isChecked = isChecked;

        ChangeEvent changeEvent = Pools.obtain(ChangeEvent.class);
        if (fire(changeEvent))
            this.isChecked = !isChecked;
        
        Pools.free(changeEvent);
    }

    @Override
    public boolean isDisabled()
    {
	return isDisabled;
    }

    @Override
    public void setDisabled(boolean isDisabled)
    {
	this.isDisabled = isDisabled;
    }
    
    /**
     * Used internally
     * @param grid the parent grid
     */
    public <T> void setGrid(final GridSelection<? extends GridSelectionItem> grid)
    {
	this.grid = grid;
	
	addListener(new InputListener() {

	    @Override
	    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
	    {
		if (button != Buttons.LEFT || !grid.isSelectable())
		    return false;

		if (grid.getSelectionMode() == SelectionMode.MULTI)
		{
		    if (!gridItemGroup.isMultiSelection())
		    {
			if (gridItemGroup.isMultiOnCtrl())
			{
			    if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT))
				gridItemGroup.beginSelection();
			}
			else
			    gridItemGroup.beginSelection();
		    }
		    else
		    {
			if (gridItemGroup.isMultiOnCtrl())
			{
			    if (!Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && !Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT))
				gridItemGroup.endSelection();
			}
		    }
		}
		
		if(gridItemGroup.isMultiSelection() && isChecked())
		    gridItemGroup.uncheck(GridSelectionItem.this);
		else
		    gridItemGroup.setChecked(GridSelectionItem.this);
		
		return true;
	    }
	    
	    @Override
	    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
	    {
	        isOver = true;
	    }
	    @Override
	    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
	    {
	        isOver = false;
	    }

	});
    }
    
}
