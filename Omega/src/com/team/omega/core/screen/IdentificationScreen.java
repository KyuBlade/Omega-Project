package com.team.omega.core.screen;

import net.team.omega.core.network.LoginServerFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.team.omega.core.LocalizationHandler;
import com.team.omega.ui.Panel;
import com.team.omega.utils.ThreadFactory;


public class IdentificationScreen extends BaseScreen
{
    
    private Table table;
    private Panel internalPanel;
    
    private Label userLabel;
    private Label passwordLabel;
    
    private TextField userInput;
    private TextField passwordInput;
    
    private ImageTextButton submit;
    
    public IdentificationScreen()
    {
	super();
	
	table = new Table(skin);
	table.setFillParent(true);
	stage.addActor(table);
	
	userLabel = new Label(LocalizationHandler.getInstance().getDialog("login.user"), skin);
	userInput = new TextField("", skin);
	passwordLabel = new Label(LocalizationHandler.getInstance().getDialog("login.password"), skin);
	passwordInput = new TextField("", skin);
	passwordInput.setPasswordMode(true);
	passwordInput.setPasswordCharacter('*');
	submit = new ImageTextButton(LocalizationHandler.getInstance().getDialog("login.loging"), skin);
	submit.addListener(new ClickListener() {
	    
	    @Override
	    public void clicked(InputEvent event, float x, float y) 
	    {
		Gdx.app.debug("Login", "Login clicked");
		ThreadFactory.newThread(new Runnable(){

		    @Override
		    public void run()
		    {
			LoginServerFactory.getInstance().connect(userInput.getText(), passwordInput.getText());
		    }
		    
		});
	    }
	    
	});	
	
	table.debug();
	internalPanel = new Panel(skin);
	internalPanel.add(userLabel);
	internalPanel.add(userInput);
	internalPanel.row();
	internalPanel.add(passwordLabel);
	internalPanel.add(passwordInput);
	internalPanel.row();
	internalPanel.add(submit).colspan(2);
	table.add(internalPanel);
    }

    @Override
    public void render(float delta)
    {
	super.render(delta);
	
	Table.drawDebug(stage);
    }

    @Override
    public void dispose()
    {
	super.dispose();
    }
    
    public void showPopup(String message)
    {
	Dialog dialogServerUnreachable = new Dialog(message, skin);
	dialogServerUnreachable.button(new TextButton("Ok", skin));
	dialogServerUnreachable.setTitleAlignment(Align.center);
	dialogServerUnreachable.setMovable(false);
	dialogServerUnreachable.center();
	dialogServerUnreachable.show(stage);
    }

}
