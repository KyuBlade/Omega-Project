package com.team.omega.core.project;

import java.io.File;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.team.omega.core.EditorCore;
import com.team.omega.core.screen.InterfaceScreen;


public class ProjectHandler
{

    private File file;
    private SAXBuilder builder;
    private Document document;
    
    private ProjectReader projectReader;
    private ProjectWriter projectWriter;
    
    public ProjectHandler(File file)
    {
	builder = new SAXBuilder();
	try
	{
	    document = builder.build(file);
	} catch (JDOMException | IOException e)
	{
	    EditorCore.getInstance().getScreenManager().getScreen(InterfaceScreen.class).showPopup("Error", "Error when loading file.\n\n" + e.getMessage());
	}
	
	projectReader = new ProjectReader(document);
	projectWriter = new ProjectWriter(document, file);
    }
    
    public void save()
    {
	try
	{
	    projectWriter.save();
	} catch (IOException e)
	{
	    EditorCore.getInstance().getScreenManager().getScreen(InterfaceScreen.class).showPopup("Saving error", "Error when saving file\n\n " + e.getMessage());
	}
    }
    
}
