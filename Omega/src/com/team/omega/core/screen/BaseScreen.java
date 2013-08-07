package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.team.omega.core.GameCore;


public class BaseScreen implements Screen
{
    
    protected Table layout;
    protected Stage stage;
    protected Skin skin;
    
    public BaseScreen()
    {
	stage = new Stage();
	
	layout = new Table();
	layout.setFillParent(true);
	stage.addActor(layout);
	
	// Debug purpose
	layout.debug();
	layout.debugTable();
	layout.debugCell();
	
	// TODO: May do overload and be used once for all screen
	// So make a SkinManager
	TextureAtlas _atlas = GameCore.getInstance().getAssetManager().get("data/skins/default/default-skin.atlas");
	skin = new Skin(Gdx.files.internal("data/skins/default/default.json"), _atlas);
	
	Gdx.app.debug("Screen", this.getClass().getSimpleName() + " created");
    }
    
    @Override
    public void render(float delta)
    {
	stage.act(Gdx.graphics.getDeltaTime());
	stage.draw();
	
	Table.drawDebug(stage);
    }

    @Override
    public void resize(int width, int height)
    {
	Gdx.app.debug("Resizing", "Resize to " + width + "x" + height);
	stage.setViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
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
	stage.dispose();
	skin.dispose();
    }

}
