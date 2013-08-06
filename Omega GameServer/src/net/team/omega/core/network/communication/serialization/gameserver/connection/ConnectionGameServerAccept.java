package net.team.omega.core.network.communication.serialization.gameserver.connection;

import net.team.omega.core.network.communication.gameserver.ClientConnection;
import net.team.omega.core.network.communication.serialization.MessageData;

public class ConnectionGameServerAccept extends MessageData
{

    private boolean needCharacterCreation;

    @Override
    public void preSend(ClientConnection connection)
    {
	/*PreparedStatement _pQuery = SQLFactory.getInstance().prepareQuery("SELECT COUNT(ID) AS count FROM characters WHERE AccountID = ?");
	ResultSet _result;

	try
	{
	    _pQuery.setInt(1, MasterEntityData.getInstance().getComponent(connection.getAccount(), AccountComponent.class).getAccountId());

	    _result = _pQuery.executeQuery();

	    while (_result.next())
	    {
		if(_result.getInt("count") == 0)
		{
		    needCharacterCreation = true;
		}
		else
		{
		    needCharacterCreation = false;
		}
		
	    }
	    
	    _pQuery.close();
	} catch (SQLException e)
	{
	    LogHandler.getInstance().severe("Unable to know if accountID " + MasterEntityData.getInstance().getComponent(connection.getAccount(), AccountComponent.class).getAccountId() + " have characters.");
	    LogHandler.getInstance().severe("Cause : " + e.getMessage());
	}*/
    }

}
