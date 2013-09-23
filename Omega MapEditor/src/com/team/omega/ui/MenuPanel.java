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
	add(titlePanel);
    }
    
    @Override
    public Cell<?> add(Actor actor)
    {
	return super.add(actor).row();
    }

    @Override
    public Cell<?> add(String label)
    {
	return super.add(label).row();
    }
    
}
