package com.team.omega.core.renderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class GridRenderer
{

    private ShapeRenderer gridRenderer;
    private OrthographicCamera cam;
    
    private int cellWidth = 32, cellHeight = 32;
    private int gridSize = 32;
    
    public GridRenderer(OrthographicCamera cam)
    {
	this.cam = cam;
	gridRenderer = new ShapeRenderer();
	gridRenderer.scale(1f, 0.5f, 0f);
	gridRenderer.rotate(0f, 0f, 1f, 45f);
	gridRenderer.setColor(1f, 1f, 1f, 1f);
    }
    
    public void render(float delta)
    {
	gridRenderer.setProjectionMatrix(cam.combined);
	gridRenderer.begin(ShapeType.Line);
	for(float x = -gridSize / 2, y = 0f; x <= 0f; x++, y++)
	    gridRenderer.line(x * cellWidth, -((y == gridSize / 2) ? y - 1 : y)* cellHeight, x * cellWidth, ((y == gridSize / 2) ? y - 1 : y) * cellHeight);
	for(float x = 0f, y = -gridSize / 2; y <= 0f; x++, y++)
	    gridRenderer.line(-((x == gridSize / 2) ? x - 1 : x) * cellWidth, y * cellHeight, ((x == gridSize / 2) ? x - 1 : x) * cellWidth, y * cellHeight);
	
	for(float x = 1f, y = -gridSize / 2 + 1; x <= gridSize / 2; x++, y++)
	    gridRenderer.line(x * cellWidth, y * cellHeight, x * cellWidth, -y * cellHeight);
	for(float x = -gridSize / 2 + 1, y = 1f; y <= gridSize / 2; x++, y++)
	    gridRenderer.line(x * cellWidth, y * cellHeight, -x * cellWidth, y * cellHeight);
	gridRenderer.end();
	
	// Origin
	gridRenderer.begin(ShapeType.Line);
	gridRenderer.rect(-5f, -5f, 10f, 10f);
	gridRenderer.end();
    }
    
}
