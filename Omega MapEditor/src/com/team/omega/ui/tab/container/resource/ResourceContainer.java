package com.team.omega.ui.tab.container.resource;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.team.omega.core.EditorCore;
import com.team.omega.core.project.resource.ProjectResource;
import com.team.omega.core.project.resource.Resource;
import com.team.omega.core.project.resource.ResourceFileFilter;
import com.team.omega.ui.ProgressWindow;
import com.team.omega.ui.ResourceGridItem;
import com.team.omega.ui.ResourceListRow;
import com.team.omega.ui.base.SelectionMode;
import com.team.omega.ui.base.list.AdvancedList;
import com.team.omega.ui.base.panel.Panel;
import com.team.omega.ui.base.tab.TabContainer;


public abstract class ResourceContainer extends TabContainer
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
    protected ArrayMap<Resource, ResourceGridItem> resourceGridBind = new ArrayMap<>();
    
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
	resourceList.setSelectionMode(SelectionMode.MULTI);
	
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
	
	addResource.addListener(new ClickListener() {

	    public void clicked(InputEvent event, float x, float y)
	    {
		EventQueue.invokeLater(new Runnable() {

		    @Override
		    public void run()
		    {
			int _val = fileChooser.showOpenDialog(null);
			if (_val == JFileChooser.APPROVE_OPTION)
			{
			    File[] _files = fileChooser.getSelectedFiles();
			    for (File _file : _files)
				processSelection(_file);

			    loadSelection();
			}
		    }

		});
	    }

	});
	addFolderResource.addListener(new ClickListener() {

	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		folderChooser.setSelectedFile(new File("D:\\windows\\Downloads\\tiles\\sample"));
		EventQueue.invokeLater(new Runnable() {

		    @Override
		    public void run()
		    {
			int _val = folderChooser.showOpenDialog(null);
			if (_val == JFileChooser.APPROVE_OPTION)
			{
			    File[] _files = folderChooser.getSelectedFiles();
			    for (File _file : _files)
				processSelection(_file);

			    loadSelection();
			}
		    }

		});
	    }

	});
    }
    
    protected void processSelection(File file)
    {
	processSelection(file, true);
    }

    protected void processSelection(final File file, boolean recursively)
    {
	if (file.isDirectory() && recursively)
	{
	    for (File _file : file.listFiles())
		processSelection(_file, false);
	}

	if (!file.getName().endsWith(".png"))
	    return;

	final AssetManager _assets = EditorCore.getInstance().getExternalAssetManager();

	if (_assets.isLoaded(file.getPath()))
	    return;

	toLoad.add(file.getPath().replace("\\", "/"));
    }
    
    protected abstract void loadSelection();
    public abstract void populateList();

    public ProjectResource getResource()
    {
        return resource;
    }

}
