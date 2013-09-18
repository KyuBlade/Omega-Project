package com.team.omega.core.screen;

public class EditorScreen extends BaseScreen
{

    private boolean isGridDisplay;

    public EditorScreen(ScreenManager screenManager)
    {
	super(screenManager, 1);
    }

    public boolean isGridDisplay()
    {
	return isGridDisplay;
    }

    public void setGridDisplay(boolean isGridDisplay)
    {
	this.isGridDisplay = isGridDisplay;
    }

}
