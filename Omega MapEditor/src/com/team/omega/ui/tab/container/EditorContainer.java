package com.team.omega.ui.tab.container;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.team.omega.core.EditorCore;
import com.team.omega.core.ProjectInstance;
import com.team.omega.core.project.ProjectData;
import com.team.omega.core.screen.EditorScreen;
import com.team.omega.ui.ResourceSelection;
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
    private Panel editorViewPanel;

    private MapPropertiesMenu mapPropertiesMenu;
    private ResourceSelection resourceSelection;
    
    private ScrollPane rightScrollPane;
    
    private EditorScreen editorScreen;
    
    private boolean isMinimized;
    private float oldLeftSplitPaneAmount;
    private float oldRightSplitPaneAmount;

    public EditorContainer(File file, Skin skin)
    {
	super(skin);
	
	editorScreen = EditorCore.getInstance().getScreenManager().getScreen(EditorScreen.class);
	
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

	editorViewPanel = new Panel(skin, "blank");
	editorViewPanel.setTouchable(Touchable.enabled);
	editorViewPanel.addListener(new InputListener() {
	    
	    @Override
	    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
	    {
		if(button != Buttons.LEFT)
		    return false;
		
		editorScreen.click(x, y);
		
		return true;
	    }
	    
	});
	leftSplitPane = new SplitPane(editorViewPanel, resourceSelection, true, skin);
	leftPanel.bottom().add(leftSplitPane).expand().fill();

	rightPanel.defaults().expandX().fill();
	rightPanel.add(mapPropertiesMenu);

	rightScrollPane = new ScrollPane(rightPanel, skin);
	rightScrollPane.setFadeScrollBars(false);
	rightScrollPane.setFlickScroll(false);

	rightSplitPane = new SplitPane(leftPanel, rightScrollPane, false, skin);
	rightSplitPane.setSplitAmount(0.8f);
	add(rightSplitPane).expand().fill();
    }
    
    @Override
    public void setStage(Stage stage)
    {
	super.setStage(stage);
	if(stage == null)
	    return;
	
	stage.addListener(new InputListener() {

	    @Override
	    public boolean keyDown(InputEvent event, int keycode)
	    {
		if (keycode == Keys.SPACE)
		{
		    isMinimized = !isMinimized;
		    if (isMinimized)
		    {
			oldLeftSplitPaneAmount = leftSplitPane.getSplit();
			oldRightSplitPaneAmount = rightSplitPane.getSplit();
			leftSplitPane.setSplitAmount(1f);
			rightSplitPane.setSplitAmount(1f);
		    }
		    else
		    {
			if(leftSplitPane.getSplit() < 1f)
			    oldLeftSplitPaneAmount = leftSplitPane.getSplit();
			leftSplitPane.setSplitAmount(oldLeftSplitPaneAmount);
			if(rightSplitPane.getSplit() < 1f)
			    oldRightSplitPaneAmount = rightSplitPane.getSplit();
			rightSplitPane.setSplitAmount(oldRightSplitPaneAmount);
		    }
		}
		else
		    return false;

		return true;
	    }

	});
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
