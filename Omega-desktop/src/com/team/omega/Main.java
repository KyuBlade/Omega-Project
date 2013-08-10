package com.team.omega;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.team.omega.core.GameCore;

public class Main
{

    public static void main(String[] args)
    {
	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	cfg.title = Config.GAME_TITLE;
	cfg.useGL20 = Config.USE_GL20;
	cfg.width = Config.DISPLAY_WIDTH;
	cfg.height = Config.DISPLAY_HEIGHT;
	cfg.vSyncEnabled = false;
	cfg.foregroundFPS = 0;
	cfg.fullscreen = false;
	
	new LwjglApplication(GameCore.getInstance(), cfg);
    }
    
}
