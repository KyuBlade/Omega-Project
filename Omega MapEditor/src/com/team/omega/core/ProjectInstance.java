package com.team.omega.core;

import java.io.File;

import com.team.omega.core.project.ProjectHandler;


public class ProjectInstance
{

    private ProjectHandler handler;
    
    private boolean needSave;
    
    public ProjectInstance(File file)
    {
	this.handler = new ProjectHandler(file);
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
    
}
