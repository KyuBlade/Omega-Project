package com.team.omega.core.screen;

import java.io.File;

import javax.swing.JFileChooser;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.team.omega.core.project.ProjectFileFilter;
import com.team.omega.ui.EditorContainer;
import com.team.omega.ui.MainEditorTab;
import com.team.omega.ui.base.menu.BasicMenuItem;
import com.team.omega.ui.base.menu.CheckboxMenuItem;
import com.team.omega.ui.base.menu.ContextMenu;
import com.team.omega.ui.base.menu.MenuBar;
import com.team.omega.ui.base.menu.ToolBar;
import com.team.omega.ui.base.tab.TabPane;



public class InterfaceScreen extends BaseScreen
{
    
    private EditorScreen editorScreen;
    
    private JFileChooser fileDialog;
    
    private MenuBar menuBar;
    private ContextMenu fileMenu;
    private ContextMenu editMenu;
    private ContextMenu viewMenu;
    private ContextMenu tilesetMenu;
    
    private TextButton fileMenuButton;
    private TextButton editMenuButton;
    private TextButton viewMenuButton;
    private TextButton tilesetMenuButton;
    
    private ToolBar toolBar;
    
    private ImageButton newButton;
    private ImageButton openButton;
    private ImageButton saveButton;
    private ImageButton undoButton;
    private ImageButton redoButton;
    
    private TabPane tabPane;

    public InterfaceScreen(ScreenManager screenManager)
    {
	super(screenManager, 2);
	
	this.editorScreen = screenManager.getScreen(EditorScreen.class);
	
	fileDialog = new JFileChooser();
	fileDialog.setFileFilter(new ProjectFileFilter());
	fileDialog.setAcceptAllFileFilterUsed(false);
	
	// MenuBar
	menuBar = new MenuBar(skin);
	fileMenuButton = new TextButton("File", skin, "menu");
	fileMenuButton.setWidth(50f);
	createFileMenu();
	menuBar.addContextMenu(fileMenuButton, fileMenu, stage);
	
	editMenuButton = new TextButton("Edit", skin, "menu");
	editMenuButton.setWidth(50f);
	createEditMenu();
	menuBar.addContextMenu(editMenuButton, editMenu, stage);
	
	viewMenuButton = new TextButton("View", skin, "menu");
	viewMenuButton.setWidth(50f);
	createViewMenu();
	menuBar.addContextMenu(viewMenuButton, viewMenu, stage);
	
	tilesetMenuButton = new TextButton("Tileset", skin, "menu");
	tilesetMenuButton.setWidth(70f);
	createTilesetMenu();
	menuBar.addContextMenu(tilesetMenuButton, tilesetMenu, stage);
	
	layout.add(menuBar).expandX().fillX().left();
	layout.row();
	
	// Toolbar
	toolBar = new ToolBar(skin);
	newButton = new ImageButton(skin, "new-file");
	newButton.addListener(new ClickListener() {
	    
	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		openOpenDialog();
	    }
	    
	});
	openButton = new ImageButton(skin, "open");
	saveButton = new ImageButton(skin, "save");
	undoButton = new ImageButton(skin, "back");
	redoButton = new ImageButton(skin, "next");
	toolBar.add(newButton);
	toolBar.add(openButton);
	toolBar.add(saveButton);
	toolBar.add(undoButton);
	toolBar.add(redoButton);
	
	layout.add(toolBar).expandX().fillX().left();
	layout.row();
	
	tabPane = new TabPane(skin);
	layout.add(tabPane).expand().fill();
    }
    
    public void createFileMenu()
    {
	fileMenu = new ContextMenu(skin);
	fileMenu.add(new BasicMenuItem("New...", "Ctrl + N", skin));
	
	BasicMenuItem _open = new BasicMenuItem("Open...", "Ctrl + O", skin);
	_open.addListener(new ClickListener()
	{
	    
	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		openOpenDialog();
	    }

	});
	fileMenu.add(_open);
	BasicMenuItem _saveAs = new BasicMenuItem("Save As...", skin);
	_saveAs.addListener(new ClickListener() {
	   
	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		openSaveDialog();
	    }
	    
	});
	fileMenu.add(_saveAs);
	fileMenu.add(new BasicMenuItem("Save", "Ctrl + S", skin));
	fileMenu.add(new BasicMenuItem("Export...", skin));
	fileMenu.add(new BasicMenuItem("Close", skin));
	fileMenu.add(new BasicMenuItem("Close all", skin));
	fileMenu.add(new BasicMenuItem("Exit", skin));
    }
    
    public void openOpenDialog()
    {
	new Thread(new Runnable() {

	    @Override
	    public void run()
	    {
		int _val = fileDialog.showOpenDialog(null);
		if (_val == JFileChooser.APPROVE_OPTION)
		{
		    File _file = fileDialog.getSelectedFile();
		    tabPane.addTab(new MainEditorTab(_file.getName(), new EditorContainer(_file, skin), stage, skin));
		}
	    }

	}).start();
    }
    
    public void openSaveDialog()
    {
	new Thread(new Runnable() {

	    @Override
	    public void run()
	    {
		int _val = fileDialog.showSaveDialog(null);
		if (_val == JFileChooser.APPROVE_OPTION)
		{
		    
		}
	    }

	}).start();
    }

    public void createEditMenu()
    {
	editMenu = new ContextMenu(skin);
	editMenu.add(new BasicMenuItem("Cut", "Ctrl + X", skin));
	editMenu.add(new BasicMenuItem("Copy", "Ctrl + C", skin));
	editMenu.add(new BasicMenuItem("Paste", "Ctrl + V", skin));
    }
    
    public void createViewMenu()
    {
	viewMenu = new ContextMenu(skin);
	CheckboxMenuItem _checkboxMenu = new CheckboxMenuItem("Display grid", "Ctrl + G", skin) {
	    
	    @Override
	    public boolean rules()
	    {
		return editorScreen.isGridDisplay();
	    }
	    
	};
	_checkboxMenu.addListener(new InputListener() {
	    
	    @Override
	    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
	    {
		editorScreen.setGridDisplay(!editorScreen.isGridDisplay());
		
		return true;
	    }
	    
	});
	viewMenu.add(_checkboxMenu);
    }
    
    public void createTilesetMenu()
    {
	tilesetMenu = new ContextMenu(skin);
	tilesetMenu.add(new BasicMenuItem("Manage...", "Ctrl + T", skin));
    }
    
    @Override
    public void render(float delta)
    {
	super.render(delta);
    }

}
