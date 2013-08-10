package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.team.omega.core.GameCore;


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
    
    static {
	inputProcessor = new InputMultiplexer();
	Gdx.input.setInputProcessor(inputProcessor);
	
	TextureAtlas _atlas = GameCore.getInstance().getAssetManager().get("data/skins/default/default-skin.atlas");
	skin = new Skin(Gdx.files.internal("data/skins/default/default.json"), _atlas);
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
