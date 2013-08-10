package net.team.omega.core;

import net.team.omega.core.database.HibernateFactory;
import net.team.omega.core.entity.WorldManager;
import net.team.omega.core.network.gameserver.GameServerFactory;
import net.team.omega.core.network.loginserver.InternalGameServerFactory;
import net.team.omega.logging.LogHandler;

public class ServerCore
{
    
    private WorldManager world;

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
	
	// Create world
	LogHandler.info("Creating world");
	world = new WorldManager("Channel 1");
	
	// Add systems to world
	LogHandler.info("Setup world");
	
	
	// Initialize world
	LogHandler.info("Initializing world");
	world.initialize();
	
	// Start world
	world.start();

	// Start server
	LogHandler.info("Start GameServer");
	GameServerFactory.getInstance().start();

	LogHandler.info("Awaiting connection ...");
    }

}
