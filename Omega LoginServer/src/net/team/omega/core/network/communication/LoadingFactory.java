package net.team.omega.core.network.communication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.team.omega.core.database.HibernateFactory;
import net.team.omega.core.database.table.GameServer;
import net.team.omega.utils.LogHandler;

import org.hibernate.Session;

public class LoadingFactory
{

    private List<GameServer> gameservers = new ArrayList<>();
    
    private static LoadingFactory instance; 

    public LoadingFactory()
    {

    }

    public static LoadingFactory getInstance()
    {
	if(instance ==  null)
	    instance = new LoadingFactory();
	
	return instance;
    }
    
    public void loadGameServers()
    {
	Session _session = HibernateFactory.getSession();
	List<GameServer> _result = _session.createCriteria(GameServer.class).list();
	Iterator<GameServer> _it = _result.iterator();
	int count = 0;
	while (_it.hasNext())
	{
	    count++;
	    gameservers.add(_it.next());
	}
	
	_session.close();

	LogHandler.system(count + " gameserver registered");
    }

    public List<GameServer> getGameServers()
    {
	return gameservers;
    }

}
