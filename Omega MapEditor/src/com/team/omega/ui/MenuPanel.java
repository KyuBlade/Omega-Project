package com.team.omega.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.esotericsoftware.tablelayout.Cell;
import com.team.omega.ui.base.panel.Panel;


public class MenuPanel extends Panel
{

    private Panel titlePanel;
    private Label titleLabel;
    
    private Panel contentPanel;
    
    public MenuPanel(String title, Skin skin)
    {
	this(title, skin, "default");
    }
    
    public MenuPanel(String title, Skin skin, String styleName)
    {
	super(skin, styleName);
	
	titlePanel = new Panel(skin, styleName);
	titleLabel = new Label(title, skin);
	
	titlePanel.add(titleLabel);
	defaults().expandX().fillX().left();
	super.add(titlePanel).row();
	
	contentPanel = new Panel(skin, "blank");
	super.add(contentPanel);
	contentPanel.left();
	contentPanel.defaults().left();
    }
    
    @Override
    public Cell<?> add(Actor actor)
    {
        return contentPanel.add(actor);
    }
    
    @Override
    public Cell<?> add(String text)
    {
        return contentPanel.add(text);
    }
    
}
