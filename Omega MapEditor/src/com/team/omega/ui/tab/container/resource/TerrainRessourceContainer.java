package com.team.omega.ui.tab.container.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.SnapshotArray;
import com.team.omega.core.EditorCore;
import com.team.omega.core.project.resource.ProjectResource;
import com.team.omega.core.project.resource.Resource;
import com.team.omega.core.project.resource.ResourceLoader;
import com.team.omega.core.project.resource.TerrainResource;
import com.team.omega.core.screen.InterfaceScreen;
import com.team.omega.ui.ProgressWindow;
import com.team.omega.ui.ResourceGridItem;
import com.team.omega.ui.ResourceListRow;
import com.team.omega.ui.base.grid.GridSelection;
import com.team.omega.ui.tab.container.EditorContainer;
import com.team.omega.ui.tab.container.resource.selection.TerrainResourceSelectionContainer;


public class TerrainRessourceContainer extends ResourceContainer
{

    public TerrainRessourceContainer(final ProjectResource resource, Skin skin)
    {
	super(resource, skin);
	
	removeResource.addListener(new ClickListener() {

	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		GridSelection<ResourceGridItem> _grid = ((EditorContainer) EditorCore.getInstance().getScreenManager()
			.getScreen(InterfaceScreen.class).getProjectTabPane().getCurrentTab())
			.getResourceSelection().getTab(TerrainResourceSelectionContainer.class).getGridSelection();
		
		// Get selected resources
		SnapshotArray<ResourceListRow> _resourceRows = resourceList.getSelection();
		for (ResourceListRow _listRow : _resourceRows)
		{
		    Resource _resource = _listRow.getResource();

		    // Remove from the list
		    resourceList.removeItem(_listRow);

		    // Remove from the GridSelection
		    _grid.remove(resourceGridBind.get(_resource));
		    resourceGridBind.removeKey(_resource);

		    // Remove from project resources
		    resource.removeResource((TerrainResource) _resource);
		    
		    // Release from memory
		    AssetManager _assets = EditorCore.getInstance().getExternalAssetManager();
		    _assets.unload(_assets.getAssetFileName(_resource.getTextureRegion().getTexture()));
		}
	    }

	});
    }

    @Override
    protected void loadSelection()
    {
	progress = new ProgressWindow(getStage(), skin);
	progress.show();
	
	final EditorContainer _container = ((EditorContainer) EditorCore.getInstance().getScreenManager().getScreen(InterfaceScreen.class).getProjectTabPane().getCurrentTab());
	final ResourceLoader _loader = EditorCore.getInstance().getResourceLoader();
	final AssetManager _assets = EditorCore.getInstance().getExternalAssetManager();
	
	for (int i = 0; i < toLoad.size; i++)
	{
	    final int _current = i;
	    final int _total = toLoad.size;
	    _loader.addToQueue(new Runnable() {

		@Override
		public void run()
		{
		    String _file = toLoad.first();
		    toLoad.removeIndex(0);
		    
		    _assets.load(_file, Texture.class);
		    _assets.finishLoading();

		    TerrainResource _resource = new TerrainResource(_assets.get(_file, Texture.class));
		    resource.addResource(_resource);
		    resourceList.addItem(new ResourceListRow(_resource, skin));
		    
		    ResourceGridItem _item = new ResourceGridItem(skin, _resource.getTextureRegion());
		    _container.getResourceSelection().getTab(TerrainResourceSelectionContainer.class).getGridSelection().add(_item);
		    resourceGridBind.put(_resource, _item);
		    
		    String[] _filename = _file.split("\\/");
		    progress.setFilename(_filename[_filename.length - 1]);
		    progress.getProgressBar().setPercent((float) _current / (_total - 1));
		}

	    });
	}
	
	_loader.addToQueue(new Runnable() {

	    @Override
	    public void run()
	    {
		progress.hide();
	    }

	});
    }

    @Override
    public void populateList()
    {
    }

}
