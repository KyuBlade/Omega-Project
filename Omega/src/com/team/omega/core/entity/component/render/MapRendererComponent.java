package com.team.omega.core.entity.component.render;

import com.artemis.Component;
import com.team.omega.core.renderer.IsometricMapRenderer;

public class MapRendererComponent extends Component
{

    private IsometricMapRenderer mapRenderer;

    public MapRendererComponent(IsometricMapRenderer mapRenderer)
    {
	this.mapRenderer = mapRenderer;
    }

    public IsometricMapRenderer getMapRenderer()
    {
	return mapRenderer;
    }

}
