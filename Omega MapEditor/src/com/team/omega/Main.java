package com.team.omega;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.team.omega.core.EditorCore;


public class Main
{

    public static void main(String[] args)
    {
	LwjglApplicationConfiguration _config = new LwjglApplicationConfiguration();
	_config.width = 1280;
	_config.height = 720;
	_config.foregroundFPS = 0;
	_config.vSyncEnabled = false;
	_config.title = "Omega Map Editor";
	
	new LwjglApplication(EditorCore.getInstance(), _config);
    }

}
