package com.team.omega.core.screen;

import java.util.Iterator;
import java.util.List;

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
import com.esotericsoftware.tablelayout.Cell;
import com.team.omega.core.Constants;
import com.team.omega.core.GameCore;
import com.team.omega.core.LocalizationHandler;
import com.team.omega.ui.Panel;


public class CharacterSelectionScreen extends BaseScreen
{

    private Image background;
    
    private Panel characterPanel;
    private Table appendTable;
    
    private TextButton backButton;
    private TextButton selectButton;
    
    public CharacterSelectionScreen(ScreenManager screenManager)
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
		Gdx.app.debug("Button", "Want to get back");
	    }

	});
        
        selectButton = new TextButton(LocalizationHandler.getInstance().getDialog("character.selection.select"), skin);
        selectButton.addListener(new ChangeListener() {

	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		Gdx.app.debug("Button", "Want to connect in game");
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
	
	GameCore.getInstance().getScreenManager().removeScreen(GameServerSelectionScreen.class);
	GameCore.getInstance().getScreenManager().removeScreen(CharacterCreationScreen.class);
	GameCore.getInstance().getScreenManager().removeScreen(WaitingScreen.class);
    }

    public void addPlayers(List<SamplePlayer> players)
    {
	for (final SamplePlayer _sample : players)
	{
	    Panel _panel = new Panel(skin, "player_cell"); // Contain player details
	    _panel.pad(50f);
	    _panel.setTouchable(Touchable.enabled);
	    _panel.addListener(new ChangeListener() {

		@Override
		public void changed(ChangeEvent event, Actor actor)
		{
		    Gdx.app.debug("PanelListener", _sample.getName());
		}

	    });

	    _panel.add(new Label(LocalizationHandler.getInstance().getDialog("character.name") + ": " + _sample.getName(), skin)).row();
	    _panel.add(new Label(LocalizationHandler.getInstance().getDialog("character.level") + ": " + _sample.getLevel(), skin)).row();
	    _panel.add(new Label(LocalizationHandler.getInstance().getDialog("character.breed") + ": " + _sample.getBreed(), skin));

	    appendTable.add(_panel).space(10f);
	}
    }

}
