package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.team.omega.core.GameCore;


public class MasterScreen implements Screen
{

    /**
     * Layout that store all layouts screen
     */
    protected final static Stack globalLayout = new Stack();
    
    /** 
     * Draw the internal screen layout
     */
    protected final static Stage stage = new Stage();
    
    static {
	globalLayout.setFillParent(true);
	stage.addActor(globalLayout);
    }
    
    public static void masterRender(float delta)
    {
	stage.act(Gdx.graphics.getDeltaTime());
	stage.draw();
	
	Table.drawDebug(stage);
    }

    public static void masterResize(int width, int height)
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

    public static void masterDispose()
    {
	stage.dispose();
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
    }

}
