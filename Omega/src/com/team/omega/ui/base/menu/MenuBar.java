package com.team.omega.ui.base.menu;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.esotericsoftware.tablelayout.Cell;


public class MenuBar extends Table
{

    @SuppressWarnings("unused")
    private MenuBarStyle style;
    
    private Map<Button, ContextMenu> menuBind = new HashMap<>();
    private boolean isOpen;
    private boolean isInitialized;
    
    public MenuBar(Skin skin)
    {
	this(skin, "default");
    }
    
    public MenuBar(Skin skin, String styleName)
    {
	super(skin);
	
	setStyle(skin.get(styleName, MenuBarStyle.class));
    }
    
    public void setStyle(MenuBarStyle style)
    {
	this.style = style;
	
	this.setBackground(style.background);
	this.left();
    }
    
    public Cell<?> addContextMenu(final Button actor, final ContextMenu menu, final Stage stage)
    {
	stage.addActor(menu);
	menuBind.put(actor, menu);

	if (!isInitialized)
	{
	    isInitialized = true;
	    stage.addListener(new InputListener() {

		@Override
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
		{
		    Actor _target = event.getTarget();
		    if (_target instanceof ContextMenu || _target instanceof SubMenuItem)
			return false;
		    else if (_target instanceof Label)
		    {
			Actor _parent = _target.getParent();
			if (_parent instanceof TextButton)
			{
			    if (menuBind.containsKey(_parent))
				return false;
			}
			else if (_parent instanceof SubMenuItem)
			    return false;
		    }

		    closeAllMenu();
		    isOpen = false;

		    return true;
		}

	    });
	}
	
	actor.addListener(new InputListener() {
	    
	    @Override
	    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
	    {
		closeAllMenu();
		isOpen = !isOpen;
		menu.setPosition(actor.getX(), stage.getHeight() - actor.getY() - actor.getHeight());
		menu.setVisible(isOpen);
		
		return true;
	    }
	    
	    @Override
	    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
	    {
		if(!isOpen)
		    return;
		
		closeAllMenu();
		menu.setVisible(true);
		menu.setPosition(actor.getX(), stage.getHeight() - actor.getY() - actor.getHeight());
	    }
	    
	});
	
	return super.add(actor).minWidth(actor.getWidth());
    }
    
    private void closeAllMenu()
    {
	for(ContextMenu _menu : menuBind.values())
	    _menu.setVisible(false);
    }
    
    public static class MenuBarStyle
    {
	
	public Drawable background;
	
	public MenuBarStyle()
	{

	}

	public MenuBarStyle(Drawable background)
	{
	    this.background = background;
	}

	public MenuBarStyle(MenuBarStyle style)
	{
	    this.background = style.background;
	}
	
    }
    
}
