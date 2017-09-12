package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.team.omega.core.EditorCore;


public abstract class MasterScreen implements Screen
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
    
    /** 
     * Catch events and draw actors
     */
    protected static Stage stage;
    
    /**
     * Overlap all screen layouts
     */
    protected static Stack stageStack;
    
    static {
	inputProcessor = new InputMultiplexer();
	Gdx.input.setInputProcessor(inputProcessor);
	
	TextureAtlas _atlas = EditorCore.getInstance().getInternalAssetManager().get("skin/skin.atlas");
	skin = new Skin(Gdx.files.internal("skin/default.json"), _atlas);
	
	mainBatch = new SpriteBatch();
	stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true, mainBatch);
	stageStack = new Stack();
	stage.addActor(stageStack);
	stageStack.setFillParent(true);
    }
    
    public final static void masterRender(float delta)
    {
	stage.act(delta);
	stage.draw();
	
	Table.drawDebug(stage);
    }

    @Override
    public abstract void render(float delta);

    @Override
    public abstract void resize(int width, int height);
    
    @Override
    public abstract void show();

    @Override
    public abstract void hide();

    @Override
    public abstract void pause();

    @Override
    public abstract void resume();
    
    @Override
    public abstract void dispose();

    public final static void masterDispose()
    {
	skin.dispose();
	stage.dispose();
	mainBatch.dispose();
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
