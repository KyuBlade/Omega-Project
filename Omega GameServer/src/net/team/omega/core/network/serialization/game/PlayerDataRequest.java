package net.team.omega.core.network.serialization.game;

import net.team.omega.core.network.gameserver.ClientConnection;
import net.team.omega.core.network.serialization.MessageData;


public class PlayerDataRequest extends MessageData
{

    //private List<RemoteEntity> entities;
    
    public PlayerDataRequest()
    {
	
    }
    
    @Override
    public void process(ClientConnection connection)
    {
	/*entities = new ArrayList<RemoteEntity>();
	
	EntitySet _set = MasterEntityData.getInstance().getEntities(
		PlayerComponent.class, 
		ModelTransactionComponent.class, 
		HealthComponent.class, 
		LocationComponent.class, 
		NameComponent.class, 
		RotationComponent.class, 
		SpeedComponent.class, 
		BonusSpeedComponent.class, 
		BasicMovementComponent.class, 
		HumanMovementComponent.class);

	for(Entity _p : _set)
	{
	    if(MasterEntityData.getInstance().getComponent(_p.getId(), InitializedComponent.class).isInitialized())
	    {
		if(_p.getId().equals(connection.getPlayer()))
		{
		    RemoteEntity _toSend = new RemoteEntity(_p.getId(), _p.getComponents());
		    
		    RemoteEntity _e = new RemoteEntity(_p.getId(), _p.getComponents(), new ControlledPlayerComponent());
		    entities.add(_e);
		    
		    PlayerCreate _message = RecycleManager.getInstance().newObject(PlayerCreate.class);
		    _message.setEntity(_toSend);
		    GameServerFactory.getInstance().sendToAllInGameExceptTCP(connection.getID(), _message);
		}
		else
		    entities.add(new RemoteEntity(_p.getId(), _p.getComponents()));
	    }
	}
	
	connection.sendTCP(this);*/
    }
    
}
