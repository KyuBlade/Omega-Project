package com.team.omega.core.project.resource;

import com.badlogic.gdx.utils.Array;

public class ProjectResource
{
    
    private Array<TerrainResource> terrainResources = new Array<>();
    private Array<WallResource> wallResources = new Array<>();
    private Array<ObjectResource> objectResources = new Array<>();

    public ProjectResource()
    {
	
    }

    public Array<TerrainResource> getTerrainResources()
    {
	return terrainResources;
    }
    
    public void addTerrainResource(TerrainResource resource)
    {
	terrainResources.add(resource);
    }
    
    public void removeTerrainResource(TerrainResource resource)
    {
	terrainResources.removeValue(resource, false);
    }

    public Array<WallResource> getWallResources()
    {
	return wallResources;
    }
    
    public void addWallResource(WallResource resource)
    {
	wallResources.add(resource);
    }
    
    public void removeWallResource(WallResource resource)
    {
	wallResources.removeValue(resource, false);
    }

    public Array<ObjectResource> getObjectResources()
    {
	return objectResources;
    }
    
    public void addObjectResource(ObjectResource resource)
    {
	objectResources.add(resource);
    }
    
    public void removeObjectResource(ObjectResource resource)
    {
	objectResources.removeValue(resource, false);
    }

}
