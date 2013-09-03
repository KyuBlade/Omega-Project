package com.team.omega.core.asset.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonBinary;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;


public class SkeletonLoader extends SynchronousAssetLoader<Skeleton, SkeletonLoader.Parameters>
{
    
    public static class Parameters extends AssetLoaderParameters<Skeleton>
    {
	
    }
    
    public SkeletonLoader(FileHandleResolver resolver)
    {
	super(resolver);
    }
    
    @Override
    public Skeleton load(AssetManager assetManager, String fileName, Parameters parameter)
    {
	
	String _path = null;
	String _ext = null;
	if (fileName.endsWith(".json") || fileName.endsWith(".skel"))
	{
	    _path = fileName.substring(0, fileName.length() - 5);
	    _ext = fileName.substring(fileName.length() - 4);
	}
	else
	{
	    Gdx.app.error("SkeletonLoader", "An unsupported file format are given to load a skeleton.");
	    
	    System.exit(-1);
	}
	
	FileHandle atlasFile = Gdx.files.internal(_path + ".atlas");
	TextureAtlasData data = !atlasFile.exists() ? null : new TextureAtlasData(atlasFile, atlasFile.parent(), false);
	TextureAtlas atlas = new TextureAtlas(data);

	SkeletonData _skeletonData = null;
	if (_ext.equals("json"))
	{
	    SkeletonJson json = new SkeletonJson(atlas);
	    _skeletonData = json.readSkeletonData(Gdx.files.internal(_path + ".json"));
	}
	else if (_ext.equals("skel"))
	{
	    SkeletonBinary binary = new SkeletonBinary(atlas);
	    _skeletonData = binary.readSkeletonData(Gdx.files.internal(_path + ".skel"));
	}

	return new Skeleton(_skeletonData);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, Parameters parameter)
    {
	return null;
    }
    
}
