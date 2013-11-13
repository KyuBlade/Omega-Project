package com.team.omega.ui.base.list;

import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.SnapshotArray;
import com.team.omega.ui.base.SelectionMode;
import com.team.omega.ui.base.grid.GridSelectionItem;
import com.team.omega.ui.base.group.ActorGroup;

/**
 * Create a list with custom rows.
 *
 * @param <T>
 */
public class AdvancedList<T extends ListRow> extends VerticalGroup
{
    
    private SelectionMode selectionMode;
    
    private boolean isSelectable = true;
    
    private ActorGroup<T> listRowGroup;

    /**
     * Create an {@link AdvancedList} with no default {@link GridSelectionItem}.
     */
    public AdvancedList()
    {
	this(null);
    }

    /**
     * Create an {@link AdvancedList} and fill it with {@link GridSelectionItem}.
     * 
     * @param items Array of {@link ListRow} to fill with
     */
    public AdvancedList(T[] items)
    {
	listRowGroup = new ActorGroup<>();
	listRowGroup.setMinCheckCount(0);
	
	setSelectionMode(SelectionMode.SINGLE);
	
	if(items == null)
	    return;
	
	for(T _item : items)
	    addItem(_item);
    }
    
    public IntArray getSelectedIndexes()
    {
	IntArray _indexes = new IntArray();
	for(T _item : listRowGroup.getAllChecked())
	    _indexes.add(listRowGroup.getActors().indexOf(_item, true));
	
	return _indexes;
    }

    /** 
     * @return The {@link ListRow}s of the currently selected items.
     */
    public SnapshotArray<T> getSelection()
    {
	return new SnapshotArray<>(listRowGroup.getAllChecked());
    }

    /**
     * Access to all {@link ListRow} of this list.
     * 
     * @return All {@link ListRow} of this list.
     */
    public Array<T> getItems()
    {
	return listRowGroup.getActors();
    }

    /**
     * Add a {@link ListRow} to this list and set it to selected.
     * @param item The {@link ListRow} to add.
     */
    public void addItem(final T item)
    {	
	item.setList(this);
	listRowGroup.add(item);
	item.setWidth(getWidth());
	addActor(item);
    }
    
    /**
     * Remove an {@link ListRow} from this list.
     * Next {@link ListRow} will be set to selected. If there are no more {@link ListRow} next then it select the previous.
     * 
     * @param item The {@link ListRow} to remove.
     */
    public void removeItem(T item)
    {
	if(item == null)
	    throw new NullPointerException("item cannot be null.");
	
	listRowGroup.remove(item);
	item.remove();
    }
    
    
    public void removeItems(Array<T> items)
    {
	for(T _item : items)
	    removeItem(_item);
    }
    
    /**
     * Remove severals {@link ListRow} from this list.
     * Next {@link ListRow} will be set to selected. If there are no more {@link GridSelectionItem} next then it select the previous.
     * 
     * @param items The {@link ListRow} to removes.
     */
    public void removeItems(T... items)
    {
	for(T _item : items)
	    removeItem(_item);
    }
    
    /**
     * Set the selected item.
     * 
     * @param index The index of the item to be selected.
     */
    public void setSelected(int index)
    {
	Array<T> _items = listRowGroup.getActors();
	if(index <= 0 || index >= _items.size )
	    return;
	
	setSelected(_items.get(index));
    }
    
    public void setSelected(T item)
    {
	if(item == null)
	    throw new NullPointerException("Selected item cannot be null.");
	
	listRowGroup.setChecked(item);
    }
    
    public boolean isSelected(int index)
    {
	return isSelected(listRowGroup.getAllChecked().get(index));
    }
    
    public boolean isSelected(T item)
    {
	if(item == null)
	    return false;
	
	return item.isChecked();
    }
    
    /**
     * Set if {@link ListRow} of this list are selectable.
     * 
     * @param isSelectable true if {@link ListRow} are selectable, false if not.
     */
    public void setSelectable(boolean isSelectable)
    {
	this.isSelectable = isSelectable;
    }
    
    /**
     * @return If {@link ListRow} are selectable.
     */
    public boolean isSelectable()
    {
	return isSelectable;
    }
    
    public void unselect(int index)
    {
	unselect(listRowGroup.getAllChecked().get(index));
    }
    
    public void unselect(T item)
    {
	listRowGroup.uncheck(item);
    }
    
    /**
     * Set the {@link SelectionMode} of the list.
     * 
     * @param mode The new {@link SelectionMode} of the list.
     */
    public void setSelectionMode(SelectionMode mode)
    {
	selectionMode = mode;
    }
    
    /**
     * @return The current {@link SelectionMode} of the list.
     */
    public SelectionMode getSelectionMode()
    {
	return selectionMode;
    }

}
