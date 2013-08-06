package net.team.omega.core.network.communication.serialization.gameserver.connection;

import net.team.omega.core.network.communication.gameserver.ClientConnection;
import net.team.omega.core.network.communication.serialization.MessageData;


public class FakeConnection extends MessageData
{

    public FakeConnection()
    {
	
    }
    
    @Override
    public void process(ClientConnection connection)
    {
	// Account creation
	/*EntityId _acc = MasterEntityData.getInstance().createEntity();
	
	MasterEntityData.getInstance().setComponents(_acc, 
		new AccountComponent(-1), 
		new NameComponent("Fake" + UUID.randomUUID().toString().replace("-", "")), 
		new AuthorizationComponent((short) 10));
	
	connection.setState(ConnectionState.CONNECTED);
	
	// Player creation
	EntityId _player = MasterEntityData.getInstance().createEntity();
	
	MasterEntityData.getInstance().setComponents(_player, 
		new PlayerComponent(-1, -1),
		new SurvivalTimeComponent(0), 
		new LocationComponent(new Vector3f(0.0f, 1.0f, 0.0f)),
		new RotationComponent(new Quaternion(0.0f, 0.0f, 0.0f, 0.0f)),
		new NameComponent("Fake-" + UUID.randomUUID().toString().replace("-", "")),
		new HealthComponent(0, Constants.CHARACTER_DATA_MAX_LIFE, Constants.CHARACTER_DATA_MAX_LIFE),
		new ModelTransactionComponent(2), 
		new SpeedComponent(5.0f), 
		new BonusSpeedComponent(0f), 
		new BasicMovementComponent(), 
		new HumanMovementComponent(), 
		new InitializedComponent(true));
	
	// Bind player to account
	MasterEntityData.getInstance().setComponent(_acc, new PlayerAccountComponent(_player));
	
	connection.setAccount(_acc);
	
	connection.sendTCP(RecycleManager.getInstance().newObject(StartGame.class));*/
    }
    
}
