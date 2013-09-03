package com.team.omega.core.entity.component.render;

import com.artemis.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraComponent extends Component
{

    private OrthographicCamera cam;

    public CameraComponent(OrthographicCamera cam)
    {
	this.cam = cam;
    }

    public OrthographicCamera getCamera()
    {
	return cam;
    }

    public void setCamera(OrthographicCamera cam)
    {
	this.cam = cam;
    }

}
