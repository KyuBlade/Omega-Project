package net.team.omega.core;

import net.team.omega.core.database.HibernateFactory;
import net.team.omega.core.network.gameserver.GameServerFactory;
import net.team.omega.core.network.loginserver.InternalGameServerFactory;
import net.team.omega.logging.LogHandler;

public class ServerCore
{

    public ServerCore()
    {

    }

    public void start()
    {
	LogHandler.info("[GameServer] Survival");

	// Initialize database connection
	LogHandler.info("Create database connection");
	HibernateFactory.setupSessionFactory();

	// Make a connection with LoginServer
	LogHandler.info("Create connection with LoginServer");
	InternalGameServerFactory.getInstance().start();

	// Start server
	LogHandler.info("Start GameServer");
	GameServerFactory.getInstance().start();

	LogHandler.info("Awaiting connection ...");
    }

}
