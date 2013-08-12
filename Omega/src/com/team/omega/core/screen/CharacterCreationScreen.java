package com.team.omega.core.screen;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.team.omega.core.network.GameServerFactory;
import net.team.omega.core.network.serialization.character.CharacterCreation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.team.omega.core.Breed;
import com.team.omega.core.Constants;
import com.team.omega.core.GameCore;
import com.team.omega.core.LocalizationHandler;
import com.team.omega.core.Sex;
import com.team.omega.ui.panel.Panel;


public class CharacterCreationScreen extends BaseScreen
{

    private Class<? extends BaseScreen> requestFrom;
    
    private Image background;
    
    private Panel rightPanel;
    
    private Panel racePanel;
    private Panel raceTitlePanel;
    private ButtonGroup raceGroup;
    private Map<Button, Breed> raceBind = new HashMap<>();
    
    private ImageButton warriorRaceButton;
    private ImageButton assassinRaceButton;
    private ImageButton templarRaceButton;
    private ImageButton mageRaceButton;
    private ImageButton archerRaceButton;
    private ImageButton musicianRaceButton;
    
    private Panel sexTitlePanel;
    private Panel sexPanel;
    private ButtonGroup sexGroup;
    private Map<Button, Sex> sexBind = new HashMap<>();
    
    private ImageButton maleButton;
    private ImageButton femaleButton;
    
    private Panel namePanel;
    private Panel nameTitlePanel;
    
    private TextField nameInput;
    
    private Panel buttonsPanel;
    
    private TextButton backButton;
    private TextButton createButton;
    
    public CharacterCreationScreen(final ScreenManager screenManager)
    {
	super(screenManager, Constants.CHARACTER_SELECTION_SCREEN_DEPTH);
	
	background = new Image(GameCore.getInstance().getAssetManager().get("data/skins/default/backgrounds/login_bg.jpg", Texture.class));
	
	layout.setBackground(background.getDrawable());
	
	rightPanel = new Panel(skin, "black_alpha");
	rightPanel.pad(10f).padBottom(300f);
	rightPanel.defaults().spaceBottom(10f);
	
	// Race selection
	racePanel = new Panel(skin, "light_brown");
	raceTitlePanel = new Panel(skin, "title");
	raceGroup = new ButtonGroup();
	
	warriorRaceButton = new ImageButton(skin, "warrior_race");
	assassinRaceButton = new ImageButton(skin, "assassin_race");
	templarRaceButton = new ImageButton(skin, "templar_race");
	mageRaceButton = new ImageButton(skin, "mage_race");
	archerRaceButton = new ImageButton(skin, "archer_race");
	musicianRaceButton = new ImageButton(skin, "musician_race");
	
	raceBind.put(warriorRaceButton, Breed.WARRIOR);
	raceBind.put(assassinRaceButton, Breed.ASSASSIN);
	raceBind.put(templarRaceButton, Breed.TEMPLAR);
	raceBind.put(mageRaceButton, Breed.MAGE);
	raceBind.put(archerRaceButton, Breed.ARCHER);
	raceBind.put(musicianRaceButton, Breed.MUSICIAN);
	
	raceGroup.add(warriorRaceButton, assassinRaceButton, templarRaceButton, mageRaceButton, archerRaceButton, musicianRaceButton);
	
	((Label) raceTitlePanel.add(LocalizationHandler.getInstance().getDialog("character.breed") + " : ").minWidth(200f).getWidget()).setAlignment(Align.center);
	racePanel.add(raceTitlePanel).colspan(6).row().spaceBottom(20f).padTop(5f);
	
	racePanel.add(warriorRaceButton);
	racePanel.add(assassinRaceButton);
	racePanel.add(templarRaceButton);
	racePanel.add(mageRaceButton);
	racePanel.add(archerRaceButton);
	racePanel.add(musicianRaceButton);
	
	rightPanel.add(racePanel).fill().row();
	
	// Sex selection
	sexPanel = new Panel(skin, "light_brown");
	sexTitlePanel = new Panel(skin, "title");
	sexGroup = new ButtonGroup();
	
	maleButton = new ImageButton(skin, "male");
	femaleButton = new ImageButton(skin, "female");
	
	sexBind.put(maleButton,  Sex.MALE);
	sexBind.put(femaleButton,  Sex.FEMALE);
	sexGroup.add(maleButton, femaleButton);
	
	((Label) sexTitlePanel.add(LocalizationHandler.getInstance().getDialog("character.sex") + " : ").minWidth(200f).getWidget()).setAlignment(Align.center);
	sexPanel.add(sexTitlePanel).colspan(2).row().spaceBottom(20f).padTop(5f);
	sexPanel.add(maleButton);
	sexPanel.add(femaleButton);
	
	rightPanel.add(sexPanel).fill();
	rightPanel.row();
	
	// Name selection
	namePanel = new Panel(skin, "light_brown");
	nameTitlePanel = new Panel(skin, "title");
	nameInput = new TextField("", skin);
	
	((Label) nameTitlePanel.add(LocalizationHandler.getInstance().getDialog("character.name") + " : ").minWidth(200f).getWidget()).setAlignment(Align.center);
	namePanel.add(nameTitlePanel).row().spaceBottom(20f).padTop(5f);
	namePanel.add(nameInput);
	
	rightPanel.add(namePanel).fill();
	
	layout.add(rightPanel).row();
	
	// Buttons
	buttonsPanel = new Panel(skin, "black_alpha");
	backButton = new TextButton(LocalizationHandler.getInstance().getDialog("common.choice.back"), skin);
	backButton.addListener(new ChangeListener(){

	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		if(requestFrom == null || requestFrom.equals(GameServerSelectionScreen.class))
		{
		    GameServerFactory.getInstance().disconnect();
		    screenManager.addScreen(GameServerSelectionScreen.class);
		    screenManager.removeScreen(CharacterCreationScreen.class);
		}
		else
		{
		    screenManager.addScreen(CharacterSelectionScreen.class);
		    screenManager.removeScreen(CharacterCreationScreen.class);
		}
	    }
	    
	});
	
	createButton = new TextButton(LocalizationHandler.getInstance().getDialog("character.creation.create"), skin);
	createButton.addListener(new ChangeListener()
	{

	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		String _name = nameInput.getText();
		Breed _breed = raceBind.get(raceGroup.getChecked());
		Sex _sex = sexBind.get(sexGroup.getChecked());
		
		if( _name.isEmpty() || _breed == null || _sex == null)
		{
		    showPopup("", LocalizationHandler.getInstance().getDialog("character.creation.error.unfilled"));
		    return;
		}
		
		if (_name.length() >= Constants.CHARACTER_NAME_MIN_LENGTH && _name.length() <= Constants.CHARACTER_NAME_MAX_LENGTH)
		{
		    Pattern _pattern = Pattern.compile("[^a-zA-Z-_0-9]");
		    Matcher matcher = _pattern.matcher(_name);
		    if (!matcher.find())
		    {
			screenManager.addScreen(WaitingScreen.class);
			GameServerFactory.getInstance().sendTCP(new CharacterCreation(_name, _breed.getId(), _sex.getId()));
		    }
		}
		else
		    showPopup("", LocalizationHandler.getInstance().getDialog("character.creation.error.name.lenght", "" + Constants.CHARACTER_NAME_MIN_LENGTH, "" + Constants.CHARACTER_NAME_MAX_LENGTH));
		   
	    }
	    
	});
	
	buttonsPanel.row().pad(10f).spaceRight(90f);
	buttonsPanel.add(backButton).left();
	buttonsPanel.add(createButton).right();
	
	layout.add(buttonsPanel).spaceTop(10f);
	layout.add().expandX();
	layout.padLeft(10f);
    }
    
    @Override
    public void show()
    {
	super.show();
	
	screenManager.removeScreen(GameServerSelectionScreen.class);
	screenManager.removeScreen(CharacterSelectionScreen.class);
	screenManager.removeScreen(WaitingScreen.class);
    }
    
    public void setRequestFrom(Class<? extends BaseScreen> clazz)
    {
	this.requestFrom = clazz;
    }
    
}
