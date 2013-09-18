package com.team.omega.core.renderer;

import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C4;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U4;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V4;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.X1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.X2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.X3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.X4;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.Y1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.Y2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.Y3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.Y4;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

public class IsometricMapRenderer extends IsometricTiledMapRenderer implements Renderer
{

    private float[] vertices = new float[20];
    private TiledMapTileLayer currentLayer;
    
    public IsometricMapRenderer(TiledMap map)
    {
	super(map);
    }

    public IsometricMapRenderer(TiledMap map, SpriteBatch spriteBatch)
    {
	super(map, spriteBatch);
    }

    public IsometricMapRenderer(TiledMap map, float unitScale)
    {
	super(map, unitScale);
    }

    public IsometricMapRenderer(TiledMap map, float unitScale, SpriteBatch spriteBatch)
    {
	super(map, unitScale, spriteBatch);
    }

    @Override
    public void renderTileLayer(TiledMapTileLayer layer)
    {
	currentLayer = layer;
	
	float tileWidth = layer.getTileWidth() * unitScale;
	float tileHeight = layer.getTileHeight() * unitScale;
	float halfTileWidth = tileWidth * 0.5f;
	float halfTileHeight = tileHeight * 0.5f;
	
	int col1 = 0;
	int col2 = layer.getWidth() - 1;
	
	/*int col1 = Math.round(viewBounds.x / tileWidth);
	int col2 = col1 + Math.round(viewBounds.width / tileWidth);*/
	
	/*int row1 = Math.round(viewBounds.y / tileHeight);
	int row2 = row1 + Math.round(viewBounds.height / tileHeight);*/
	
	int row1 = 0;
	int row2 = layer.getHeight() - 1;
	
	viewBounds.x -= 100;
	viewBounds.y -= 100;
	viewBounds.width += 100;
	viewBounds.height += 100;
	
	for (int row = row2; row >= row1; row--)
	{ 
	    for (int col = col1; col <= col2; col++)
	    {
		float x = (col + row) * halfTileWidth;
		float y = (row - col) * halfTileHeight;
		
		if(!viewBounds.contains(x, y))
		    continue;

		final TiledMapTileLayer.Cell cell = layer.getCell(col, row);
		if (cell == null)
		    continue;
		renderTile(cell, x, y);
		
	    }
	}
    }
    
    public void renderTile(Cell cell, float x, float y)
    {
	final TiledMapTile tile = cell.getTile();
	if (tile != null)
	{
	    final Color batchColor = spriteBatch.getColor();
	    final float color = Color.toFloatBits(batchColor.r, batchColor.g, batchColor.b, batchColor.a * currentLayer.getOpacity());
		
	    final boolean flipX = cell.getFlipHorizontally();
	    final boolean flipY = cell.getFlipVertically();
	    final int rotations = cell.getRotation();

	    TextureRegion region = tile.getTextureRegion();

	    float x1 = x;
	    float y1 = y;
	    float x2 = x1 + region.getRegionWidth() * unitScale;
	    float y2 = y1 + region.getRegionHeight() * unitScale;

	    float u1 = region.getU();
	    float v1 = region.getV2();
	    float u2 = region.getU2();
	    float v2 = region.getV();

	    vertices[X1] = x1;
	    vertices[Y1] = y1;
	    vertices[C1] = color;
	    vertices[U1] = u1;
	    vertices[V1] = v1;

	    vertices[X2] = x1;
	    vertices[Y2] = y2;
	    vertices[C2] = color;
	    vertices[U2] = u1;
	    vertices[V2] = v2;

	    vertices[X3] = x2;
	    vertices[Y3] = y2;
	    vertices[C3] = color;
	    vertices[U3] = u2;
	    vertices[V3] = v2;

	    vertices[X4] = x2;
	    vertices[Y4] = y1;
	    vertices[C4] = color;
	    vertices[U4] = u2;
	    vertices[V4] = v1;

	    if (flipX)
	    {
		float temp = vertices[U1];
		vertices[U1] = vertices[U3];
		vertices[U3] = temp;
		temp = vertices[U2];
		vertices[U2] = vertices[U4];
		vertices[U4] = temp;
	    }
	    if (flipY)
	    {
		float temp = vertices[V1];
		vertices[V1] = vertices[V3];
		vertices[V3] = temp;
		temp = vertices[V2];
		vertices[V2] = vertices[V4];
		vertices[V4] = temp;
	    }
	    if (rotations != 0)
	    {
		switch (rotations)
		{
		    case Cell.ROTATE_90:
		    {
			float tempV = vertices[V1];
			vertices[V1] = vertices[V2];
			vertices[V2] = vertices[V3];
			vertices[V3] = vertices[V4];
			vertices[V4] = tempV;

			float tempU = vertices[U1];
			vertices[U1] = vertices[U2];
			vertices[U2] = vertices[U3];
			vertices[U3] = vertices[U4];
			vertices[U4] = tempU;
			break;
		    }
		    case Cell.ROTATE_180:
		    {
			float tempU = vertices[U1];
			vertices[U1] = vertices[U3];
			vertices[U3] = tempU;
			tempU = vertices[U2];
			vertices[U2] = vertices[U4];
			vertices[U4] = tempU;
			float tempV = vertices[V1];
			vertices[V1] = vertices[V3];
			vertices[V3] = tempV;
			tempV = vertices[V2];
			vertices[V2] = vertices[V4];
			vertices[V4] = tempV;
			break;
		    }
		    case Cell.ROTATE_270:
		    {
			float tempV = vertices[V1];
			vertices[V1] = vertices[V4];
			vertices[V4] = vertices[V3];
			vertices[V3] = vertices[V2];
			vertices[V2] = tempV;

			float tempU = vertices[U1];
			vertices[U1] = vertices[U4];
			vertices[U4] = vertices[U3];
			vertices[U3] = vertices[U2];
			vertices[U2] = tempU;
			break;
		    }
		}
	    }
	    spriteBatch.draw(region.getTexture(), vertices, 0, 20);
	}
    }

    @Override
    public void render(float delta)
    {
	super.render();
    }

    @Override
    public void preRender()
    {
	
    }

}
