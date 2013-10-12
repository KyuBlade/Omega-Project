package com.team.omega.ui.base.list;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.team.omega.ui.base.panel.Panel;
import com.team.omega.ui.base.panel.PanelGroup;

public class AdvancedList<T extends ListRow> extends Table
{

    private Array<T> items = new Array<>();
    private int selectedIndex = -1;
    private boolean selectable = true;
    
    private PanelGroup panelGroup;

    public AdvancedList()
    {
	this(null);

    }

    public AdvancedList(T[] items)
    {
	panelGroup = new PanelGroup();
	
	setWidth(getPrefWidth());
	setHeight(getPrefHeight());

	top();
	defaults().expandX().fillX();
    }

    /** @return The index of the currently selected item. The top item has an index of 0. Nothing selected has an index of -1. */
    public int getSelectedIndex()
    {
	return selectedIndex;
    }

    /** @return The ListRow of the currently selected item, or null if the list is empty or nothing is selected. */
    public T getSelection()
    {
	try {
	    return items.get(selectedIndex);
	} catch(IndexOutOfBoundsException e) {
	    return null;
	}
    }

    public Array<T> getItems()
    {
	return items;
    }

    public void addItem(final T item)
    {
	item.addListener(new ClickListener() {
	   
	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		setSelected(items.indexOf(item, false));
	    }
	    
	});
	
	panelGroup.add(item);
	items.add(item);
	add(item).row();
    }
    
    public void removeItem(T item)
    {
	int _index = panelGroup.getPanels().indexOf(item, false);
	Array<Panel> _panels = panelGroup.getPanels();
	
	panelGroup.remove(item);
	
	item.remove();
	items.removeValue(item, false);
	
	if(_index >= panelGroup.getPanels().size - 1 && _panels.size > 0)
	    _index = panelGroup.getPanels().indexOf(_panels.peek(), false);
	
	setSelected(_index);
    }
    
    /**
     * Remove the selected row
     */
    public void removeSelected()
    {
	if (selectedIndex < 0 || selectedIndex >= items.size)
	    return;

	removeItem(items.get(selectedIndex));
    }
    
    public void setSelected(int index)
    {
	if(index < 0 || index > items.size)
	    return;
	
	selectedIndex = index;
	
	if(panelGroup.getPanels().size > 0)
	    panelGroup.setCheckedIndex(index);
    }

}
