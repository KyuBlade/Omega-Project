package com.team.omega.core;

import java.io.File;

import com.badlogic.gdx.utils.ArrayMap;
import com.team.omega.core.project.ProjectHandler;
import com.team.omega.core.project.resource.ProjectResource;
import com.team.omega.core.screen.InterfaceScreen;
import com.team.omega.ui.base.tab.Tab;
import com.team.omega.ui.base.tab.TabContainer;
import com.team.omega.ui.base.tab.TabPane;
import com.team.omega.ui.tab.container.EditorContainer;


public class ProjectInstance
{

    private ProjectHandler handler;
    private ProjectResource resources;
    
    private boolean needSave;
    
    public ProjectInstance(File file)
    {
	handler = new ProjectHandler(file);
	resources = new ProjectResource();
    }
    
    public ProjectHandler getProjectHandler()
    {
	return handler;
    }
    
    public ProjectResource getProjectResource()
    {
	return resources;
    }
    
    public boolean save()
    {
	if(!needSave)
	    return false;
	
	return handler.save();
    }
    
    public void saveAs(File file)
    {
	handler.saveAs(file);
    }
    
    public void close()
    {
	InterfaceScreen _screen = EditorCore.getInstance().getScreenManager().getScreen(InterfaceScreen.class);
	TabPane _tabPane = _screen.getProjectTabPane();
	
	_screen.saveProcess(this);
	
	ArrayMap<Tab, TabContainer> _tabBind = _tabPane.getTabBind();
	for(TabContainer _container : _tabBind.values())
	{
	    EditorContainer _eContainer = (EditorContainer) _container;
	    if(_eContainer.getProject().equals(this))
	    {
		_tabPane.removeTab(_tabBind.getKey(_eContainer, false));
		
		return;
	    }
	}
	
    }
    
    public static void closeAll()
    {
	InterfaceScreen _screen = EditorCore.getInstance().getScreenManager().getScreen(InterfaceScreen.class);
	TabPane _tabPane = _screen.getProjectTabPane();
	
	for(TabContainer _tab : _tabPane.getTabBind().values().toArray())
	    ((EditorContainer) _tab).getProject().close();
    }
    
}
