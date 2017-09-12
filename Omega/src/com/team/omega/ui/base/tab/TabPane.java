package com.team.omega.ui.base.tab;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
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
    private ButtonGroup buttonGroup;

    private ArrayMap<Tab, TabContainer> tabBind = new ArrayMap<>();

    private Tab currentTab;

    /**
     * Create a {@link TabPane} with the default style.
     * 
     * @param skin the skin to use for style
     */
    public TabPane(Skin skin)
    {
	this(skin, "default");
    }

    /**
     * Create a {@link TabPane} with the specified style.
     * 
     * @param skin the skin to use for style
     * @param styleName the name of the style to use
     */
    public TabPane(Skin skin, String styleName)
    {
	super(skin);

	buttonGroup = new ButtonGroup();
	tabs = new Table(skin);
	tabs.setBackground(skin.get(styleName, TabPaneStyle.class).background);
	body = new Stack();
	this.left().top();
	tabs.left();

	this.add(tabs).left().expandX().fillX();
	this.row();
	this.add(body).expand().fill();
    }

    /**
     * Add the specified {@link Tab}.
     * 
     * @param tab the {@link Tab} to add
     */
    public void addTab(Tab tab)
    {
	tabBind.put(tab, tab.getContainer());
	buttonGroup.add(tab);
	Array<Actor> _children = new Array<>(tabs.getChildren());
	_children.add(tab);
	tabs.clear();
	for (Actor _actor : _children)
	    tabs.add(_actor);

	body.add(tab.getContainer());
	tab.setFrom(this);
	
	setCurrentTab(tab);
    }

    /**
     * Remove the specified {@link Tab}.
     * 
     * @param tab the {@ink Tab} to remove
     */
    public void removeTab(Tab tab)
    {
	Tab _tab = null;
	int _index = tabBind.indexOfKey(tab);
	if(_index + 1 < tabBind.size)
	    _tab = tabBind.getKeyAt(_index + 1);
	else if(_index - 1 >= 0)
	    _tab = tabBind.getKeyAt(_index - 1);
	
	buttonGroup.remove(tab);
	tabBind.getValueAt(_index).remove();
	tabBind.removeIndex(_index);
	tab.remove();

	setCurrentTab(_tab);
    }

    private void hideAllContainer()
    {
	for (TabContainer _cont : tabBind.values())
	    _cont.setVisible(false);
    }

    /**
     * Get all {@link Tab}s of this {@link TabPane}.
     * 
     * @return all {@link Tab}
     */
    public ArrayMap<Tab, TabContainer> getTabs()
    {
	return tabBind;
    }

    /**
     * Get the selected {@link TabContainer}.
     * 
     * @return the selected {@link TabContainer}
     */
    public TabContainer getCurrentTab()
    {
	return currentTab.getContainer();
    }

    /**
     * Set the selected {@link Tab}.
     * 
     * @param currentTab the {@link Tab} to set selected
     */
    public void setCurrentTab(Tab currentTab)
    {
	this.currentTab = currentTab;
	
	if(currentTab == null)
	    return;
	
	hideAllContainer();
	tabBind.get(currentTab).setVisible(true);
	currentTab.setChecked(true);
    }
    
    /**
     * Set the selected {@ink Tab}.
     * 
     * @param index the index of the {@link Tab}
     */
    public void setCurrentTab(int index)
    {
	setCurrentTab(tabBind.getKeyAt(index));
    }
    
    /**
     * Get the {@link TabContainer} at the specified index.
     * Start from 0.
     * 
     * @param index position of the {@link Tab} of the {@link TabContainer}
     * @return the {@link TabContainer} at the specified index
     */
    public TabContainer getTab(int index)
    {
	return tabBind.getValueAt(index);
    }
    
    /**
     * Get the first {@link TabContainer} of the specified type.
     * 
     * @param clazz the type of the searched {@link TabCOntainer}
     * @return the first found {@link TabContainer} of the searched type or null if nothing is found
     */
    public <T extends TabContainer> T getTab(Class<T> clazz)
    {
	for(TabContainer _container : tabBind.values())
	{
	    try {
    	    T _cast = clazz.cast(_container);
    	    
    	    return _cast;
	    } catch(ClassCastException e)
	    {
		
	    }
	}
	
	return null;
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
