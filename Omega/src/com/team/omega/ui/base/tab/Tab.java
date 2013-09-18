package com.team.omega.ui.base.tab;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;


public class Tab extends Button
{

    private TabContainer container;
    private TabPane from;
    
    public Tab(String name, TabContainer container, Skin skin)
    {
	this(name, container, skin, "default");
    }
    
    public Tab(String name, TabContainer container, Skin skin, String styleName)
    {
	super(skin, styleName);
	
	this.container = container;
	this.add(name).minWidth(100f);
	
	this.addListener(new InputListener() {
	    
	    @Override
	    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
	    {
		Tab _tab = null;
		if(button == Buttons.LEFT)
		{
		    Actor _target = event.getTarget();
		    Actor _parent = event.getTarget().getParent();
		    if (_target instanceof Tab)
			_tab = (Tab) _target;
		    else if (_parent instanceof Tab)
			_tab = (Tab) _parent;
		}
		
		if (button == Buttons.LEFT && _tab != null)
		{
		    from.hideAllContainer();
		    from.getTabBind().get(_tab).setVisible(true);
		    from.setCurrentTab(_tab);

		    return true;
		}
		
		return false;
	    }
	    
	});
    }
    
    public static class TabStyle extends TextButtonStyle
    {

	public TabStyle()
	{
	    
	}

    }
    
    public TabContainer getContainer()
    {
	return container;
    }
    
    public void setFrom(TabPane tabPane)
    {
	this.from = tabPane;
    }
    
}
