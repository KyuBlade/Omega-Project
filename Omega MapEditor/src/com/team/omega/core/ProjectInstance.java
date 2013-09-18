package com.team.omega.core;

import java.io.File;

import com.team.omega.core.project.ProjectHandler;


public class ProjectInstance
{

    private File projectFile;
    private ProjectHandler handler;
    
    public ProjectInstance(File file)
    {
	this.projectFile = file;
	this.handler = new ProjectHandler(file);
    }
    
    public void save()
    {
	handler.save();
    }
    
}
