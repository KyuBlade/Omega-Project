package net.team.omega.core.network.serialization.gameserver.connection;

import net.team.omega.core.database.HibernateFactory;
import net.team.omega.core.database.table.Player;
import net.team.omega.core.network.gameserver.ClientConnection;
import net.team.omega.core.network.serialization.MessageData;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class ConnectionGameServerAccept extends MessageData
{

    @SuppressWarnings("unused")
    private boolean needCharacterCreation;

    @Override
    public void preSend(ClientConnection connection)
    {
	Session _session = HibernateFactory.getSession();
	Criteria _criteria = _session.createCriteria(Player.class).setProjection(Projections.rowCount()).add(Restrictions.eq("accountId", connection.getClientData().getAccount().getId()));
	
	long _count = (long) _criteria.uniqueResult();
	if(_count > 0)
	    needCharacterCreation = false;
	else
	    needCharacterCreation = true;
	
	_session.close();
    }

}
