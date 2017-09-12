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

   @SuppressWarnings("unchecked")
   public <T extends Resource> Array<T> getResources(Class<T> clazz)
   {
       if(TerrainResource.class.isInstance(clazz))
	   return (Array<T>) terrainResources;
       else if(WallResource.class.isInstance(clazz))
	   return (Array<T>) wallResources;
       else if(ObjectResource.class.isInstance(clazz))
	   return (Array<T>) objectResources;
       
       return null;
   }
   
   public <T extends Resource> void addResource(T resource)
   {
       if(resource instanceof TerrainResource)
	   terrainResources.add((TerrainResource) resource);
       else if(resource instanceof WallResource)
	   wallResources.add((WallResource) resource);
       else if(resource instanceof ObjectResource)
	   objectResources.add((ObjectResource) resource);
   }
   
   public <T extends Resource> void removeResource(T resource)
   {
       if(resource instanceof TerrainResource)
	   terrainResources.removeValue((TerrainResource) resource, true);
       else if(resource instanceof WallResource)
	   wallResources.removeValue((WallResource) resource, true);
       else if(resource instanceof ObjectResource)
	   objectResources.removeValue((ObjectResource) resource, true);
   }

}
