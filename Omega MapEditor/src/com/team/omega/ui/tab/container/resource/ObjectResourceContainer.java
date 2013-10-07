package com.team.omega.ui.tab.container.resource;

import java.io.File;

import javax.swing.JFileChooser;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.team.omega.core.EditorCore;
import com.team.omega.core.project.resource.ObjectResource;
import com.team.omega.core.project.resource.ProjectResource;
import com.team.omega.ui.ProgressWindow;
import com.team.omega.ui.ResourceListRow;
import com.team.omega.ui.tab.container.ResourceContainer;

public class ObjectResourceContainer extends ResourceContainer
{

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
		folderChooser.setSelectedFile(new File("D:\\windows\\Downloads\\tiles\\ground"));
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
		resource.removeObjectResource((ObjectResource) resourceList.getSelection().getResource());
		resourceList.removeSelected();
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

	for (int i = 0; i < toLoad.size; i++)
	{
	    final int _current = i;
	    final String _file = toLoad.get(i);
	    EditorCore.getInstance().getResourceLoader().addToQueue(new Runnable() {

		@Override
		public void run()
		{
		    AssetManager _assets = EditorCore.getInstance().getExternalAssetManager();

		    _assets.load(_file, Texture.class);
		    _assets.finishLoading();

		    ObjectResource _resource = new ObjectResource(_assets.get(_file, Texture.class));
		    resource.getObjectResources().add(_resource);
		    resourceList.addItem(new ResourceListRow(_resource, skin));
		    
		    progress.getProgressBar().setPercent((float) _current / (toLoad.size - 1));
		}

	    });
	}
	
	EditorCore.getInstance().getResourceLoader().addToQueue(new Runnable() {

	    @Override
	    public void run()
	    {
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
