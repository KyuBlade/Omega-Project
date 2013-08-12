package com.team.omega.core.screen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.team.omega.core.network.GameServerFactory;
import net.team.omega.core.network.serialization.character.CharacterDelete;
import net.team.omega.core.network.serialization.datas.SamplePlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.team.omega.core.Constants;
import com.team.omega.core.GameCore;
import com.team.omega.core.LocalizationHandler;
import com.team.omega.ui.ChoiceDialog;
import com.team.omega.ui.panel.Panel;
import com.team.omega.ui.panel.PanelGroup;


public class CharacterSelectionScreen extends BaseScreen
{

    private Image background;
    
    private Panel characterPanel;
    private Table appendTable;
    
    private TextButton backButton;
    private TextButton newButton;
    private TextButton deleteButton;
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
        
        newButton = new TextButton(LocalizationHandler.getInstance().getDialog("character.selection.new"), skin);
        newButton.addListener(new ChangeListener(){

	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		screenManager.addScreen(CharacterCreationScreen.class).setRequestFrom(CharacterSelectionScreen.class);;
		screenManager.removeScreen(CharacterSelectionScreen.class);
	    }
            
        });
        
        final Stage _stage = this.stage;
        deleteButton = new TextButton(LocalizationHandler.getInstance().getDialog("character.selection.delete"), skin);
        deleteButton.addListener(new ChangeListener(){

	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		final SamplePlayer _player = playerBind.get(panelGroup.getChecked().getId());
		if(_player == null)
		    return;
		
		final ChoiceDialog _deleteDialog = new ChoiceDialog("", 
			LocalizationHandler.getInstance().getDialog("character.selection.delete.confirm", _player.getName()), 
			LocalizationHandler.getInstance().getDialog("common.choice.refuse"), 
			LocalizationHandler.getInstance().getDialog("common.choice.accept"), skin);
		
		_deleteDialog.show(_stage).getAcceptButton()
			.addListener(new ChangeListener(){

			    @Override
			    public void changed(ChangeEvent event, Actor actor)
			    {
				screenManager.addScreen(WaitingScreen.class);
				_deleteDialog.hide();
				GameServerFactory.getInstance().sendTCP(new CharacterDelete(_player.getId()));
			    }
			    
			});
		
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
	
	if(appendTable == null)
	      appendTable = new Table();
	
	if(appendTable != null && characterPanel != null)
	{
	    appendTable.reset();
	    characterPanel.reset();
	    characterPanel.remove();
	    panelGroup.clear();
	    playerBind.clear();
	}
	
	if(panelGroup == null)
	    panelGroup = new PanelGroup();
	
	for (final SamplePlayer _sample : players)
	{
	    Panel _panel = new Panel(skin, "player_cell"); // Contain player details
	    _panel.pad(50f);
	    _panel.setTouchable(Touchable.enabled);
	    
	    Label _playerName = new Label(LocalizationHandler.getInstance().getDialog("character.name") + ": " + _sample.getName(), skin);
	    _playerName.setAlignment(Align.center);
	    Label _playerLevel = new Label(LocalizationHandler.getInstance().getDialog("character.level") + ": " + _sample.getLevel(), skin);
	    _playerLevel.setAlignment(Align.center);
	    Label _playerRace = new Label(LocalizationHandler.getInstance().getDialog("character.breed") + ": " + _sample.getBreed(), skin);
	    _playerRace.setAlignment(Align.center);
	    
	    _panel.add(_playerName).row();
	    _panel.add(_playerLevel).row();
	    _panel.add(_playerRace);

	    playerBind.put(_panel.getId(), _sample);
	    
	    panelGroup.add(_panel);
	    
	    appendTable.add(_panel).spaceRight(10f);
	}
	
	characterPanel = new Panel(skin, "black_alpha");
	characterPanel.padLeft(40f).padRight(40f).padTop(20f).padBottom(20f).row().colspan(4);
	characterPanel.add(appendTable);
	
	characterPanel.row().spaceTop(10f).minWidth(100f);
	characterPanel.defaults().maxWidth(50f);
	characterPanel.add(backButton);
	characterPanel.add(newButton);
	characterPanel.add(deleteButton);
	characterPanel.add(selectButton);
	
	layout.add(characterPanel);
    }

}
