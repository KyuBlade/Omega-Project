package com.team.omega.ui.base.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.esotericsoftware.tablelayout.Cell;
import com.team.omega.core.exception.OptimizationException;
import com.team.omega.ui.base.SelectionMode;
import com.team.omega.ui.base.group.ActorGroup;
import com.team.omega.ui.base.list.ListRow;


/**
 * It's a simple {@link Table} layouted as a grid.
 * Events can be set on each {@link GridSelectionItem}
 */
public class GridSelection<T extends GridSelectionItem> extends Table
{
    
    private Array<HorizontalGroup> rows;
    private Array<VerticalGroup> columns;
    
    private boolean isVertical;
    
    private float defaultItemWidth = 100f;
    private float defaultItemHeight = 100f;

    private int itemCount;
    private SelectionMode selectionMode;
    
    private boolean isSelectable = true;
    
    private ActorGroup<T> gridItemGroup;
    
    /**
     * Create a {@link GridSelection} with default parameters (vertical and 2 column)
     */
    public GridSelection()
    {
	this(false, 3);
    }
    
    /**
     * Create a {@link GridSelection} with defined parameters
     * 
     * @param isVertical If {@link GridSelection} should be constructed and scrolled vertically or not
     * @param itemCount Number of columns if isVertical is true or number of rows if isVertical is false
     */
    public GridSelection(boolean isVertical, int itemCount)
    {
	super();
	
	this.isVertical = isVertical;
	setSelectionMode(SelectionMode.SINGLE);
	
	gridItemGroup = new ActorGroup<>();
	gridItemGroup.setMinCheckCount(0);
	
	setSelectionMode(SelectionMode.SINGLE);
	
	if(isVertical)
	{
	    columns = new Array<>();
	    for(int i = 0; i < itemCount; i++)
		addColumn();
	}
	else
	{
	    rows = new Array<>();
	    for(int i = 0; i < itemCount; i++)
		addRow();
	}
	
    }
    
    public HorizontalGroup addRow()
    {
	if(isVertical)
	    throw new OptimizationException("GridSelection is set to vertical, so you cannot add rows.");
	
	HorizontalGroup _group = new HorizontalGroup();
	rows.add(_group);
	super.add(_group).left().row();
	itemCount++;
	
	return _group;
    }
    
    public void removeRow(HorizontalGroup row)
    {
	if(isVertical)
	    throw new OptimizationException("GridSelection is set to vertical, so you cannot remove rows.");
	
	rows.removeValue(row, false);
	row.remove();
	itemCount--;
    }
    
    public VerticalGroup addColumn()
    {
	if(!isVertical)
	    throw new OptimizationException("GridSelection is set to horizontal, so you cannot add columns.");
	
	VerticalGroup _group = new VerticalGroup();
	columns.add(_group);
	super.add(_group);
	itemCount++;
	
	return _group;
    }
    
    public void removeColumn(VerticalGroup column)
    {
	if(!isVertical)
	    throw new OptimizationException("GridSelection is set to horizontal, so you cannot remove columns.");
	
	columns.removeValue(column, false);
	column.remove();
	itemCount--;
    }
    
    /**
     * Dont use it
     */
    @Override
    public Cell<?> add(Actor actor)
    {
	throw new OptimizationException("invalid actor");
    }
    
    
    /**
     * Add a {@link GridSelectionItem} to the grid according to {@link isVertical} and itemCount
     * @param item  {@link GridSelectionItem} to add
     */
    public void add(final T item)
    {
	if(item == null)
	    throw new NullPointerException("item cannot be null.");
	   
	item.setGrid(this);
	item.setSize(defaultItemWidth, defaultItemHeight);
	
	gridItemGroup.add(item);
	if (isVertical)
	{
	    columns.get((gridItemGroup.getActors().size - 1) % itemCount).addActor(item);
	}
	else
	{
	    rows.get((gridItemGroup.getActors().size - 1) % itemCount).addActor(item);
	}
    }
    
    /**
     * Remove a {@link GridSelectionItem} and relayout the {@link GridSelection}
     * @param item {@link GridSelectionItem} to remove
     */
    public void remove(T item)
    {
	remove(item, true);
    }
    
    /**
     * Remove a {@link GridSelectionItem}
     * @param item {@link GridSelectionItem} to remove
     * @param relayout true if you want to relayout the whole {@link GridSelection}
     */
    public void remove(T item, boolean relayout)
    {
	if(item == null)
	    throw new NullPointerException("item cannot be null.");
	
	gridItemGroup.remove(item);
	
	if(relayout)
	    relayout();
    }
    
    /**
     * Relayout the grid with {@link itemCount}
     */
    public void relayout()
    {
	SnapshotArray<T> _actors = new SnapshotArray<>(gridItemGroup.getActors());
	
	gridItemGroup.clear();
	if(isVertical)
	{
	    for(VerticalGroup _column : columns)
		_column.clear();
	}
	else
	{
	    for(HorizontalGroup _row : rows)
		_row.clear();
	}
	
	for(T _item : _actors)
	    add(_item);
    }
    
    /**
     * Set the selected item.
     * 
     * @param index The index of the item to be selected.
     */
    public void setSelected(int index)
    {
	Array<T> _items = gridItemGroup.getActors();
	if(index <= 0 || index >= _items.size )
	    return;
	
	setSelected(_items.get(index));
    }
    
    public void setSelected(T item)
    {
	if(item == null)
	    throw new NullPointerException("Selected item cannot be null.");
	
	gridItemGroup.setChecked(item);
    }
    
    public boolean isSelected(int index)
    {
	return isSelected(gridItemGroup.getAllChecked().get(index));
    }
    
    public boolean isSelected(T item)
    {
	if(item == null)
	    return false;
	
	return item.isChecked();
    }
    
    public void unselect(int index)
    {
	unselect(gridItemGroup.getAllChecked().get(index));
    }
    
    public void unselect(T item)
    {
	gridItemGroup.uncheck(item);
    }
    
    /**
     * @return If {@link GridSelection} should be created vertically or horizontally
     */
    public boolean isVertical()
    {
	return isVertical;
    }
    
    /**
     * Set if {@link GridSelectionItem} of this {@link GridSelection} are selectable.
     * 
     * @param isSelectable true if {@link GridSelectionItem} are selectable, false if not.
     */
    public void setSelectable(boolean isSelectable)
    {
	this.isSelectable = isSelectable;
    }
    
    /**
     * @return If {@link GridSelection} are selectable.
     */
    public boolean isSelectable()
    {
	return isSelectable;
    }
    
    /**
     * @return Number of columns if isVertical is true else, number of rows
     */
    public int getItemCount()
    {
	return itemCount;
    }
    
    /**
     * @return Return the current selection
     */
    public Array<T> getSelection()
    {	    
	return gridItemGroup.getAllChecked();
    }
    
    public void setSelectionMode(SelectionMode mode)
    {
	selectionMode = mode;
    }
    
    public SelectionMode getSelectionMode()
    {
	return selectionMode;
    }
    
}
