package com.team.omega.ui.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.team.omega.ui.MenuPanel;

public class MapPropertiesMenu extends MenuPanel
{

    private Label mapName;
    private Label mapWidth;
    private Label mapHeight;

    public MapPropertiesMenu(Skin skin)
    {
	super("Map properties", skin);

	mapName = new Label("", skin);
	mapWidth = new Label("", skin);
	mapHeight = new Label("", skin);
	
	add("Name : ").maxWidth(100f);
	add(mapName).row();
	add("Width : ").maxWidth(100f);
	add(mapWidth).row();
	add("Height : ").maxWidth(100f);
	add(mapHeight);
    }

    public void setMapName(String name)
    {
	mapName.setText(name);;
    }

    public void setMapWidth(int width)
    {
	mapWidth.setText("" + width);
    }

    public void setMapHeight(int height)
    {
	mapHeight.setText("" + height);
    }

}
