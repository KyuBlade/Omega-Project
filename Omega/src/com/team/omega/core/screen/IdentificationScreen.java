package com.team.omega.core.screen;

import net.team.omega.network.core.LoginServerFactory;

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
import com.team.omega.utils.ThreadFactory;


public class IdentificationScreen extends BaseScreen
{
    
    private Table table;
    
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
	table.add(userLabel);
	table.add(userInput);
	table.row();
	table.add(passwordLabel);
	table.add(passwordInput);
	table.row();
	table.add(submit).colspan(2);
	table.row();
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
    
    public void showServerUnreachable()
    {
	Dialog dialogServerUnreachable = new Dialog("Unable to connect to server", skin);
	dialogServerUnreachable.button(new TextButton("Ok", skin));
	dialogServerUnreachable.setTitleAlignment(Align.center);
	dialogServerUnreachable.setMovable(false);
	dialogServerUnreachable.center();
	dialogServerUnreachable.show(stage);
    }

}
