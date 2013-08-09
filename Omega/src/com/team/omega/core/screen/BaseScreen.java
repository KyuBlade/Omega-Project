package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.team.omega.core.GameCore;


public class BaseScreen extends MasterScreen implements Comparable<BaseScreen>
{
    
    /** 
     * Internal screen layout
     */
    protected Table layout;
    
    /** 
     * Draw the internal screen layout
     */
    protected Stage stage = new Stage();
    
    /**
     * If true, rendered by the {@link ScreenManager}
     */
    protected boolean isActive;
    
    /**
     * Depth of the screen
     */
    protected int depth = 1;
    
    /**
     * 
     * @param screenManager the {@link ScreenManager} who's adding the {@link BaseScreen}
     * @param depth the depth you want screen to be rendered
     */
    protected ScreenManager screenManager;
    
    public BaseScreen(ScreenManager screenManager, int depth)
    {
	layout = new Table();
	layout.setFillParent(true);
	
	stage.addActor(layout);
	
	this.screenManager = screenManager;
	
	setDepth(depth);
	
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

	float _staticWidth = GameCore.getInstance().getConfigManager().getInitialWidth();
	float _staticHeight = GameCore.getInstance().getConfigManager().getInitialHeight();
	Vector2 size = Scaling.fit.apply(_staticWidth, _staticHeight, width, height);
	int viewportX = (int) (width - size.x) / 2;
	int viewportY = (int) (height - size.y) / 2;
	int viewportWidth = (int) size.x;
	int viewportHeight = (int) size.y;
	Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight);
	stage.setViewport(_staticWidth, _staticHeight, true, viewportX, viewportY, viewportWidth, viewportHeight);
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
    }
    
    /**
     * 
     * @return if screen is rendered
     */
    public boolean isActive()
    {
        return isActive;
    }
    
    /**
     * 
     * @param isActive rendered or not
     */
    public void setActive(boolean isActive)
    {
        this.isActive = isActive;
    }
    
    /**
     * 
     * @return the depth of the screen
     */
    public int getDepth()
    {
        return depth;
    }
    
    /**
     * 
     * @param depth the depth you want the screen to be rendered by the {@link ScreenManager}
     */
    public void setDepth(int depth)
    {
	this.depth = depth;
	
	screenManager.updateScreens();
    }

    /*@Override
    public int compare(BaseScreen c1, BaseScreen c2)
    {
	int value = 0;
	if (c1.depth > c2.depth)
	    value = 1;
	else if (c1.depth < c2.depth)
	    value = -1;
	else if (c1.depth == c2.depth)
	    value = 0;
	    
	return value;
    }*/

    @Override
    public int compareTo(BaseScreen c)
    {
	int value = 0;
	if (depth > c.depth)
	    value = 1;
	else if (depth < c.depth)
	    value = -1;
	else if (depth == c.depth)
	    value = 0;
	    
	return value;
    }
    
}
