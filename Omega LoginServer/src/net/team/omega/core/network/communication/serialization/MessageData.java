package net.team.omega.core.network.communication.serialization;

import net.team.omega.core.network.communication.gameserver.GameServerConnection;
import net.team.omega.core.network.communication.loginserver.ClientConnection;
import net.team.omega.utils.LogHandler;


public class MessageData extends NetworkData
{

    public MessageData()
    {
	super();
    }
    
    public void process(ClientConnection connection)
    {
	LogHandler.warning("Process for message " + this.getClass().getSimpleName() + " is undefined !");
    }
    
    public void process(GameServerConnection connection)
    {
	LogHandler.warning("Process for message " + this.getClass().getSimpleName() + " is undefined !");
    }
    
}
