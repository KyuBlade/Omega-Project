package com.team.omega.core.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.badlogic.gdx.Gdx;


public class ProjectWriter
{

    private File file;
    private Document document;
    private ProjectData projectData;
    
    public ProjectWriter(Document document, ProjectData projectData)
    {
	this(document, projectData, null);
    }
    
    public ProjectWriter(Document document, ProjectData projectData, File file)
    {
	this.document = document;
	this.projectData = projectData;
	this.file = file;
    }
    
    /**
     * Try to save if already saved
     * @return false if saved , true if need to specify where to save
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean save() throws FileNotFoundException, IOException
    {
	if(file == null)
	    return true;
	
	save(file);
	return false;
    }
    
    /**
     * Save the project
     * @param file destination to save
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void save(File file) throws FileNotFoundException, IOException
    {
	XMLOutputter output = new XMLOutputter(Format.getPrettyFormat());
	output.output(document, new FileOutputStream(file));
	
	Gdx.app.debug("Save", "Project saved");
    }
    
}
