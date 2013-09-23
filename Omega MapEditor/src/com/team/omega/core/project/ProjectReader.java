package com.team.omega.core.project;

import org.jdom2.Document;


public class ProjectReader
{

    private Document document;
    private ProjectData projectData;
    
    public ProjectReader(Document document, ProjectData projectData)
    {
	this.document = document;
	this.projectData = projectData;
	
	initialize();
    }
    
    public void initialize()
    {
	String[] _split = document.getBaseURI().split("\\/");
	projectData.setName(_split[_split.length - 1]);
    }
    
}
