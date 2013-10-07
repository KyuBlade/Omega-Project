package com.team.omega.core.project.resource;

import java.io.File;

import javax.swing.filechooser.FileFilter;


public class ResourceFileFilter extends FileFilter
{

    @Override
    public boolean accept(File file)
    {
	if((file.isFile() && file.getName().endsWith(".png")) || file.isDirectory())	    
	    return true;
	
	return false;
    }

    @Override
    public String getDescription()
    {
	return "Image Resource";
    }
    
}
