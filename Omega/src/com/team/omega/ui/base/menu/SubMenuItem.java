package com.team.omega.ui.base.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class SubMenuItem extends MenuItem
{
    
    private ContextMenu menu;
    
    public SubMenuItem(Skin skin)
    {
	this("", skin);
    }
    
    public SubMenuItem(String text, Skin skin)
    {
	this(text, skin, "default-sub");
    }
    
    public SubMenuItem(String text, Skin skin, String styleName)
    {
	super(skin, styleName);
	
	if(style.icon != null)
	    this.iconCell.setWidget(new Image(style.icon));
	if(style.sub != null)
	    this.subCell.setWidget(new Image(style.sub));
	
	this.textCell.setWidget(new Label(text, skin));
    }
    
    public void setContextMenu(final ContextMenu menu, final Stage stage)
    {
	this.menu = menu;
	
	menu.setVisible(false);
	stage.addActor(menu);
	this.addListener(new InputListener()
	{
	    
	    @Override
	    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
	    {
		Actor _actor = event.getListenerActor();
		((ContextMenu) _actor.getParent()).closeAllMenu();
		menu.setPosition(_actor.getX() + _actor.getWidth() + _actor.getParent().getX(), _actor.getY() + _actor.getParent().getY() + _actor.getHeight());
		menu.setVisible(true);
	    }
	    
	});
    }
    
    public ContextMenu getContextMenu()
    {
	return menu;
    }
    
}
