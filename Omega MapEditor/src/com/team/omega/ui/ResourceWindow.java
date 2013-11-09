package com.team.omega.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ArrayMap;
import com.team.omega.core.ProjectInstance;
import com.team.omega.ui.base.tab.Tab;
import com.team.omega.ui.base.tab.TabPane;
import com.team.omega.ui.tab.container.resource.ObjectResourceContainer;


public class ResourceWindow extends Window
{
    
    private static ArrayMap<ProjectInstance, ResourceWindow> windowBind = new ArrayMap<>();
    
    private ProjectInstance project;
    
    private TabPane tabPane;
    private TextButton closeButton;
    
    public ResourceWindow(ProjectInstance project, Skin skin)
    {
	this(project, skin, "default");
    }
    
    public ResourceWindow(ProjectInstance project, Skin skin, String styleName)
    {
	super("Resource manager", skin, styleName);
	
	this.project = project;
	
	setModal(true);
	setVisible(false);
	
	tabPane = new TabPane(skin);
	tabPane.addTab(new Tab("Object", new ObjectResourceContainer(project.getProjectResource(), skin), skin));
	
	add(tabPane).expand().fill().row();
	
	closeButton = new TextButton("Close", skin);
	closeButton.addListener(new ClickListener() {
	    
	    public void clicked (InputEvent event, float x, float y)
	    {
		setVisible(false);
	    }
	    
	});
	add(closeButton);
	
	setSize(Gdx.graphics.getWidth() / 1.5f, Gdx.graphics.getHeight() / 1.5f);
	setPosition(Gdx.graphics.getWidth() / 2 - getWidth() / 2, Gdx.graphics.getHeight() / 2 - getHeight() / 2);
    }
    
    public static ResourceWindow getInstance(ProjectInstance project, Skin skin)
    {
	ResourceWindow _window = windowBind.get(project);
	if(_window == null)
	{
	    _window = new ResourceWindow(project, skin);
	    windowBind.put(project, _window);
	}
	
	return _window;
    }
    
}
