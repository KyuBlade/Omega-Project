package com.team.omega.core.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.team.omega.core.GameCore;
import com.team.omega.core.LocalizationHandler;
import com.team.omega.ui.Panel;


public class GameServerSelectionScreen extends BaseScreen
{

    private Image background;
    
    private Label serverName;
    private Label serverState;
    private Label serverPing;
    
    private ScrollPane scrollPane;
    private List serverList;
    
    private TextButton backButton;
    private TextButton selectButton;
    
    public GameServerSelectionScreen()
    {
	background = new Image(GameCore.getInstance().getAssetManager().get("data/skins/default/backgrounds/login_bg.jpg", Texture.class));
	
	serverName = new Label(LocalizationHandler.getInstance().getDialog("gameserver.selection.name"), skin);
	serverState = new Label(LocalizationHandler.getInstance().getDialog("gameserver.selection.state"), skin);
	serverPing = new Label(LocalizationHandler.getInstance().getDialog("gameserver.selection.ping"), skin);

	serverList = new List(new String[] {"test", "test2", "test3", "test4" , "test2", "test3", "test4" , "test2", "test3", "test4" , "test2", "test3", "test4" , "test2", "test3", "test4"}, skin);
	
	scrollPane = new ScrollPane(serverList);
	
	backButton = new TextButton(LocalizationHandler.getInstance().getDialog("common.choice.back"), skin);
	selectButton = new TextButton(LocalizationHandler.getInstance().getDialog("gameserver.selection.select"), skin);
	
	Panel _panel = new Panel(skin, "black_alpha");
	_panel.debug();
	_panel.defaults().width(100f);
	_panel.add(serverName);
	_panel.add(serverState);
	_panel.add(serverPing);
	
	_panel.row();
	_panel.add(scrollPane).width(400).height(200).colspan(3);
	
	_panel.row();
	_panel.add(backButton);
	_panel.add();
	_panel.add(selectButton);
	
	layout.setBackground(background.getDrawable());
	layout.add(_panel);
    }
    
    public List getServerList()
    {
	return serverList;
    }
    
}
