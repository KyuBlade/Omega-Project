package com.team.omega.ui.base.group;

import com.badlogic.gdx.utils.Array;


public class ActorGroup<T extends Groupable>
{
    
    protected Array<T> actors = new Array<>();
    protected Array<T> checked = new Array<>(1);
    protected int minCheckCount, maxCheckCount = 1;
    protected boolean uncheckLast = true;
    protected T lastChecked;
    
    private boolean isMultiOnCtrl = true;
    private boolean isMultiSelection;
    
    public ActorGroup()
    {
	
    }

    public void add(T actor)
    {
	if (actor == null)
	    throw new IllegalArgumentException("actor cannot be null.");
	
	boolean _shouldCheck = actor.isChecked() || actors.size < minCheckCount;
	actor.setActorGroup(this);
	actors.add(actor);
	if (_shouldCheck)
	    actor.setChecked(true);
    }
    
    public void remove(T actor)
    {
	if (actor == null)
	    throw new IllegalArgumentException("actor cannot be null.");
	
	actor.setChecked(false);
	actor.setActorGroup(null);
	actors.removeValue(actor, true);
    }
    
    public void setChecked(T actor)
    {
	if (actor == null)
	    throw new IllegalArgumentException("actor cannot be null.");
	
	actor.setChecked(true);
    }

    public boolean canCheck(T actor, boolean newState)
    {
	if (actor.isChecked() == newState)
	    return false;

	if (!newState)
	{
	    // Keep actor checked to enforce minCheckCount.
	    if (checked.size <= minCheckCount)
		return false;
	    
	    checked.removeValue(actor, true);
	}
	else
	{
	    // Keep actor unchecked to enforce maxCheckCount.
	    if (maxCheckCount != -1 && checked.size >= maxCheckCount)
	    {
		if (uncheckLast)
		{
		    final int old = minCheckCount;
		    minCheckCount = 0;
		    lastChecked.setChecked(false);
		    minCheckCount = old;
		}
		else
		    return false;
	    }
	    checked.add(actor);
	    lastChecked = actor;
	}

	return true;
    }

    public void uncheckAll()
    {
	final int _old = minCheckCount;
	minCheckCount = 0;
	for(T _actor : actors)
	    _actor.setChecked(false);
	minCheckCount = _old;
	checked.clear();
    }

    public void uncheck(T actor)
    {
	actor.setChecked(false);
    }
    
    public void beginSelection()
    {
	isMultiSelection = true;
	setMaxCheckCount(-1);
    }
    
    public void endSelection()
    {
	isMultiSelection = false;
	setMaxCheckCount(1);
	uncheckAll();
    }

    public Array<T> getAllChecked()
    {
	return checked;
    }

    public Array<T> getActors()
    {
	return actors;
    }

    public T getLastChecked()
    {
	return lastChecked;
    }

    public T getChecked()
    {
	if (checked.size > 0)
	    return checked.get(0);

	return null;
    }

    /*
     * @return If need to press control key to select multiple items
     */
    public boolean isMultiOnCtrl()
    {
        return isMultiOnCtrl;
    }

    /**
     * Set the way how MultiSelection is handled
     * 
     * @param isMultiOnCtrl true if must press control key to select multiple items
     */
    public void setMultiOnCtrl(boolean isMultiOnCtrl)
    {
        this.isMultiOnCtrl = isMultiOnCtrl;
    }

    public boolean isMultiSelection()
    {
	return isMultiSelection;
    }

    public void setMinCheckCount(int minCheckCount)
    {
	this.minCheckCount = minCheckCount;
    }

    public void setMaxCheckCount(int maxCheckCount)
    {
	this.maxCheckCount = maxCheckCount;
    }

    public void setUncheckLast(boolean uncheckLast)
    {
	this.uncheckLast = uncheckLast;
    }

    public void clear()
    {
	actors.clear();
	checked.clear();
	
	lastChecked = null;
    }
    
}
