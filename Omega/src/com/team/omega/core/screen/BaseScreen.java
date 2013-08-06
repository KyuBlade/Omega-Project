package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.team.omega.core.GameCore;


public class BaseScreen implements Screen
{

    protected GameCore core;
    protected boolean isActive;
    
    protected Stage stage;
    protected Skin skin;
    
    public BaseScreen(GameCore core)
    {
	this.core = core;
	isActive = true;
	
	stage = new Stage();
	Gdx.input.setInputProcessor(stage);
	
	TextureAtlas _atlas = core.getAssetManager().get("data/skins/default/default-skin.atlas");
	skin = new Skin(Gdx.files.internal("data/skins/default/default.json"), _atlas);
	
	Gdx.app.debug("Screen", this.getClass().getSimpleName() + " created");
    }
    
    @Override
    public void render(float delta)
    {
	if(!isActive)
	    return;
	
	Gdx.gl.glClearColor(0f, 0f, 0f, 1);
	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height)
    {
    }

    @Override
    public void show()
    {
	isActive = true;
    }

    @Override
    public void hide()
    {
	isActive = false;
    }

    @Override
    public void pause()
    {
	isActive = false;
    }

    @Override
    public void resume()
    {
	isActive = true;
    }

    @Override
    public void dispose()
    {
    }

}
