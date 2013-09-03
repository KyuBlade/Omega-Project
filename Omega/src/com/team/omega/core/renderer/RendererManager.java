package com.team.omega.core.renderer;


public class RendererManager
{

    private CameraRenderer camRenderer;
    private IsometricMapRenderer mapRenderer;
    private PlayerRenderer playerRenderer;

    public RendererManager()
    {
	
    }

    public void render(float delta)
    {
	camRenderer.render(delta);
	
	mapRenderer.setView(camRenderer.getCamera());
	mapRenderer.render(delta);
	playerRenderer.render(delta);
    }

    public CameraRenderer getCamRenderer()
    {
	return camRenderer;
    }

    public void setCamRenderer(CameraRenderer camRenderer)
    {
	this.camRenderer = camRenderer;
    }

    public IsometricMapRenderer getMapRenderer()
    {
	return mapRenderer;
    }

    public void setMapRenderer(IsometricMapRenderer mapRenderer)
    {
	this.mapRenderer = mapRenderer;
    }

    public PlayerRenderer getPlayerRenderer()
    {
	return playerRenderer;
    }

    public void setPlayerRenderer(PlayerRenderer playerRenderer)
    {
	this.playerRenderer = playerRenderer;
    }

}
