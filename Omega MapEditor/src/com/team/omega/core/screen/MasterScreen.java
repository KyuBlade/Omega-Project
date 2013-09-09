package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.team.omega.core.EditorCore;


public class MasterScreen implements Screen
{
    
    /**
     * Style
     */
    protected static Skin skin;
    
    /**
     * Propagate inputs
     */
    protected static InputMultiplexer inputProcessor;
    
    /** 
     * {@link SpriteBatch} used for all screens (improve performances)
     */
    protected static SpriteBatch mainBatch;
    
    static {
	inputProcessor = new InputMultiplexer();
	Gdx.input.setInputProcessor(inputProcessor);
	
	TextureAtlas _atlas = EditorCore.getInstance().getAssetManager().get("skin/skin.atlas");
	skin = new Skin(Gdx.files.internal("skin/default.json"), _atlas);
	
	mainBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta)
    {
    }

    @Override
    public void resize(int width, int height)
    {
    }

    @Override
    public void show()
    {
    }

    @Override
    public void hide()
    {
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void dispose()
    {
	skin.dispose();
    }
    
    public static Skin getSkin()
    {
	return skin;
    }
    
    public static InputMultiplexer getInputProcessor()
    {
	return inputProcessor;
    }
    
}
