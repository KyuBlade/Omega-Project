package com.team.omega.core.project;

import java.io.File;

import javax.swing.filechooser.FileFilter;


public class ProjectFileFilter extends FileFilter
{

    @Override
    public boolean accept(File file)
    {
	if(file.isFile() && file.getName().endsWith(".omp"))	    
	    return true;
	
	return false;
    }

    @Override
    public String getDescription()
    {
	return "Omega Map Project";
    }
    
}
