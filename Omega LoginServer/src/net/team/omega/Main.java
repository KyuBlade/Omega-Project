package net.team.omega;

import net.team.omega.core.database.HibernateFactory;
import net.team.omega.core.network.LoadingFactory;
import net.team.omega.core.network.gameserver.InternalLoginServerFactory;
import net.team.omega.core.network.loginserver.LoginServerFactory;
import net.team.omega.utils.LogHandler;



public class Main
{

    public static void main(String[] args)
    {
	HibernateFactory.setupSessionFactory();
	
	LoadingFactory.getInstance().loadGameServers();
	
	LogHandler.info("Starting InteralLoginServer ...");
	InternalLoginServerFactory.getInstance();
	
	LogHandler.info("Starting LoginServer ...");
	LoginServerFactory.getInstance();
    }

}
