package com.team.omega.core.entity.component.render;

import com.artemis.Component;
import com.esotericsoftware.spine.Skeleton;
import com.team.omega.core.GameCore;


public class SkeletonComponent extends Component
{
    
    private Skeleton skeleton;

    public SkeletonComponent(String skeleton)
    {
	this.skeleton = GameCore.getInstance().getAssetManager().get(skeleton);
    }

    public Skeleton getSkeleton()
    {
        return skeleton;
    }
    
    public void setSkeleton(Skeleton skeleton)
    {
	this.skeleton = skeleton;
    }
    
}
