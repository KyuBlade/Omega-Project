package com.team.omega.core.screen;

import net.team.omega.core.network.LoginServerFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
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
    
    private Image background;
    
    private Label userLabel;
    private Label passwordLabel;
    
    private TextField userInput;
    private TextField passwordInput;
    
    private ImageTextButton submit;
    
    public IdentificationScreen()
    {
	super();
	
	Gdx.input.setInputProcessor(stage);
	
	Texture _bgTexture = new Texture(Gdx.files.internal("data/skins/default/backgrounds/login_bg.jpg"));
	background = new Image(_bgTexture);
	
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
	
	Stack _stack = new Stack();
	Table _table = new Table(skin);
	
	_table.add(userLabel);
	_table.add(userInput);
	_table.row();
	_table.add(passwordLabel);
	_table.add(passwordInput);
	_table.row();
	_table.add(submit).colspan(2);
	
	_stack.add(background);
	_stack.add(_table);
	layout.add(_stack);
    }

    @Override
    public void render(float delta)
    {
	super.render(delta);
    }

    @Override
    public void dispose()
    {
	super.dispose();
    }
    
    public void showPopup(String message)
    {
	Dialog dialogServerUnreachable = new Dialog(message, skin);
	dialogServerUnreachable.button(new TextButton(LocalizationHandler.getInstance().getDialog("common.choice.ok"), skin));
	dialogServerUnreachable.setTitleAlignment(Align.center);
	dialogServerUnreachable.setMovable(false);
	dialogServerUnreachable.center();
	dialogServerUnreachable.show(stage);
    }

}
