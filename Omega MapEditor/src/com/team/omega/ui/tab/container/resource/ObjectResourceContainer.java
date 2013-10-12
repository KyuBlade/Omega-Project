package com.team.omega.ui.tab.container.resource;

import java.io.File;

import javax.swing.JFileChooser;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ArrayMap;
import com.team.omega.core.EditorCore;
import com.team.omega.core.project.resource.ObjectResource;
import com.team.omega.core.project.resource.ProjectResource;
import com.team.omega.core.project.resource.Resource;
import com.team.omega.core.project.resource.ResourceLoader;
import com.team.omega.core.screen.InterfaceScreen;
import com.team.omega.ui.ProgressWindow;
import com.team.omega.ui.ResourceGridItem;
import com.team.omega.ui.ResourceListRow;
import com.team.omega.ui.base.grid.GridSelection;
import com.team.omega.ui.tab.container.EditorContainer;
import com.team.omega.ui.tab.container.ResourceContainer;

public class ObjectResourceContainer extends ResourceContainer
{

    private ArrayMap<Resource, ResourceGridItem> resourceGridBind = new ArrayMap<>();
    
    public ObjectResourceContainer(final ProjectResource resource, Skin skin)
    {
	super(resource, skin);

	addResource.addListener(new ClickListener() {

	    public void clicked(InputEvent event, float x, float y)
	    {
		new Thread(new Runnable() {

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

		}).start();

	    }

	});
	addFolderResource.addListener(new ClickListener() {

	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		folderChooser.setSelectedFile(new File("D:\\windows\\Downloads\\tiles\\sample"));
		new Thread(new Runnable() {

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

		}).start();
	    }

	});
	removeResource.addListener(new ClickListener() {

	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		final GridSelection<ResourceGridItem> _grid = ((EditorContainer) EditorCore.getInstance().getScreenManager().getScreen(InterfaceScreen.class).getProjectTabPane().getCurrentTab().getContainer()).getResourceGrid();
		
		if(resourceList.getSelection() == null)
		    return;
		
		ObjectResource _resource = (ObjectResource) resourceList.getSelection().getResource();
		resource.removeObjectResource(_resource);
		resourceList.removeSelected();
		
		_grid.remove(resourceGridBind.get(_resource), true);
		resourceGridBind.removeKey(_resource);		
	    }

	});
    }

    private void processSelection(File file)
    {
	processSelection(file, true);
    }

    private void processSelection(final File file, boolean recursively)
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

    private void loadSelection()
    {
	progress = new ProgressWindow(getStage(), skin);
	progress.show();
	
	final EditorContainer _container = ((EditorContainer) EditorCore.getInstance().getScreenManager().getScreen(InterfaceScreen.class).getProjectTabPane().getCurrentTab().getContainer());
	final ResourceLoader _loader = EditorCore.getInstance().getResourceLoader();
	final AssetManager _assets = EditorCore.getInstance().getExternalAssetManager();
	
	for (int i = 0; i < toLoad.size; i++)
	{
	    final int _current = i;
	    final String _file = toLoad.get(i);
	    _loader.addToQueue(new Runnable() {

		@Override
		public void run()
		{
		    _assets.load(_file, Texture.class);
		    _assets.finishLoading();

		    ObjectResource _resource = new ObjectResource(_assets.get(_file, Texture.class));
		    resource.getObjectResources().add(_resource);
		    resourceList.addItem(new ResourceListRow(_resource, skin));
		    
		    ResourceGridItem _item = new ResourceGridItem(skin, _resource.getTextureRegion());
		    _container.getResourceGrid().add(_item);
		    resourceGridBind.put(_resource, _item);
		    
		    progress.getProgressBar().setPercent((float) _current / (toLoad.size - 1));
		}

	    });
	}
	
	_loader.addToQueue(new Runnable() {

	    @Override
	    public void run()
	    {
		_container.getResourceGrid().update();
		progress.hide();
	    }

	});
    }

    public void populateList()
    {
	for (ObjectResource _resource : resource.getObjectResources())
	{
	    resourceList.getItems().add(new ResourceListRow(_resource, skin));
	}
    }

}
