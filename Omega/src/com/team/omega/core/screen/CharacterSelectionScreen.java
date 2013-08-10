package com.team.omega.core.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.team.omega.core.Constants;
import com.team.omega.core.GameCore;


public class CharacterSelectionScreen extends BaseScreen
{

    private Image background;
    
    public CharacterSelectionScreen(ScreenManager screenManager)
    {
	super(screenManager, Constants.CHARACTER_SELECTION_SCREEN_DEPTH);
	
	background = new Image(GameCore.getInstance().getAssetManager().get("data/skins/default/backgrounds/login_bg.jpg", Texture.class));
	
	layout.add(background);
    }
    
    @Override
    public void show()
    {
	super.show();
	
	GameCore.getInstance().getScreenManager().removeScreen(GameServerSelectionScreen.class);
	GameCore.getInstance().getScreenManager().removeScreen(CharacterCreationScreen.class);
	GameCore.getInstance().getScreenManager().removeScreen(WaitingScreen.class);
    }
    
}
