package net.team.omega.core.network.serialization.connection;

import java.util.List;

import net.team.omega.core.database.HibernateFactory;
import net.team.omega.core.database.table.Account;
import net.team.omega.core.network.loginserver.ClientConnection;
import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.utils.CryptUtils;
import net.team.omega.utils.LogHandler;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class ConnectionLogin extends MessageData
{

    private String user;
    private String password;
    
    public ConnectionLogin()
    {
	
    }
    
    @Override
    public void process(ClientConnection connection)
    {
	Session _session = HibernateFactory.getSession();
	
	Criteria _criteria = _session.createCriteria(Account.class)
	    .add(Restrictions.and(
		    Restrictions.eq("user", user), 
		    Restrictions.eq("password", CryptUtils.securePassword(password)))).setMaxResults(1);
	
	List<Account> _result = _criteria.list();
	
	_session.close();

	if (!_result.isEmpty())
	{
	    Account _account = _result.get(0);
	    connection.getClientDatas().setAccount(_account); // Bind account to current connection
	    if (_account.isBanned())
	    {
		LogHandler.warning("Client " + _account.getUser() + " (" + connection.getRemoteAddressTCP().getHostName() + ") try to connect but was banned !");
		connection.sendTCP(new ConnectionLoginBanned()); // Send banned message

		return;
	    }
	    
	    connection.sendTCP(new ConnectionLoginAccept()); // Send accept message
	}
	else
	    connection.sendTCP(new ConnectionLoginRefuse()); // Send refuse message
    }

    public String getUser()
    {
	return user;
    }

    public String getPassword()
    {
	return password;
    }
    
}
