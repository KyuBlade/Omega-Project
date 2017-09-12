package com.team.omega.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class IsometricUtil
{
    
    private static final float cellWidth = 32f, cellHeight = 32f;
    
    public static Vector2 isoToWorld(Vector2 isoPoint)
    {
	return isoToWorld(isoPoint.x, isoPoint.y);
    }
    
    public static Vector2 isoToWorld(float x, float y)
    {
	return new Vector2(x, y);
    }
    
    public static Vector3 worldToIso(Vector3 worldPoint)
    {
	
	return worldPoint;
    }
    
}
