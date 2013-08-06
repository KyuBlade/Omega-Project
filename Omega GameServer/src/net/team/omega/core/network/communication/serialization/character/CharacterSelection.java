package net.team.omega.core.network.communication.serialization.character;

import net.team.omega.core.network.communication.gameserver.ClientConnection;
import net.team.omega.core.network.communication.serialization.MessageData;


public class CharacterSelection extends MessageData
{

    //private EntityId entityId;
    
    @Override
    public void process(ClientConnection connection)
    {
	// Check if character is owned by user
	/*Entity _e = MasterEntityData.getInstance().getEntity(entityId, PlayerComponent.class);

	if(_e == null)
	{
	    LogHandler.getInstance().warning("Account " + MasterEntityData.getInstance().getComponent(connection.getAccount(), AccountComponent.class).getAccountId() + " try to connect with an unloaded character");
	    connection.close();
	    
	    return;
	}
	
	// Can connect with this character
	if(MasterEntityData.getInstance().getComponent(connection.getAccount(), AccountComponent.class).getAccountId() == _e.get(PlayerComponent.class).getAccountId())
	{
	    connection.setPlayer(entityId);
	    
	    MasterEntityData.getInstance().setComponent(entityId, new InitializedComponent(true));
	    
	    connection.sendTCP(RecycleManager.getInstance().newObject(StartGame.class));
	}
	else
	{
	    LogHandler.getInstance().warning("Account " + MasterEntityData.getInstance().getComponent(connection.getAccount(), AccountComponent.class).getAccountId() + " try to connect with an unowned character");
	    connection.close();
	    
	    return;
	}*/
    }
    
}
