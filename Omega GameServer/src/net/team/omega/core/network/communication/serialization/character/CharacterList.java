package net.team.omega.core.network.communication.serialization.character;

import net.team.omega.core.network.communication.gameserver.ClientConnection;
import net.team.omega.core.network.communication.serialization.MessageData;

public class CharacterList extends MessageData
{

    //private List<RemoteEntity> characterList = new ArrayList<RemoteEntity>();

    public CharacterList()
    {

    }

    @Override
    public void process(ClientConnection connection)
    {
	/*PreparedStatement _pQuery = SQLFactory.getInstance().prepareQuery("SELECT * FROM characters WHERE AccountID = ?");
	ResultSet _result;

	try
	{
	    _pQuery.setInt(1, MasterEntityData.getInstance().getComponent(connection.getAccount(), AccountComponent.class).getAccountId());

	    _result = _pQuery.executeQuery();

	    while (_result.next())
	    {
		int _playerId = _result.getInt("ID");
		String _name = _result.getString("Name");
		int _life = _result.getInt("Life");
		int _modelId = _result.getInt("ModelID");
		float _posX = _result.getFloat("PosX");
		float _posY = _result.getFloat("PosY");
		float _posZ = _result.getFloat("PosZ");
		float _rotX = _result.getFloat("RotX");
		float _rotY = _result.getFloat("RotY");
		float _rotZ = _result.getFloat("RotZ");
		long _survivalTime = _result.getLong("SurvivalTime");
		int _accountId = _result.getInt("AccountID");

		// Check if character already loaded
		ComponentFilter<PlayerComponent> _f = FieldFilter.create(PlayerComponent.class, "playerId", _playerId);
		EntityId _entityId = MasterEntityData.getInstance().findEntity(_f);
		
		LogHandler.getInstance().system("Loaded : " + _entityId);
		
		if (_entityId == null)
		{
		    _entityId = MasterEntityData.getInstance().createEntity();
		    MasterEntityData.getInstance().setComponents(_entityId, 
			    new PlayerComponent(_playerId, _accountId), 
			    new SurvivalTimeComponent(_survivalTime), 
			    new NameComponent(_name), 
			    new ModelTransactionComponent(_modelId), 
			    new HealthComponent(0, _life, Constants.CHARACTER_DATA_MAX_LIFE),
			    new LocationComponent(_posX, _posY, _posZ),
			    new RotationComponent(_rotX, _rotY, _rotZ), 
			    new InitializedComponent(false), 
			    new SpeedComponent(1.0f), 
			    new BasicMovementComponent(), 
			    new HumanMovementComponent());
		}
		
		characterList.add(new RemoteEntity(_entityId, NameComponent.class, ModelTransactionComponent.class, SurvivalTimeComponent.class));
		
	    }

	    _pQuery.close();

	    connection.sendTCP(this);
	} catch (SQLException e)
	{
	    LogHandler.getInstance().severe("Unable to get character list of account " + MasterEntityData.getInstance().getComponent(connection.getAccount(), AccountComponent.class).getAccountId());
	    LogHandler.getInstance().severe("Cause : " + e.getMessage());
	}*/
    }

}
