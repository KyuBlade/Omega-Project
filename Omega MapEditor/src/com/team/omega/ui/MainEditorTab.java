package com.team.omega.ui;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.team.omega.ui.base.menu.BasicMenuItem;
import com.team.omega.ui.base.menu.ContextMenu;
import com.team.omega.ui.base.menu.SubMenuItem;
import com.team.omega.ui.base.tab.Tab;
import com.team.omega.ui.base.tab.TabContainer;
import com.team.omega.ui.base.tab.TabPane;

public class MainEditorTab extends Tab
{

    private ContextMenu menu;
    private BasicMenuItem closeItem;
    private BasicMenuItem closeAllItem;

    public MainEditorTab(String name, TabContainer container, Stage stage, Skin skin)
    {
	this(name, container, stage, skin, "default");
    }

    public MainEditorTab(String name, TabContainer container, Stage stage, Skin skin, String styleName)
    {
	super(name, container, skin, styleName);

	// Create menu
	final MainEditorTab _self = this;
	menu = new ContextMenu(skin);
	closeItem = new BasicMenuItem("Close", skin);
	closeItem.addListener(new ClickListener()
	{
	    
	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		((EditorContainer) _self.getContainer()).getProject().close();
	    }

	});
	closeAllItem = new BasicMenuItem("Close All", skin);

	menu.add(closeItem);
	menu.add(closeAllItem);
	stage.addActor(menu);

	stage.addListener(new InputListener() {

	    @Override
	    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
	    {
		Actor _target = event.getTarget();
		if (_target instanceof ContextMenu || _target instanceof SubMenuItem)
		    return false;
		else if (_target instanceof Label)
		{
		    Actor _parent = _target.getParent();
		    if (_parent instanceof Tab || _parent instanceof SubMenuItem)
			return false;
		}

		ContextMenu.closeAllMenu();

		return true;
	    }

	});
    }

    @Override
    public void setFrom(final TabPane from)
    {
	super.setFrom(from);

	this.addListener(new InputListener() {
	    
	    @Override
	    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
	    {
		Tab _tab = null;
		if (button == Buttons.RIGHT)
		{
		    Actor _target = event.getTarget();
		    Actor _parent = event.getTarget().getParent();
		    if (_target instanceof Tab)
			_tab = (Tab) _target;
		    else if (_parent instanceof Tab)
			_tab = (Tab) _parent;
		}

		if (button == Buttons.RIGHT && _tab != null)
		{
		    ContextMenu.closeAllMenu();
		    
		    menu.setPosition(event.getStageX(), event.getStageY());
		    menu.setVisible(true);

		    return true;
		}

		return false;
	    }

	});
    }

}
