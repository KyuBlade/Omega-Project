package com.team.omega.core.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.team.omega.core.Constants;
import com.team.omega.core.GameCore;


public class CharacterCreationScreen extends BaseScreen
{

    private Image background;
    
    private List characterList;
    
    public CharacterCreationScreen(ScreenManager screenManager)
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
	GameCore.getInstance().getScreenManager().removeScreen(CharacterSelectionScreen.class);
	GameCore.getInstance().getScreenManager().removeScreen(WaitingScreen.class);
    }
    
}
