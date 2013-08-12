package net.team.omega.core.network.serialization.character;

import java.util.ArrayList;
import java.util.List;

import net.team.omega.core.database.HibernateFactory;
import net.team.omega.core.database.table.Player;
import net.team.omega.core.network.gameserver.ClientConnection;
import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.logging.LogHandler;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CharacterList extends MessageData
{

    private List<SamplePlayer> characterList = new ArrayList<>();

    public CharacterList()
    {

    }

    @Override
    public void process(ClientConnection connection)
    {
	int _accountId = 0;
	try {
	_accountId = connection.getClientData().getAccount().getId();
	} catch (NullPointerException e) {
	    LogHandler.warning("Unable to get characterList");
	    
	    return;
	}
	
	Session _session = HibernateFactory.getSession();

	Criteria _criteria = _session.createCriteria(Player.class)
		.add(Restrictions.eq("accountId", _accountId));

	@SuppressWarnings("unchecked")
	List<Player> _result = _criteria.list();
	
	_session.close();
	
	for(Player _char : _result)
	    characterList.add(new SamplePlayer(_char.getId(), _char.getName(), _char.getBreed(), _char.getSex(), _char.getLevel()));
	
	connection.sendTCP(this);
    }
    
    @SuppressWarnings("unused")
    public static class SamplePlayer
    {
	
	private int id;
	private String name;
	private int level;
	private int breed;
	private int sex;
	
	public SamplePlayer(int id, String name, int breed, int sex, int level)
	{
	    this.id = id;
	    this.name = name;
	    this.breed = breed;
	    this.sex = sex;
	    this.level = level;
	}
	
    }

}
