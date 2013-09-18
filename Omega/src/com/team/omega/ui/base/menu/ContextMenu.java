package com.team.omega.ui.base.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.tablelayout.Cell;


public class ContextMenu extends Table
{
    
    private static Array<ContextMenu> menuList = new Array<>();
    
    public ContextMenu(Skin skin)
    {
	super(skin);
	
	menuList.add(this);
	this.top().left();
    }
    
    @Override
    public Cell<?> add(Actor actor)
    {
	this.row();
	this.setVisible(false);
	
	return super.add(actor);
    }
    
    @Override
    public void setVisible(boolean isVisible)
    {
	super.setVisible(isVisible);
    }
    
    public static void closeAllMenu()
    {
	for (Actor _actor : menuList)
	{
	    _actor.setVisible(false);
	}
    }

}
