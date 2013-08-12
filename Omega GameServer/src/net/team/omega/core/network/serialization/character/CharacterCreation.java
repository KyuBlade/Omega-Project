package net.team.omega.core.network.serialization.character;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.team.omega.Constants;
import net.team.omega.core.database.HibernateFactory;
import net.team.omega.core.database.table.Player;
import net.team.omega.core.network.gameserver.ClientConnection;
import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.game.StartGame;
import net.team.omega.logging.LogHandler;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class CharacterCreation extends MessageData
{

    private String name;
    private int breed;
    private int sex;

    @Override
    public void process(ClientConnection connection)
    {
	LogHandler.info("Name : " + name + " / Breed : " + breed + " / Sex : " + sex);
 	final Pattern _pattern = Pattern.compile("[^a-zA-Z-_0-9]");

	if (!name.isEmpty() && name.length() >= Constants.CHARACTER_NAME_MIN_LENGTH && name.length() <= Constants.CHARACTER_NAME_MAX_LENGTH)
	{
	    Matcher matcher = _pattern.matcher(name);
	    if (!matcher.find())
	    {
		// Can create a new character?
		Session _session = HibernateFactory.getSession();
		Criteria _criteria = _session.createCriteria(Player.class).setProjection(Projections.rowCount()).add(Restrictions.eq("accountId", connection.getClientData().getAccount().getId()));
		long _count = (long) _criteria.uniqueResult();
		
		if (_count >= Constants.CHARACTER_MAX_CAPACITY) // Max capacity reached
		{
		    connection.sendTCP(new CharacterCreationMaxCapacityReached());
		    _session.close();
		    
		    return;
		}
		
		// Find if name is already used
		_session.clear();
		_criteria = _session.createCriteria(Player.class).setProjection(Projections.rowCount()).add(Restrictions.eq("name", name));
		_count = (long) _criteria.uniqueResult();
		
		_session.clear();

		if (_count == 0) // Name unused
		{
		    // Create in database
		    LogHandler.info("Create character");
		    Transaction _t = _session.beginTransaction();
		    Player _player = new Player();
		    _player.setAccountId(connection.getClientData().getAccount().getId());
		    _player.setBreed(breed);
		    _player.setName(name);
		    _player.setSex(sex);
		    _player.setX(Constants.CHARACTER_START_X);
		    _player.setY(Constants.CHARACTER_START_Y);
		    _player.setLevel(1);
		    
		    _session.save(_player);
		    _t.commit();
		    _session.close();
		    
		    // Bind the player to the connection
		    //connection.setPlayer(_e);

		    connection.sendTCP(new StartGame());
		}
		else // Name already in use
		    connection.sendTCP(new CharacterCreationNameTaken());
	    }
	    else
		connection.close();
	}
	else
	    connection.close();
    }

}
