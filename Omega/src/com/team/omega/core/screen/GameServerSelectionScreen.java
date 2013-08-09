package com.team.omega.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.team.omega.core.GameCore;
import com.team.omega.core.LocalizationHandler;
import com.team.omega.ui.Panel;
import com.team.omega.ui.list.ListRow;
import com.team.omega.ui.list.TabbedList;


public class GameServerSelectionScreen extends BaseScreen
{

    private Image background;
    
    private ScrollPane scrollPane;
    private TabbedList serverList;
    
    private TextButton backButton;
    private TextButton selectButton;
    
    public GameServerSelectionScreen()
    {
	background = new Image(GameCore.getInstance().getAssetManager().get("data/skins/default/backgrounds/login_bg.jpg", Texture.class));
	
	String _serverName = LocalizationHandler.getInstance().getDialog("gameserver.selection.name");
	String _serverState = LocalizationHandler.getInstance().getDialog("gameserver.selection.state");
	String _serverPing = LocalizationHandler.getInstance().getDialog("gameserver.selection.ping");

	serverList = new TabbedList(new ListRow[]{}, skin);
	serverList.setColumnGap(100);
	serverList.setHeader(_serverName + "\t" + _serverState + "\t" + _serverPing);
	serverList.addListener(new ChangeListener(){

	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		Gdx.app.debug("List change", "Name : " + ((TabbedList) actor).getSelection().getStore()[1]);
	    }
	    
	});
	
	scrollPane = new ScrollPane(serverList, skin);
	scrollPane.setFadeScrollBars(false);
	scrollPane.setFlickScroll(false);
	
	backButton = new TextButton(LocalizationHandler.getInstance().getDialog("common.choice.back"), skin);
	selectButton = new TextButton(LocalizationHandler.getInstance().getDialog("gameserver.selection.select"), skin);
	
	Panel _panel = new Panel(skin, "black_alpha");
	//_panel.debug();
	_panel.pad(20f);
	
	_panel.row();
	_panel.add(scrollPane).width(400).height(200).colspan(3);
	
	_panel.row().colspan(2).padTop(10f);
	_panel.add(backButton).left();
	_panel.add(selectButton).right();
	
	layout.setBackground(background.getDrawable());
	layout.add(_panel);
    }
    
    public TabbedList getServerList()
    {
	return serverList;
    }
    
}
