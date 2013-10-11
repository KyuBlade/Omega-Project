package com.team.omega.ui.base.grid;

import com.badlogic.gdx.Gdx;
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

    private Array<T> items = new Array<>();
    
    private boolean isVertical;
    
    private float defaultItemWidth = 100f;
    private float defaultItemHeight = 100f;

    private int itemCount;
    
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
	
	setTouchable(Touchable.enabled);
	
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
     * @param item
     * @return
     */
    public Cell<?> add(T item)
    {
	panelGroup.add(item);
	
	super.getChildren().add(item);
	GridSelectionItem[] _children = getChildren().toArray(GridSelectionItem.class);
	clearChildren();
	
	int _splitedSize = _children.length / itemCount + 1;
	Cell<?> _lastCell = null;
	for(int i = 0; i < _children.length; i++)
	{
	    if((!isVertical && i % _splitedSize == 0) || (isVertical && i % itemCount == 0))
		row();
	    
	    _lastCell = super.add(_children[i]);
	}
	
	return _lastCell;
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
    
}
