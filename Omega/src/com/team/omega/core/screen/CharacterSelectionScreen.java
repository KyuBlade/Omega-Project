package com.team.omega.core.screen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.team.omega.core.network.GameServerFactory;
import net.team.omega.core.network.serialization.datas.SamplePlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.team.omega.core.Constants;
import com.team.omega.core.GameCore;
import com.team.omega.core.LocalizationHandler;
import com.team.omega.ui.Panel;
import com.team.omega.ui.PanelGroup;


public class CharacterSelectionScreen extends BaseScreen
{

    private Image background;
    
    private Panel characterPanel;
    private Table appendTable;
    
    private TextButton backButton;
    private TextButton selectButton;
    
    private PanelGroup panelGroup;
    
    private Map<Integer, SamplePlayer> playerBind = new HashMap<>();
    
    public CharacterSelectionScreen(final ScreenManager screenManager)
    {
	super(screenManager, Constants.CHARACTER_SELECTION_SCREEN_DEPTH);
	
	background = new Image(GameCore.getInstance().getAssetManager().get("data/skins/default/backgrounds/login_bg.jpg", Texture.class));
	layout.setBackground(background.getDrawable());
	
	// Buttons setup
        backButton = new TextButton(LocalizationHandler.getInstance().getDialog("common.choice.back"), skin);
        backButton.addListener(new ChangeListener() {

	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		GameServerFactory.getInstance().disconnect();
		screenManager.addScreen(GameServerSelectionScreen.class);
		screenManager.removeScreen(CharacterSelectionScreen.class);
	    }

	});
        
        selectButton = new TextButton(LocalizationHandler.getInstance().getDialog("character.selection.select"), skin);
        selectButton.addListener(new ChangeListener() {

	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		Gdx.app.debug("Button", "Want to connect in game character " + playerBind.get(panelGroup.getChecked().getId()).getName());
	    }

	});
	
        appendTable = new Table();
	characterPanel = new Panel(skin, "black_alpha");
	characterPanel.padLeft(40f).padRight(40f).padTop(20f).padBottom(20f).row().colspan(2);
	characterPanel.add(appendTable);
	
	characterPanel.row().spaceTop(10f);
	characterPanel.add(backButton);
	characterPanel.add(selectButton);
	
	layout.add(characterPanel);
    }
    
    @Override
    public void show()
    {
	super.show();
	
	screenManager.removeScreen(GameServerSelectionScreen.class);
	screenManager.removeScreen(CharacterCreationScreen.class);
	screenManager.removeScreen(WaitingScreen.class);
    }

    public void addPlayers(List<SamplePlayer> players)
    {
	// Check if character list changed
	if(playerBind.values().size() == players.size())
	    return;
	
	appendTable.reset();
	
	if(panelGroup == null)
	    panelGroup = new PanelGroup();
	
	for (final SamplePlayer _sample : players)
	{
	    Panel _panel = new Panel(skin, "player_cell"); // Contain player details
	    _panel.pad(50f);
	    _panel.setTouchable(Touchable.enabled);

	    _panel.add(new Label(LocalizationHandler.getInstance().getDialog("character.name") + ": " + _sample.getName(), skin)).row();
	    _panel.add(new Label(LocalizationHandler.getInstance().getDialog("character.level") + ": " + _sample.getLevel(), skin)).row();
	    _panel.add(new Label(LocalizationHandler.getInstance().getDialog("character.breed") + ": " + _sample.getBreed(), skin));

	    playerBind.put(_panel.getId(), _sample);
	    panelGroup.add(_panel);
	    appendTable.add(_panel).spaceRight(10f);
	}
    }

}
