package net.team.omega.communication.core;

import java.util.ArrayList;

import net.team.omega.communication.serialization.character.CharacterCreation;
import net.team.omega.communication.serialization.character.CharacterCreationMaxCapacityReached;
import net.team.omega.communication.serialization.character.CharacterCreationNameTaken;
import net.team.omega.communication.serialization.character.CharacterDelete;
import net.team.omega.communication.serialization.character.CharacterDeleted;
import net.team.omega.communication.serialization.character.CharacterList;
import net.team.omega.communication.serialization.character.CharacterSelection;
import net.team.omega.communication.serialization.connection.ConnectionLogin;
import net.team.omega.communication.serialization.connection.ConnectionLoginAccept;
import net.team.omega.communication.serialization.connection.ConnectionLoginBanned;
import net.team.omega.communication.serialization.connection.ConnectionLoginRefuse;
import net.team.omega.communication.serialization.connection.FakeConnection;
import net.team.omega.communication.serialization.connection.gameserver.ConnectionGameServerAccept;
import net.team.omega.communication.serialization.connection.gameserver.ConnectionGameServerConnect;
import net.team.omega.communication.serialization.connection.gameserver.ConnectionGameServerInit;
import net.team.omega.communication.serialization.connection.gameserver.ConnectionGameServerKey;
import net.team.omega.communication.serialization.connection.gameserver.ConnectionGameServerList;
import net.team.omega.communication.serialization.connection.gameserver.ConnectionGameServerSelect;
import net.team.omega.communication.serialization.datas.GameServer;
import net.team.omega.communication.serialization.game.PlayerCreate;
import net.team.omega.communication.serialization.game.PlayerDataRequest;
import net.team.omega.communication.serialization.game.PlayerRemove;
import net.team.omega.communication.serialization.game.StartGame;
import net.team.omega.communication.serialization.game.movement.GameMovement;
import net.team.omega.communication.serialization.game.movement.GameMovementViewDirection;
import net.team.omega.communication.serialization.game.movement.GameMovementWalk;

import com.esotericsoftware.kryo.Kryo;


public class ClassRegister
{

    private static final Class<?>[] loginClass = 
	{
            ArrayList.class, 
            
            GameServer.class,
            
            ConnectionLogin.class, 
            ConnectionLoginAccept.class, 
            ConnectionLoginBanned.class, 
            ConnectionLoginRefuse.class, 
            
            ConnectionGameServerInit.class, 
            ConnectionGameServerList.class, 
            ConnectionGameServerSelect.class, 
            ConnectionGameServerKey.class
	};
    
    private static final Class<?>[] gameClass =
	{
            ArrayList.class, 
            
            ConnectionGameServerAccept.class, 
            ConnectionGameServerConnect.class, 
            
            CharacterCreation.class, 
            CharacterCreationNameTaken.class, 
            CharacterCreationMaxCapacityReached.class, 
            
            CharacterSelection.class, 
            CharacterList.class, 
            CharacterDelete.class, 
            CharacterDeleted.class, 
            
            StartGame.class, 
            
            FakeConnection.class, 
            
            GameMovement.class, 
            GameMovementWalk.class, 
            GameMovementViewDirection.class, 
            
            PlayerCreate.class, 
            PlayerDataRequest.class, 
            PlayerRemove.class,
	};
    
    public ClassRegister()
    {
	
    }
    
    public static void loginRegister(Kryo kryo)
    {
	for(Class<?> _clazz : loginClass)
	    kryo.register(_clazz);
    }
    
    public static void gameRegister(Kryo kryo)
    {
	for(Class<?> _clazz : gameClass)
	    kryo.register(_clazz);
    }
    
}
