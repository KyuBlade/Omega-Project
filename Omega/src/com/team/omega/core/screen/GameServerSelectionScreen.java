package com.team.omega.core.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.team.omega.core.GameCore;
import com.team.omega.ui.Panel;


public class GameServerSelectionScreen extends BaseScreen
{

    private Image background;
    
    private Label serverName;
    private Label serverState;
    private Label serverPing;
    
    private List serverList;
    
    public GameServerSelectionScreen()
    {
	background = new Image(GameCore.getInstance().getAssetManager().get("data/skins/default/backgrounds/login_bg.jpg", Texture.class));
	
	serverName = new Label("gameserver.selection.name", skin);
	serverState = new Label("gameserver.selection.state", skin);
	serverPing = new Label("gameserver.selection.ping", skin);
	serverList = new List(null, skin);
	
	Panel _panel = new Panel(skin, "black_alpha");
	_panel.add(serverName);
	_panel.add(serverState);
	_panel.add(serverPing);
	
	_panel.add(serverList);
	
	layout.add(_panel);
	
    }
    
}
