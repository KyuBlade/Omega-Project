package com.team.omega.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.team.omega.ui.base.tab.Tab;
import com.team.omega.ui.base.tab.TabPane;
import com.team.omega.ui.tab.container.resource.ObjectResourceSelectionContainer;
import com.team.omega.ui.tab.container.resource.TerrainResourceSelectionContainer;


public class ResourceSelection extends TabPane
{
    
    public ResourceSelection(Skin skin)
    {
	super(skin);
	
	addTab(new Tab("Terrain", new TerrainResourceSelectionContainer(skin), skin));
	addTab(new Tab("Objects", new ObjectResourceSelectionContainer(skin), skin));
    }
    
}
