package com.team.omega.ui.base.grid;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.tablelayout.Cell;
import com.team.omega.core.exception.OptimizationException;
import com.team.omega.ui.base.panel.PanelGroup;


/**
 * It's a simple {@link Table} layouted as a grid.
 * Events can be set on each {@link GridSelectionItem}
 */
public class GridSelection<T extends GridSelectionItem> extends Table
{

    public enum SelectionMode {
	MULTI,
	SINGLE
    }
    
    private Array<T> items = new Array<>();
    
    private boolean isVertical;
    
    private float defaultItemWidth = 100f;
    private float defaultItemHeight = 100f;

    private int itemCount;
    private SelectionMode selectionMode = SelectionMode.SINGLE;
    
    private T[] selection;
    
    private PanelGroup panelGroup;
    
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
	this.itemCount = itemCount;
	
	panelGroup = new PanelGroup();
	defaults().minSize(defaultItemWidth, defaultItemHeight).maxSize(defaultItemWidth, defaultItemHeight);
    }
    
    /**
     * Dont use it
     */
    @Override
    public Cell<?> add(Actor actor)
    {
	throw new OptimizationException("Invalid actor");
    }
    
    /**
     * Add a {@link GridSelectionItem} to the grid according to {@link isVertical} and itemCount
     * You need to call {@link GridSelection#update()} after this to layout
     * @param item {@link GridSelectionItem} to add
     */
    public void add(T item)
    {
	add(item, false);
    }
    
    /**
     * Add a {@link GridSelectionItem} to the grid according to {@link isVertical} and itemCount
     * @param item  {@link GridSelectionItem} to add
     * @param update true if you want to update automatically after this add
     */
    public void add(T item, boolean update)
    {
	panelGroup.add(item);
	items.add(item);
	
	if(update)
	    update();
    }
    
    /**
     * Remove a {@link GridSelectionItem}
     * @param item {@link GridSelectionItem} to remove
     */
    public void remove(T item)
    {
	remove(item, false);
    }
    
    /**
     * Remove a {@link GridSelectionItem}
     * @param item {@link GridSelectionItem} to add
     * @param update true if you want to update automatically after this remove
     */
    public void remove(T item, boolean update)
    {
	panelGroup.remove(item);
	items.removeValue(item, false);
	item.remove();
	
	if(update)
	    update();
    }
    
    /** 
     * Update the TableLayout.
     * Call it when you have finished to add your item
     */
    public void update()
    {
	clearChildren();
	
	int _splitedSize = items.size / itemCount + 1;
	for(int i = 0; i < items.size; i++)
	{
	    if((!isVertical && i % _splitedSize == 0) || (isVertical && i % itemCount == 0))
		row();
	    
	    super.add(items.get(i));
	}
    }
    
    /**
     * @return If {@link GridSelection} should be created vertically or horizontally
     */
    public boolean isVertical()
    {
	return isVertical;
    }
    
    /**
     * @return Number of columns if isVertical is true else, number of rows
     */
    public int getItemCount()
    {
	return itemCount;
    }
    
    /**
     * @return Return the current selection if you are in MultiSelection mode
     */
    public T getSelection()
    {
	if(selectionMode != SelectionMode.SINGLE)
	    throw new OptimizationException("You are in MultiSelection mode, you must use getSelections()");
	    
	return selection[0];
    }
    
    /**
     * @return Return the current selected items
     */
    public T[] getSelections()
    {
	return selection;
    }
    
}
