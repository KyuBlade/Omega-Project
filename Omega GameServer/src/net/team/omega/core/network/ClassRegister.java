package net.team.omega.core.network;

import java.util.ArrayList;

import net.team.omega.core.network.queue.WaitingConnection;
import net.team.omega.core.network.serialization.character.CharacterCreation;
import net.team.omega.core.network.serialization.character.CharacterCreationMaxCapacityReached;
import net.team.omega.core.network.serialization.character.CharacterCreationNameTaken;
import net.team.omega.core.network.serialization.character.CharacterDelete;
import net.team.omega.core.network.serialization.character.CharacterDeleted;
import net.team.omega.core.network.serialization.character.CharacterList;
import net.team.omega.core.network.serialization.character.CharacterSelection;
import net.team.omega.core.network.serialization.data.Account;
import net.team.omega.core.network.serialization.game.PlayerCreate;
import net.team.omega.core.network.serialization.game.PlayerDataRequest;
import net.team.omega.core.network.serialization.game.PlayerRemove;
import net.team.omega.core.network.serialization.game.StartGame;
import net.team.omega.core.network.serialization.gameserver.connection.ConnectionGameServerAccept;
import net.team.omega.core.network.serialization.gameserver.connection.ConnectionGameServerConnect;
import net.team.omega.core.network.serialization.gameserver.connection.FakeConnection;
import net.team.omega.core.network.serialization.internal.InternalGameServerDatas;
import net.team.omega.core.network.serialization.internal.InternalGameServerDisconnect;
import net.team.omega.core.network.serialization.internal.InternalGameServerGenerateKey;
import net.team.omega.core.network.serialization.internal.InternalGameServerGeneratedKey;
import net.team.omega.core.network.serialization.internal.InternalGameServerKey;
import net.team.omega.core.network.serialization.internal.InternalGameServerUpdate;

import com.esotericsoftware.kryo.Kryo;


public class ClassRegister
{
    
    private static Class<?>[] clientClass = 
	{
            ArrayList.class, 
            	
            ConnectionGameServerAccept.class, 
            ConnectionGameServerConnect.class, 
            
            CharacterList.SamplePlayer.class, 
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
		
		GameServerState.class, 
            	
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
