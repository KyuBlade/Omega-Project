package com.team.omega.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.team.omega.ui.base.tab.Tab;
import com.team.omega.ui.base.tab.TabPane;
import com.team.omega.ui.tab.container.resource.selection.ObjectResourceSelectionContainer;
import com.team.omega.ui.tab.container.resource.selection.TerrainResourceSelectionContainer;
import com.team.omega.ui.tab.container.resource.selection.WallResourceSelectionContainer;


public class ResourceSelection extends TabPane
{
    
    public ResourceSelection(Skin skin)
    {
	super(skin);
	
	addTab(new Tab("Terrain", new TerrainResourceSelectionContainer(skin), skin));
	addTab(new Tab("Wall", new WallResourceSelectionContainer(skin), skin));
	addTab(new Tab("Object", new ObjectResourceSelectionContainer(skin), skin));
	setCurrentTab(0);
    }
    
}
