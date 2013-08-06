package net.team.omega.core.network.communication.serialization.character;

import net.team.omega.core.network.communication.gameserver.ClientConnection;
import net.team.omega.core.network.communication.serialization.MessageData;

public class CharacterCreation extends MessageData
{

    private int modelID;
    private String name;

    @Override
    public void process(ClientConnection connection)
    {
	/*final Pattern _pattern = Pattern.compile("[^a-zA-Z-_0-9]");
	int _accountId = connection.getClientData().getAccount().getId();

	if (!name.isEmpty() && name.length() >= Constants.CHARACTER_NAME_MIN_LENGTH && name.length() <= Constants.CHARACTER_NAME_MAX_LENGTH)
	{
	    Matcher matcher = _pattern.matcher(name);
	    if (!matcher.find())
	    {
		// Can create a new character?
		PreparedStatement _pQuery = SQLFactory.getInstance().prepareQuery("SELECT COUNT(ID) AS count FROM characters WHERE AccountID = ?");
		ResultSet _result;

		try
		{
		    _pQuery.setInt(1, _accountId);

		    _result = _pQuery.executeQuery();

		    while (_result.next())
		    {
			int count = _result.getInt("count");
			if (count >= Constants.CHARACTER_MAX_CAPACITY) // Max capacity reached
			{
			    CharacterCreationMaxCapacityReached _message = RecycleManager.getInstance().newObject(CharacterCreationMaxCapacityReached.class);
			    connection.sendTCP(_message);

			    _pQuery.close();

			    return;
			}
		    }

		    _pQuery.close();
		} catch (SQLException e)
		{
		    LogHandler.getInstance().severe("Error when check if account   " + connection.getAccount().getId() + " have reached maximum character capacity");
		    LogHandler.getInstance().severe("Cause : " + e.getMessage());

		    return;
		}

		// Find if name is already used
		_pQuery = null;
		_pQuery = SQLFactory.getInstance().prepareQuery("SELECT COUNT(ID) AS count FROM characters WHERE Name LIKE ?");
		_result = null;

		try
		{
		    _pQuery.setString(1, name);

		    _result = _pQuery.executeQuery();

		    while (_result.next())
		    {
			int count = _result.getInt("count");
			if (count == 0) // Name unused
			{
			    // Get initial position
			    Vector3f _initPos = new Vector3f(10.0f, 10.0f, 10.0f);

			    // Get initial rotation
			    Quaternion _initRot = new Quaternion(0.0f, 0.0f, 0.0f, 0.0f);

			    // Add new character to database
			    PreparedStatement _pInsertQuery = SQLFactory.getInstance().prepareQuery("INSERT INTO characters " + "(Name, ModelID, Life, SurvivalTime, PosX, PosY, PosZ, RotX, RotY, RotZ, AccountID) " + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			    _pInsertQuery.setString(1, name);
			    _pInsertQuery.setInt(2, modelID);
			    _pInsertQuery.setInt(3, Constants.CHARACTER_DATA_MAX_LIFE);
			    _pInsertQuery.setInt(4, 0);
			    _pInsertQuery.setFloat(5, _initPos.x);
			    _pInsertQuery.setFloat(6, _initPos.y);
			    _pInsertQuery.setFloat(7, _initPos.z);
			    _pInsertQuery.setFloat(8, _initRot.getX());
			    _pInsertQuery.setFloat(9, _initRot.getY());
			    _pInsertQuery.setFloat(10, _initRot.getZ());
			    _pInsertQuery.setInt(11, _accountId);

			    _pInsertQuery.executeUpdate();

			    int _id = -1;
			    ResultSet _lastID = _pInsertQuery.getGeneratedKeys();
			    while (_lastID != null && _lastID.next())
			    {
				_id = _lastID.getInt(1);
			    }

			    // Create character entity
			    EntityId _e = MasterEntityData.getInstance().createEntity();

			    MasterEntityData.getInstance().setComponents(_e, new PlayerComponent(_id, _accountId), 
				    new SurvivalTimeComponent(0), 
				    new LocationComponent(new Vector3f(0f, 3f, 0f)), 
				    new RotationComponent(
				    new Quaternion(0f, 0f, 0f, 0f)), 
				    new NameComponent(name), 
				    new HealthComponent(0, Constants.CHARACTER_DATA_MAX_LIFE, Constants.CHARACTER_DATA_MAX_LIFE), 
				    new ModelTransactionComponent(modelID), 
				    new SpeedComponent(1f), 
				    new InitializedComponent(true), 
				    new BasicMovementComponent(), 
				    new HumanMovementComponent());

			    // Bind the player to the connection
			    connection.setPlayer(_e);
			    if(connection != null && _e != null)
			    {}

			    // Send to client CharacterSelection state change
			    StartGame _message = RecycleManager.getInstance().newObject(StartGame.class);

			    connection.sendTCP(_message);
			}
			else
			// Name already in use
			{
			    CharacterCreationNameTaken message = RecycleManager.getInstance().newObject(CharacterCreationNameTaken.class);

			    connection.sendTCP(message);
			}
		    }

		    _pQuery.close();
		} catch (SQLException e)
		{
		    LogHandler.getInstance().severe("Error when trying to find or create character name : " + name);
		    LogHandler.getInstance().severe("Cause : " + e.getMessage());
		}

	    }
	    else
		connection.close();
	}
	else
	    connection.close();*/
    }

}
