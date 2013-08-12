package net.team.omega.core.network.serialization.character;



import net.team.omega.core.database.HibernateFactory;
import net.team.omega.core.database.table.Player;
import net.team.omega.core.network.gameserver.ClientConnection;
import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.gameserver.connection.ConnectionGameServerAccept;
import net.team.omega.logging.LogHandler;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class CharacterDelete extends MessageData
{

    private int id;

    public CharacterDelete()
    {

    }

    @Override
    public void process(ClientConnection connection)
    {
	Session _session = HibernateFactory.getSession();

	try
	{
	    // Check if character is owned by user
	    Player _player = (Player) _session.get(Player.class, new Integer(id));
	    if (_player == null)
	    {
		LogHandler.warning("Account " + connection.getClientData().getAccount().getId() + " try to delete a character uncreated (" + id + ")");
		connection.close();
	    }
	    else if (_player.getAccountId() != connection.getClientData().getAccount().getId())
	    {
		LogHandler.warning("Account " + connection.getClientData().getAccount().getId() + " try to delete an unowned character " + _player.getName() + "(" + id + ")");
		connection.close();
	    }
	    else
	    {
		Transaction _t = _session.beginTransaction();
		_session.delete(_player);
		_t.commit();
		LogHandler.info("Account " + connection.getClientData().getAccount().getId() + " deleted character " + _player.getName() + " (" + _player.getId() + ")");
		
		connection.sendTCP(new ConnectionGameServerAccept());
	    }
	} finally
	{
	    _session.close();
	}
    }

}
