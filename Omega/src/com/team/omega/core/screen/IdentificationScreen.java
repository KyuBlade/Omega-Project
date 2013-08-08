package com.team.omega.core.screen;

import net.team.omega.core.network.LoginServerFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.team.omega.core.GameCore;
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
	
	background = new Image(GameCore.getInstance().getAssetManager().get("data/skins/default/backgrounds/login_bg.jpg", Texture.class));
	
	userLabel = new Label(LocalizationHandler.getInstance().getDialog("login.user"), skin);
	userInput = new TextField("", skin);
	passwordLabel = new Label(LocalizationHandler.getInstance().getDialog("login.password"), skin);
	passwordInput = new TextField("", skin);
	passwordInput.setPasswordMode(true);
	passwordInput.setPasswordCharacter('*');
	
	// For debug speed
	userInput.setText("test");
	passwordInput.setText("testpass");
	
	submit = new ImageTextButton(LocalizationHandler.getInstance().getDialog("login.loging"), skin);
	submit.addListener(new ChangeListener() {
	    
	    @Override
	    public void changed (ChangeEvent event, Actor actor)
	    {
		Gdx.app.debug("Login", "Login clicked");
		ThreadFactory.newThread(new Runnable(){

		    @Override
		    public void run()
		    {
			String _user = userInput.getText();
			String _pass = passwordInput.getText();
			
			if(_user.isEmpty() || _pass.isEmpty())
			    return;
			
			LoginServerFactory.getInstance().connect(_user, _pass);
		    }
		    
		});
	    }
	    
	});	

	Panel _panel = new Panel(skin, "black_alpha");
	_panel.pad(10f);
	_panel.add(userLabel);
	_panel.add(userInput);
	_panel.row().padTop(5f);
	_panel.add(passwordLabel);
	_panel.add(passwordInput);
	_panel.row().padTop(10f);
	_panel.add(submit).colspan(2);
	
	layout.setBackground(background.getDrawable());
	layout.add(_panel);
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
