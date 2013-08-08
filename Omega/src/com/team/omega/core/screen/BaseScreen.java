package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


public class BaseScreen extends MasterScreen
{
    
    /** 
     * Internal screen layout
     */
    protected Table layout;
    
    public BaseScreen()
    {
	layout = new Table();
	layout.setFillParent(true);
	
	globalLayout.add(layout);
	
	Gdx.app.debug("Screen", this.getClass().getSimpleName() + " created");
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

}
