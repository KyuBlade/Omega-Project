package com.team.omega.ui.base.list;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.utils.Array;

public class AdvancedList<T extends ListRow> extends Table
{

    private Array<T> items = new Array<>();
    private int selectedIndex;
    private boolean selectable = true;

    public AdvancedList()
    {
	this(null);

    }

    public AdvancedList(T[] items)
    {
	setWidth(getPrefWidth());
	setHeight(getPrefHeight());

	defaults().expandX().fillX();
    }

    /** Sets whether this List's items are selectable. If not selectable, touch events will not be consumed. */
    public void setSelectable(boolean selectable)
    {
	this.selectable = selectable;
    }

    /** @return True if items are selectable. */
    public boolean isSelectable()
    {
	return selectable;
    }

    /** @return The index of the currently selected item. The top item has an index of 0. Nothing selected has an index of -1. */
    public int getSelectedIndex()
    {
	return selectedIndex;
    }

    /** @return The ListRow of the currently selected item, or null if the list is empty or nothing is selected. */
    public T getSelection()
    {
	if (items.size == 0 || selectedIndex == -1)
	    return null;

	return items.get(selectedIndex);
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
		items.get(selectedIndex).setIsSelected(false);
		selectedIndex = items.indexOf(item, false);
		item.setIsSelected(true);
	    }
	    
	});
	
	items.add(item);
	add(item).row();
    }
    
    public void removeItem(T item)
    {
	item.remove();
	int _index = items.indexOf(item, false);
	items.removeValue(item, false);
	items.get(_index).setIsSelected(true);
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

}
