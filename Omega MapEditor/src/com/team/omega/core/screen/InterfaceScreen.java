package com.team.omega.core.screen;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ArrayMap.Values;
import com.team.omega.core.ProjectInstance;
import com.team.omega.core.project.ProjectFileFilter;
import com.team.omega.ui.EditorContainer;
import com.team.omega.ui.MainEditorTab;
import com.team.omega.ui.base.menu.BasicMenuItem;
import com.team.omega.ui.base.menu.CheckboxMenuItem;
import com.team.omega.ui.base.menu.ContextMenu;
import com.team.omega.ui.base.menu.MenuBar;
import com.team.omega.ui.base.menu.ToolBar;
import com.team.omega.ui.base.tab.Tab;
import com.team.omega.ui.base.tab.TabContainer;
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
		openSaveDialog(true);
	    }
	    
	});
	openButton = new ImageButton(skin, "open");
	openButton.addListener(new ClickListener() {
	    
	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		openOpenDialog();
	    }
	    
	});
	saveButton = new ImageButton(skin, "save");
	saveButton.addListener(new ClickListener() {
	    
	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		saveProcess(getCurrentProject());
	    }
	    
	});
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
	tabPane.addTab(new MainEditorTab("Test", new EditorContainer(new File("C:\\Users\\Kyu\\Documents\\test.omp"), skin), stage, skin));
	tabPane.addTab(new MainEditorTab("Test 2", new EditorContainer(new File("C:\\Users\\Kyu\\Documents\\test2.omp"), skin), stage, skin));
	layout.add(tabPane).expand().fill();
    }
    
    public void createFileMenu()
    {
	fileMenu = new ContextMenu(skin);
	BasicMenuItem _new = new BasicMenuItem("New...", "Ctrl + N", skin);
	_new.addListener(new ClickListener()
	{
	    
	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		openSaveDialog(true);
	    }

	});
	fileMenu.add(_new);
	
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
		openSaveDialog(false);
	    }
	    
	});
	fileMenu.add(_saveAs);
	BasicMenuItem _save = new BasicMenuItem("Save", "Ctrl + S", skin);
	_save.addListener(new ClickListener() {
	    
	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		saveProcess(getCurrentProject());
	    }
	    
	});
	fileMenu.add(_save);
	fileMenu.add(new BasicMenuItem("Export...", skin));
	BasicMenuItem _close = new BasicMenuItem("Close", skin);
	_close.addListener(new ClickListener() {
	    
	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		ProjectInstance _project = getCurrentProject();
		if(_project != null)
		    _project.close();
	    }
	    
	});
	fileMenu.add(_close);
	
	BasicMenuItem _closeAll = new BasicMenuItem("Close All", skin);
	_closeAll.addListener(new ClickListener() {
	    
	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		ProjectInstance.closeAll();
	    }
	    
	});
	fileMenu.add(_closeAll);
	fileMenu.add(new BasicMenuItem("Exit", skin));
    }
    
    /**
     * Open a file chooser to open a project
     */
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
		    
		    // Check if project is already open
		    Values<TabContainer> _containers = tabPane.getTabBind().values();
		    for(TabContainer _container : _containers)
		    {
			if(((EditorContainer) _container).getProject().getProjectHandler().getProjectData().getName().equalsIgnoreCase(_file.getName()))
			{
			    tabPane.setCurrentTab(tabPane.getTabBind().getKey(_container, false));
			    
			    return;
			}
		    }
		    
		    tabPane.addTab(new MainEditorTab(_file.getName(), new EditorContainer(_file, skin), stage, skin));
		}
	    }

	}).start();
    }
    
    /**
     * Open a file chooser to save project
     * @param newProject true if it's a new project, else false
     */
    public void openSaveDialog(final boolean newProject)
    {
	if(!newProject && getCurrentProject() == null)
	    return;
	
	new Thread(new Runnable() {

	    @Override
	    public void run()
	    {
		int _val = fileDialog.showSaveDialog(null);
		if (_val == JFileChooser.APPROVE_OPTION)
		{
		    File _file = fileDialog.getSelectedFile();
		    if(_file.exists())
		    {
			int _result = JOptionPane.showConfirmDialog(null, "File exists, overwrite?", "File exists", JOptionPane.YES_NO_OPTION);
		        if(_result == JOptionPane.NO_OPTION)
		            return;
		    }
			
		    if(newProject)
			tabPane.addTab(new MainEditorTab(_file.getName(), new EditorContainer(_file, skin), stage, skin));
		    else
		    {
			getCurrentProject().saveAs(_file);
		    }
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
	_checkboxMenu.addListener(new ClickListener() {
	    
	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		editorScreen.setGridDisplay(!editorScreen.isGridDisplay());
	    }
	    
	});
	viewMenu.add(_checkboxMenu);
    }
    
    public void createTilesetMenu()
    {
	tilesetMenu = new ContextMenu(skin);
	tilesetMenu.add(new BasicMenuItem("Manage...", "Ctrl + T", skin));
    }
    
    public synchronized void saveProcess(ProjectInstance project)
    {
	if(project == null)
	    return;
	
	boolean _needSave = project.save();
	if(_needSave)
	    openSaveDialog(false);
    }
    
    public ProjectInstance getCurrentProject()
    {
	Tab _curTab = tabPane.getCurrentTab();
	if(_curTab == null)
	    return null;
	
	return ((EditorContainer) _curTab.getContainer()).getProject();
    }
    
    public TabPane getTabPane()
    {
	return tabPane;
    }
    
    @Override
    public void render(float delta)
    {
	super.render(delta);
    }

}
