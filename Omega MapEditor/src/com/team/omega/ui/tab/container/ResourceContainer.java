package com.team.omega.ui.tab.container;

import javax.swing.JFileChooser;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.team.omega.core.project.resource.ProjectResource;
import com.team.omega.core.project.resource.ResourceFileFilter;
import com.team.omega.ui.ProgressWindow;
import com.team.omega.ui.ResourceListRow;
import com.team.omega.ui.base.list.AdvancedList;
import com.team.omega.ui.base.panel.Panel;
import com.team.omega.ui.base.tab.TabContainer;


public class ResourceContainer extends TabContainer
{

    protected Skin skin;
    
    protected JFileChooser fileChooser;
    protected JFileChooser folderChooser;
    
    protected ProjectResource resource;
    
    private Panel listPanel;
    protected ScrollPane listScroll;
    private Table rightMenu;
    
    protected AdvancedList<ResourceListRow> resourceList;
    protected TextButton addResource;
    protected TextButton addFolderResource;
    protected TextButton removeResource;
    
    protected ProgressWindow progress;
    protected Array<String> toLoad = new Array<>(); 
    
    public ResourceContainer(ProjectResource resource, Skin skin)
    {
	super(skin);
	
	this.skin = skin;
	this.resource = resource;
	
	fileChooser = new JFileChooser();
	fileChooser.setFileFilter(new ResourceFileFilter());
	fileChooser.setAcceptAllFileFilterUsed(false);
	fileChooser.setMultiSelectionEnabled(true);
	
	folderChooser = new JFileChooser();
	folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	folderChooser.setMultiSelectionEnabled(true);
	
	listPanel = new Panel(skin, "blank");
	listScroll = new ScrollPane(listPanel, skin);
	listScroll.setFadeScrollBars(false);
	listScroll.setForceScroll(false, false);
	listScroll.setFlickScroll(false);
	rightMenu = new Table(skin);
	
	resourceList = new AdvancedList<>();
	addResource = new TextButton("Add", skin);
	addFolderResource = new TextButton("Add folder", skin);
	removeResource = new TextButton("Remove", skin);
	
	add(listScroll).expand().fill();
	add(rightMenu).top();
	
	listPanel.add(resourceList).expand().fill();
	rightMenu.defaults().minWidth(100f);
	rightMenu.add(addResource).row();
	rightMenu.add(addFolderResource).row();
	rightMenu.add(removeResource).row();
    }

    public ProjectResource getResource()
    {
        return resource;
    }

}
