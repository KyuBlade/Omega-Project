package com.team.omega.core.screen;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.esotericsoftware.tablelayout.Value;
import com.team.omega.ui.panel.Panel;



public class InterfaceScreen extends BaseScreen
{
    
    private Panel topMenu;
    private TextButton fileMenuButton;
    private TextButton editMenuButton;
    private TextButton viewMenuButton;
    
    private ImageButton newButton;
    private ImageButton openButton;
    private ImageButton saveButton;
    private ImageButton undoButton;
    private ImageButton redoButton;
    
    private SplitPane rightSplitPane;
    private Panel rightPanel;
    private Panel leftPanel;
    private Panel tilePanel;
    private SplitPane leftSplitPane;
    
    private Panel mapPropertiesPanel;
    private Label mapPropertiesLabel;
    
    private ScrollPane tileScrollPane;
    private ScrollPane rightScrollPane;

    public InterfaceScreen(ScreenManager screenManager)
    {
	super(screenManager, 1);
	
	// Top menu
	topMenu = new Panel(skin, "grey");
	topMenu.left();
	fileMenuButton = new TextButton("File", skin, "blank");
	editMenuButton = new TextButton("Edit", skin, "blank");
	viewMenuButton = new TextButton("View", skin, "blank");
	topMenu.defaults().padLeft(5f).padRight(5f);
	topMenu.add(fileMenuButton);
	topMenu.add(editMenuButton);
	topMenu.add(viewMenuButton);
	topMenu.row();
	
	// Toolbar
	newButton = new ImageButton(skin, "new-file");
	openButton = new ImageButton(skin, "open");
	saveButton = new ImageButton(skin, "save");
	undoButton = new ImageButton(skin);
	redoButton = new ImageButton(skin);
	topMenu.add(newButton);
	topMenu.add(openButton);
	topMenu.add(saveButton);
	topMenu.add(undoButton);
	topMenu.add(redoButton);
	
	layout.add(topMenu).expandX().fillX().left();
	layout.row();
	
	rightPanel = new Panel(skin, "blank");
	rightPanel.top();
	leftPanel = new Panel(skin, "blank");
	leftPanel.row();
	
	mapPropertiesPanel = new Panel(skin);
	mapPropertiesLabel = new Label("Map properties", skin);
	
	tilePanel = new Panel(skin);
	
	tileScrollPane = new ScrollPane(tilePanel, skin);
	tileScrollPane.setFadeScrollBars(false);
	tileScrollPane.setFlickScroll(false);
	
	leftSplitPane = new SplitPane(new Panel(skin, "blank"), tileScrollPane, true, skin);
	leftPanel.bottom().add(leftSplitPane).minHeight(Value.percentHeight(0.5f)).expandX().fillX();
	
	rightPanel.defaults().expandX().fill();
	rightPanel.add(mapPropertiesPanel);
	mapPropertiesPanel.add(mapPropertiesLabel);
	
	rightScrollPane = new ScrollPane(rightPanel, skin);
	rightScrollPane.setFadeScrollBars(false);
	rightScrollPane.setFlickScroll(false);
	
	rightSplitPane = new SplitPane(leftPanel, rightScrollPane, false, skin);
	rightSplitPane.setMaxSplitAmount(0.8f);
	rightSplitPane.setSplitAmount(0.8f);
	layout.add(rightSplitPane).expand().fill();
    }
    
    @Override
    public void render(float delta)
    {
	super.render(delta);
    }

}
