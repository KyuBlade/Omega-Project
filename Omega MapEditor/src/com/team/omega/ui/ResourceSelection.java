package com.team.omega.ui;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.team.omega.ui.base.grid.GridSelection;
import com.team.omega.ui.base.panel.Panel;


public class ResourceSelection extends Table
{

    private ScrollPane scrollPane;
    private Panel resourcePanel;
    private GridSelection<ResourceGridItem> resourceGrid;
    
    
    
    public ResourceSelection(Skin skin)
    {
	resourcePanel = new Panel(skin, "light-grey");
	resourceGrid = new GridSelection<>();
	resourceGrid.left();
	resourcePanel.add(resourceGrid).expand().fill();
	
	scrollPane = new ScrollPane(resourcePanel);
	scrollPane.setFadeScrollBars(false);
	scrollPane.setFlickScroll(false);
	add(scrollPane).expand().fill();
    }
    
    public GridSelection<ResourceGridItem> getGridSelection()
    {
	return resourceGrid;
    }
    
}
