package net.team.omega.core.network.communication.serialization;

import net.team.omega.core.network.communication.gameserver.ClientConnection;
import net.team.omega.logging.LogHandler;


public class MessageData extends NetworkData
{

    public MessageData()
    {
	super();
    }
    
    /**
     * Executed on receive (for clients)
     * 
     * @param connection Source connection of the client
     */
    public void process(ClientConnection connection)
    {
	LogHandler.warning("Process was not defined for " + this.getClass().getSimpleName());
    }
    
    /**
     * Executed before sended
     * 
     * @param connection target connection
     */
    public void preSend(ClientConnection connection)
    {
	
    }
    
    /**
     * Executed on receive (for LoginServer connection)
     */
    public void process()
    {
	LogHandler.warning("Process for message " + this.getClass().getSimpleName() + " is undefined !");
    }
    
}
