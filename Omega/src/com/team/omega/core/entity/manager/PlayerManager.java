package com.team.omega.core.entity.manager;

import com.artemis.Entity;
import com.artemis.Manager;
import com.artemis.utils.Bag;


public class PlayerManager extends Manager
{

    private Bag<Entity> players;
    
    @Override
    protected void initialize()
    {
	players = new Bag<>();
    }
    
    public void add(Entity e)
    {
	players.add(e);
    }
    
    public void remove(Entity e)
    {
	players.remove(e);
    }
    
    public int getPlayerCount()
    {
	return players.size();
    }
    
    public Bag<Entity> getPlayers()
    {
	return players;
    }

}
