package com.team.omega.ui;

import com.badlogic.gdx.utils.Array;

public class PanelGroup
{

    private final Array<Panel> panels = new Array<>();
    private Array<Panel> checkedPanels = new Array<>(1);
    private int minCheckCount, maxCheckCount = 1;
    private boolean uncheckLast = true;
    private Panel lastChecked;

    public PanelGroup()
    {
	minCheckCount = 1;
    }

    public PanelGroup(Panel... panels)
    {
	minCheckCount = 0;
	add(panels);
	minCheckCount = 1;
    }

    public void add(Panel panel)
    {
	if (panel == null)
	    throw new IllegalArgumentException("panem cannot be null.");
	panel.setPanelGroup(null);
	boolean shouldCheck = panel.isChecked() || panels.size < minCheckCount;
	panel.setChecked(false);
	panel.setPanelGroup(this);
	panels.add(panel);
	if (shouldCheck)
	    panel.setChecked(true);
    }

    public void add(Panel... panels)
    {
	if (panels == null)
	    throw new IllegalArgumentException("panels cannot be null.");
	for (int i = 0, n = panels.length; i < n; i++)
	    add(panels[i]);
    }

    public void remove(Panel panel)
    {
	if (panel == null)
	    throw new IllegalArgumentException("panel cannot be null.");
	panel.setPanelGroup(null);
	panels.removeValue(panel, true);
    }

    public void remove(Panel... panels)
    {
	if (panels == null)
	    throw new IllegalArgumentException("panels cannot be null.");
	for (int i = 0, n = panels.length; i < n; i++)
	    remove(panels[i]);
    }

    public void setChecked(int id)
    {
	for (int i = 0, n = panels.size; i < n; i++)
	{
	    Panel panel = panels.get(i);
	    if (panel.getId() == id)
	    {
		panel.setChecked(true);
		return;
	    }
	}
    }

    /**
     * Called when a panel is checked or unchecked.
     * 
     * @return true if the new state should be allowed.
     */
    protected boolean canCheck(Panel panel, boolean newState)
    {
	if (panel.isChecked() == newState)
	    return false;

	if (!newState)
	{
	    // Keep panel checked to enforce minCheckCount.
	    if (checkedPanels.size <= minCheckCount)
		return false;
	    checkedPanels.removeValue(panel, true);
	}
	else
	{
	    // Keep panel unchecked to enforce maxCheckCount.
	    if (maxCheckCount != -1 && checkedPanels.size >= maxCheckCount)
	    {
		if (uncheckLast)
		{
		    int old = minCheckCount;
		    minCheckCount = 0;
		    lastChecked.setChecked(false);
		    minCheckCount = old;
		}
		else
		    return false;
	    }
	    checkedPanels.add(panel);
	    lastChecked = panel;
	}

	return true;
    }

    /** Sets all panel' {@link Panel#isChecked()} to false, regardless of {@link #setMinCheckCount(int)}. */
    public void uncheckAll()
    {
	int _old = minCheckCount;
	minCheckCount = 0;
	for (int i = 0, n = panels.size; i < n; i++)
	{
	    Panel _panel = panels.get(i);
	    _panel.setChecked(false);
	}
	minCheckCount = _old;
    }

    /** @return the first checked panel, or null. */
    public Panel getChecked()
    {
	if (checkedPanels.size > 0)
	    return checkedPanels.get(0);
	return null;
    }

    public Array<Panel> getAllChecked()
    {
	return checkedPanels;
    }

    public Array<Panel> getPanels()
    {
	return panels;
    }

    /** Sets the minimum number of panels that must be checked. Default is 1. */
    public void setMinCheckCount(int minCheckCount)
    {
	this.minCheckCount = minCheckCount;
    }

    /** Sets the maximum number of panels that can be checked. Set to -1 for no maximum. Default is 1. */
    public void setMaxCheckCount(int maxCheckCount)
    {
	this.maxCheckCount = maxCheckCount;
    }

    /**
     * If true, when the maximum number of panels are checked and an additional panel is checked, the last panel to be checked is unchecked so that the maximum is not exceeded. If false, additional
     * panels beyond the maximum are not allowed to be checked. Default is true.
     */
    public void setUncheckLast(boolean uncheckLast)
    {
	this.uncheckLast = uncheckLast;
    }
    
    public void clear()
    {
	panels.clear();
	checkedPanels.clear();
    }

}
