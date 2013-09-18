package com.team.omega.core.screen;

import net.team.omega.core.network.GameServerState;
import net.team.omega.core.network.LoginServerFactory;
import net.team.omega.core.network.serialization.connection.gameserver.ConnectionGameServerSelect;
import net.team.omega.core.network.serialization.datas.GameServer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.team.omega.core.Constants;
import com.team.omega.core.GameCore;
import com.team.omega.core.LocalizationHandler;
import com.team.omega.ui.base.list.ListRow;
import com.team.omega.ui.base.list.TabbedList;
import com.team.omega.ui.base.panel.Panel;


public class GameServerSelectionScreen extends BaseScreen
{

    private Image background;
    
    private ScrollPane scrollPane;
    private TabbedList serverList;
    
    private TextButton backButton;
    private TextButton selectButton;
    
    private GameServer selectedGameServer;
    
    public GameServerSelectionScreen(final ScreenManager screenManager)
    {
	super(screenManager, Constants.GAMESERVER_SELECTION_SCREEN_DEPTH);
	
	background = new Image(GameCore.getInstance().getAssetManager().get("data/skins/default/backgrounds/login_bg.jpg", Texture.class));
	
	String _serverName = LocalizationHandler.getInstance().getDialog("gameserver.selection.name");
	String _serverState = LocalizationHandler.getInstance().getDialog("gameserver.selection.state");
	String _serverPing = LocalizationHandler.getInstance().getDialog("gameserver.selection.ping");

	serverList = new TabbedList(new ListRow[]{}, skin);
	serverList.setColumnGap(100);
	serverList.setHeader(_serverName + "\t" + _serverState + "\t" + _serverPing);
	
	scrollPane = new ScrollPane(serverList, skin);
	scrollPane.setFadeScrollBars(false);
	scrollPane.setFlickScroll(false);
	
	backButton = new TextButton(LocalizationHandler.getInstance().getDialog("common.choice.back"), skin);
	backButton.addListener(new ChangeListener(){

	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		screenManager.addScreen(IdentificationScreen.class);
		LoginServerFactory.getInstance().disconnect();
		screenManager.removeScreen(GameServerSelectionScreen.class);
	    }
	    
	});
	selectButton = new TextButton(LocalizationHandler.getInstance().getDialog("gameserver.selection.select"), skin);
	selectButton.addListener(new ChangeListener(){

	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		GameServer _gameserver = (GameServer) serverList.getSelection().getStore()[0];
		if(_gameserver != null)
		{
		    selectedGameServer = _gameserver;
		    if(_gameserver.getState() == GameServerState.ONLINE)
		    {
			screenManager.addScreen(WaitingScreen.class);
			LoginServerFactory.getInstance().send(new ConnectionGameServerSelect(_gameserver));
		    }
		}
	    }
	    
	});
	
	Panel _panel = new Panel(skin, "black_alpha");
	_panel.pad(20f);
	
	_panel.row();
	_panel.add(scrollPane).width(400).height(200).colspan(3);
	
	_panel.row().colspan(2).padTop(10f);
	_panel.add(backButton).left();
	_panel.add(selectButton).right();
	
	layout.setBackground(background.getDrawable());
	layout.add(_panel);
    }
    
    @Override
    public void show()
    {
	super.show();
	
	screenManager.removeScreen(WaitingScreen.class);
    }
    
    @Override
    public void hide()
    {
	super.hide();
    }
    
    public TabbedList getServerList()
    {
	return serverList;
    }
    
    public GameServer getSelectedGameServer()
    {
	return selectedGameServer;
    }
    
}
