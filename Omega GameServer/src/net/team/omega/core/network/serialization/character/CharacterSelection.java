package net.team.omega.core.network.serialization.character;

import net.team.omega.core.database.HibernateFactory;
import net.team.omega.core.database.table.Player;
import net.team.omega.core.network.gameserver.ClientConnection;
import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.game.StartGame;
import net.team.omega.logging.LogHandler;

import org.hibernate.Session;


public class CharacterSelection extends MessageData
{

    private int id;
    
    @Override
    public void process(ClientConnection connection)
    {
	int _accountId = connection.getClientData().getAccount().getId();
	
	
	Session _session = HibernateFactory.getSession();
	Player _player = (Player) _session.get(Player.class, new Integer(id));
	
	if(_player == null) // Check if character is owned by user
	{
	    LogHandler.warning("Account " + _accountId + " try to connect an uncreated character");
	    connection.close();
	}
	else if(_player.getAccountId() != _accountId)
	{
	    LogHandler.warning("Account " + _accountId + " try to connect with an unowned character");
	    connection.close();
	}
	else
	{
	    connection.sendTCP(new StartGame());
	}
    }
    
}
