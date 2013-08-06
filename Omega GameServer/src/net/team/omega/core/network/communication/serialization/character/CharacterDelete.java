package net.team.omega.core.network.communication.serialization.character;

import net.team.omega.core.network.communication.gameserver.ClientConnection;
import net.team.omega.core.network.communication.serialization.MessageData;

public class CharacterDelete extends MessageData
{

    //private EntityId entityId;

    public CharacterDelete()
    {

    }

    @Override
    public void process(ClientConnection connection)
    {
	// Check if character is owned by user
	/*Entity _character = MasterEntityData.getInstance().getEntity(entityId, PlayerComponent.class);
	if (_character == null)
	{
	    LogHandler.getInstance().warning("Account " + connection.getAccount().getId() + " try to delete an unloaded character");
	    connection.close();

	    return;
	}
	
	int _playerId = _character.get(PlayerComponent.class).getPlayerId();

	if (MasterEntityData.getInstance().getComponent(connection.getAccount(), AccountComponent.class).getAccountId() == _character.get(PlayerComponent.class).getAccountId())
	{
	    // DELETE
	    try
	    {
		PreparedStatement _pDeleteQuery = SQLFactory.getInstance().prepareQuery("DELETE FROM characters WHERE ID = ?");

		_pDeleteQuery.setInt(1, _character.get(PlayerComponent.class).getPlayerId());

		_pDeleteQuery.executeUpdate();
		
		MasterEntityData.getInstance().removeEntity(entityId);
		
		ConnectionGameServerAccept _message = RecycleManager.getInstance().newObject(ConnectionGameServerAccept.class);
		_message.preSend(connection);
		
		connection.sendTCP(_message);
		
		CharacterDeleted _dMessage = RecycleManager.getInstance().newObject(CharacterDeleted.class);
		
		connection.sendTCP(_dMessage);
	    } catch (SQLException e)
	    {
		LogHandler.getInstance().severe("Error when deleting character : " + _playerId);
    	    	LogHandler.getInstance().severe("Cause : " + e.getMessage());
	    }
	}
	else
	{
	    LogHandler.getInstance().warning("Account " + connection.getAccount().getId() + " try to delete an unowned character (" + _playerId + ")");
	    connection.close();

	    return;
	}*/
    }

}
