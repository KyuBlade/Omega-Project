package com.team.omega.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.team.omega.ui.base.ProgressBar;


public class ProgressWindow extends Window
{

    private Stage stage;
    
    private ProgressBar progressBar;
    private Label filename;
    
    public ProgressWindow(Stage stage, Skin skin)
    {
	this(stage, skin, "default");
    }
    
    public ProgressWindow(Stage stage, Skin skin, String styleName)
    {
	super("Progress ...", skin, styleName);
	
	this.stage = stage;
	
	progressBar = new ProgressBar(skin);
	filename = new Label("", skin);
	
	setSize(400f, 100f);
	progressBar.setSize(300f, 25f);
	setPosition(stage.getWidth() / 2 - getWidth() / 2, stage.getHeight() / 2 - getHeight() / 2);
	
	add(progressBar).row();
	add(filename);
	
	setModal(true);
	setMovable(false);
	
	stage.addActor(this);
	setVisible(false);
    }
    
    public ProgressBar getProgressBar()
    {
	return progressBar;
    }
    
    public void setFilename(String filename)
    {
	this.filename.setText(filename);
    }
    
    public void show()
    {
	this.setVisible(true);
    }
    
    public void hide()
    {
	this.setVisible(false);
    }
    
}
