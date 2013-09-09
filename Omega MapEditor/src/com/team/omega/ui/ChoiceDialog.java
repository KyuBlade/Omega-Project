package com.team.omega.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


public class ChoiceDialog extends Dialog
{

    private TextButton refuseButton;
    private TextButton acceptButton;
    
    public ChoiceDialog(String title, String message, String refuse, String accept, Skin skin)
    {
	super(title, skin);
	
	this.setTitleAlignment(Align.center);
	
	refuseButton = new TextButton(refuse, skin);
	refuseButton.align(Align.center);
	refuseButton.addListener(new ChangeListener(){

	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		hide();
	    }
	    
	});
	acceptButton = new TextButton(accept, skin);
	acceptButton.align(Align.center);
	
	getContentTable().add(message).minWidth(300f).pad(10f);
	getButtonTable().defaults().minWidth(100f).pad(10f);
	getButtonTable().add(refuseButton);
	getButtonTable().add(acceptButton);
	setMovable(false);
	center();
    }
    
    public TextButton getRefuseButton()
    {
	return refuseButton;
    }
    
    public TextButton getAcceptButton()
    {
	return acceptButton;
    }
    
    @Override
    public ChoiceDialog show(Stage stage)
    {
	return (ChoiceDialog) super.show(stage);
    }

}
