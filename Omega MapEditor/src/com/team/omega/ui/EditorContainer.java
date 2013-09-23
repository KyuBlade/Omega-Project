package com.team.omega.ui;

import java.io.File;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.esotericsoftware.tablelayout.Value;
import com.team.omega.core.ProjectInstance;
import com.team.omega.core.project.ProjectData;
import com.team.omega.ui.base.panel.Panel;
import com.team.omega.ui.base.tab.TabContainer;

public class EditorContainer extends TabContainer
{

    private ProjectInstance project;
    private ProjectData projectData;
    
    private SplitPane rightSplitPane;
    private Panel rightPanel;
    private Panel leftPanel;
    private Panel tilePanel;
    private SplitPane leftSplitPane;

    private MenuPanel mapPropertiesPanel;

    private ScrollPane tileScrollPane;
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

	mapPropertiesPanel = new MenuPanel("Map properties", skin);
	mapPropertiesPanel.add("Name : " + projectData.getName());
	mapPropertiesPanel.add("Width : " + projectData.getWidth());
	mapPropertiesPanel.add("Height : " + projectData.getHeight());

	tilePanel = new Panel(skin);

	tileScrollPane = new ScrollPane(tilePanel, skin);
	tileScrollPane.setFadeScrollBars(false);
	tileScrollPane.setFlickScroll(false);

	leftSplitPane = new SplitPane(new Panel(skin, "blank"), tileScrollPane, true, skin);
	leftPanel.bottom().add(leftSplitPane).minHeight(Value.percentHeight(0.5f)).expandX().fillX();

	rightPanel.defaults().expandX().fill();
	rightPanel.add(mapPropertiesPanel);

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

}
