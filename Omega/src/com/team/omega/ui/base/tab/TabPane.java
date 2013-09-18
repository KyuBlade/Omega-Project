package com.team.omega.ui.base.tab;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;


public class TabPane extends Table
{
    
    private Table tabs;
    private Stack body;
    
    private ArrayMap<Tab, TabContainer> tabBind = new ArrayMap<>();
    
    private Tab currentTab;
    
    public TabPane(Skin skin)
    {
	this(skin, "default");
    }
    
    public TabPane(Skin skin, String styleName)
    {
	super(skin);
	
	tabs = new Table(skin);
	tabs.setBackground(skin.get(styleName, TabPaneStyle.class).background);
	body = new Stack();
	this.left().top();
	tabs.left();
	
	this.add(tabs).left().expandX().fillX();
	this.row();
	this.add(body).expand().fill();
    }
    
    public void addTab(Tab tab)
    {
	tabBind.put(tab, tab.getContainer());
	Array<Actor> _children = new Array<>(tabs.getChildren());
	_children.add(tab);
	tabs.clear();
	for(Actor _actor : _children)
	    tabs.add(_actor);
	
	body.add(tab.getContainer());
	tab.setFrom(this);
	this.currentTab = tab;
    }
    
    public void hideAllContainer()
    {
	for(TabContainer _cont : tabBind.values())
	    _cont.setVisible(false);
    }
    
    public ArrayMap<Tab, TabContainer> getTabBind()
    {
	return tabBind;
    }
    
    public Tab getCurrentTab()
    {
        return currentTab;
    }
    
    public void setCurrentTab(Tab currentTab)
    {
        this.currentTab = currentTab;
    }
    
    public static class TabPaneStyle
    {

	public Drawable background;
	
	public TabPaneStyle()
	{
	    
	}
	
	public TabPaneStyle(Drawable background)
	{
	    this.background = background;
	}

    }
    
}
