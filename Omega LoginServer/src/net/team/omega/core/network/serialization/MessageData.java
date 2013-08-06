package net.team.omega.core.network.serialization;

import net.team.omega.core.network.gameserver.GameServerConnection;
import net.team.omega.core.network.loginserver.ClientConnection;
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
