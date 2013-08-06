package net.team.omega.core.network.communication;

import java.util.ArrayList;

import net.team.omega.core.network.communication.queue.WaitingConnection;
import net.team.omega.core.network.communication.serialization.character.CharacterCreation;
import net.team.omega.core.network.communication.serialization.character.CharacterCreationMaxCapacityReached;
import net.team.omega.core.network.communication.serialization.character.CharacterCreationNameTaken;
import net.team.omega.core.network.communication.serialization.character.CharacterDelete;
import net.team.omega.core.network.communication.serialization.character.CharacterDeleted;
import net.team.omega.core.network.communication.serialization.character.CharacterList;
import net.team.omega.core.network.communication.serialization.character.CharacterSelection;
import net.team.omega.core.network.communication.serialization.data.Account;
import net.team.omega.core.network.communication.serialization.game.PlayerCreate;
import net.team.omega.core.network.communication.serialization.game.PlayerDataRequest;
import net.team.omega.core.network.communication.serialization.game.PlayerRemove;
import net.team.omega.core.network.communication.serialization.game.StartGame;
import net.team.omega.core.network.communication.serialization.gameserver.connection.ConnectionGameServerAccept;
import net.team.omega.core.network.communication.serialization.gameserver.connection.ConnectionGameServerConnect;
import net.team.omega.core.network.communication.serialization.gameserver.connection.FakeConnection;
import net.team.omega.core.network.communication.serialization.internal.InternalGameServerDatas;
import net.team.omega.core.network.communication.serialization.internal.InternalGameServerDisconnect;
import net.team.omega.core.network.communication.serialization.internal.InternalGameServerGenerateKey;
import net.team.omega.core.network.communication.serialization.internal.InternalGameServerGeneratedKey;
import net.team.omega.core.network.communication.serialization.internal.InternalGameServerKey;
import net.team.omega.core.network.communication.serialization.internal.InternalGameServerUpdate;

import com.esotericsoftware.kryo.Kryo;


public class ClassRegister
{
    
    private static Class<?>[] clientClass = 
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
            
            PlayerCreate.class, 
            PlayerDataRequest.class, 
            PlayerRemove.class
        };
    
    private static Class<?>[] loginClass = 
	{
		Account.class, 
            	
		InternalGameServerDatas.class, 
		InternalGameServerUpdate.class, 
		InternalGameServerKey.class, 
		InternalGameServerGenerateKey.class, 
		InternalGameServerGeneratedKey.class, 
		InternalGameServerDisconnect.class, 
		
		WaitingConnection.class
	};

    public ClassRegister()
    {
	
    }
    
    public static void clientRegister(Kryo kryo)
    {
	for(Class<?> _clazz : clientClass)
	    kryo.register(_clazz);
    }
    
    public static void internalRegister(Kryo kryo)
    {
	for(Class<?> _clazz : loginClass)
	    kryo.register(_clazz);
    }
    
}
