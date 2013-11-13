package com.team.omega.ui.tab.container;

import java.io.File;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.esotericsoftware.tablelayout.Value;
import com.team.omega.core.ProjectInstance;
import com.team.omega.core.project.ProjectData;
import com.team.omega.ui.MenuPanel;
import com.team.omega.ui.ResourceGridItem;
import com.team.omega.ui.ResourceSelection;
import com.team.omega.ui.base.SelectionMode;
import com.team.omega.ui.base.grid.GridSelection;
import com.team.omega.ui.base.panel.Panel;
import com.team.omega.ui.base.tab.TabContainer;
import com.team.omega.ui.menu.MapPropertiesMenu;

public class EditorContainer extends TabContainer
{

    private ProjectInstance project;
    private ProjectData projectData;
    
    private SplitPane leftSplitPane;
    private SplitPane rightSplitPane;
    
    private Panel rightPanel;
    private Panel leftPanel;

    private MapPropertiesMenu mapPropertiesMenu;
    private ResourceSelection resourceSelection;
    
    private ScrollPane rightScrollPane;

    public EditorContainer(File file, Skin skin)
    {
	super(skin);
	
	project = new ProjectInstance(file);
	projectData = project.getProjectHandler().getProjectData();
	
	rightPanel = new Panel(skin, "blank");
	rightPanel.top();
	leftPanel = new Panel(skin, "blank");
	leftPanel.row();

	mapPropertiesMenu = new MapPropertiesMenu(skin);
	mapPropertiesMenu.setMapName(projectData.getName());
	mapPropertiesMenu.setMapWidth(projectData.getWidth());
	mapPropertiesMenu.setMapHeight(projectData.getHeight());
	
	resourceSelection = new ResourceSelection(skin);

	leftSplitPane = new SplitPane(new Panel(skin, "blank"), resourceSelection, true, skin);
	leftPanel.bottom().add(leftSplitPane).minHeight(Value.percentHeight(0.5f)).expandX().fillX();

	rightPanel.defaults().expandX().fill();
	rightPanel.add(mapPropertiesMenu);

	rightScrollPane = new ScrollPane(rightPanel, skin);
	rightScrollPane.setFadeScrollBars(false);
	rightScrollPane.setFlickScroll(false);

	rightSplitPane = new SplitPane(leftPanel, rightScrollPane, false, skin);
	rightSplitPane.setMaxSplitAmount(0.8f);
	rightSplitPane.setSplitAmount(0.8f);
	this.add(rightSplitPane).expand().fill();
    }
    
    public ProjectInstance getProject()
    {
	return project;
    }

    public ResourceSelection getResourceSelection()
    {
        return resourceSelection;
    }
    
    public MapPropertiesMenu getMapPropertiesMenu()
    {
	return mapPropertiesMenu;
    }

}
