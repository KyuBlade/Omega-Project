package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Scaling;
import com.team.omega.core.GameCore;
import com.team.omega.core.LocalizationHandler;


public class BaseScreen extends MasterScreen implements Comparable<BaseScreen>
{
    
    /** 
     * Internal screen layout
     */
    protected Table layout;
    
    /** 
     * Draw the internal screen layout
     */
    protected Stage stage;
    
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
	stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true, mainBatch);
	
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
	Gdx.app.debug("Resizing " + this.getClass().getSimpleName(), "Resize to " + width + "x" + height);

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
	inputProcessor.addProcessor(stage);
	setActive(true);
    }

    @Override
    public void hide()
    {
	inputProcessor.removeProcessor(stage);
	setActive(false);
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
     * Show a modal popup on top of all the screen children
     * 
     * @param title the title of the popup
     * @param message text you want to display in the popup
     */
    public void showPopup(String title, String message)
    {
	screenManager.removeScreen(WaitingScreen.class);
	final Dialog _dialog = new Dialog(title, skin);
	Label _message = new Label(message, skin);
	_message.setAlignment(Align.center);
	
	TextButton _okButton = new TextButton(LocalizationHandler.getInstance().getDialog("common.choice.ok"), skin);
	_okButton.addListener(new ChangeListener(){

	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		_dialog.hide();
	    }
	    
	});
	
	_dialog.getContentTable().add(_message).minWidth(300f).pad(10f);
	_dialog.getButtonTable().add(_okButton).minWidth(100f);
	_dialog.setTitleAlignment(Align.center);
	_dialog.setMovable(false);
	_dialog.center();
	_dialog.show(stage);
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

    /**
     * Used internally to sort screen by depth
     */
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
