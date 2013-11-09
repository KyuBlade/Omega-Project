package com.team.omega.ui.base.list;

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



public class ListRow extends Panel implements Groupable
{
    
    private ActorGroup<ListRow> listRowGroup;
    private boolean isDisabled, isChecked, isOver;
    private AdvancedList<? extends ListRow> list;
    
    private Skin skin;
    private ListRowStyle style;
    
    public ListRow(Skin skin)
    {
	this(skin, "default");
    }
    
    public ListRow(Skin skin, String styleName)
    {
	super(skin);
	
	this.skin = skin;
	setTouchable(Touchable.enabled);
	
	setStyle(skin.get(styleName, ListRowStyle.class));
    }
    
    @Override
    public float getPrefWidth()
    {
	return getWidth();
    }
    
    public void setStyle(ListRowStyle style)
    {
	this.style = style;
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
    
    public static class ListRowStyle
    {
	
	public Drawable background, disabled, checked, over;
	
	public ListRowStyle()
	{
	    
	}
	
    }
    
    @Override
    public ActorGroup<? extends Groupable> getActorGroup()
    {
	return listRowGroup;
    }
    
    @Override
    public void setActorGroup(ActorGroup<? extends Groupable> group)
    {
	listRowGroup = (ActorGroup<ListRow>) group;
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
        
        if (listRowGroup != null && !listRowGroup.canCheck(this, isChecked)) return;

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
    
    public void setList(final AdvancedList<? extends ListRow> list)
    {
	this.list = list;
	
	addListener(new InputListener() {

	    @Override
	    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
	    {
		if (button != Buttons.LEFT || !list.isSelectable())
		    return false;

		if (list.getSelectionMode() == SelectionMode.MULTI)
		{
		    if (!listRowGroup.isMultiSelection())
		    {
			if (listRowGroup.isMultiOnCtrl())
			{
			    if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT))
				listRowGroup.beginSelection();
			}
			else
			    listRowGroup.beginSelection();
		    }
		    else
		    {
			if (listRowGroup.isMultiOnCtrl())
			{
			    if (!Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && !Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT))
				listRowGroup.endSelection();
			}
		    }
		}
		
		if(listRowGroup.isMultiSelection() && ListRow.this.isChecked())
		    listRowGroup.uncheck(ListRow.this);
		else
		    listRowGroup.setChecked(ListRow.this);
		
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
