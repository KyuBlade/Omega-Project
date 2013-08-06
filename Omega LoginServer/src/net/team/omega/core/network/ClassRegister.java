package net.team.omega.core.network;

import java.util.ArrayList;

import net.team.omega.core.database.table.Account;
import net.team.omega.core.database.table.GameServer;
import net.team.omega.core.network.serialization.connection.ConnectionLogin;
import net.team.omega.core.network.serialization.connection.ConnectionLoginAccept;
import net.team.omega.core.network.serialization.connection.ConnectionLoginBanned;
import net.team.omega.core.network.serialization.connection.ConnectionLoginRefuse;
import net.team.omega.core.network.serialization.connection.gameserver.ConnectionGameServerInit;
import net.team.omega.core.network.serialization.connection.gameserver.ConnectionGameServerKey;
import net.team.omega.core.network.serialization.connection.gameserver.ConnectionGameServerList;
import net.team.omega.core.network.serialization.connection.gameserver.ConnectionGameServerSelect;
import net.team.omega.core.network.serialization.internal.InternalGameServerDatas;
import net.team.omega.core.network.serialization.internal.InternalGameServerDisconnect;
import net.team.omega.core.network.serialization.internal.InternalGameServerGenerateKey;
import net.team.omega.core.network.serialization.internal.InternalGameServerGeneratedKey;
import net.team.omega.core.network.serialization.internal.InternalGameServerKey;
import net.team.omega.core.network.serialization.internal.InternalGameServerUpdate;
import net.team.omega.core.network.serialization.internal.WaitingConnection;

import com.esotericsoftware.kryo.Kryo;


public class ClassRegister
{

    private static Class<?>[] gameserverClass =
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
    
    private static Class<?>[] loginClass =
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
    
    public ClassRegister()
    {
	
    }
    
    public static void gameserverRegister(Kryo kryo)
    {
	for(Class<?> _clazz : gameserverClass)
	    kryo.register(_clazz);
    }
    
    public static void loginRegister(Kryo kryo)
    {
	for(Class<?> _clazz : loginClass)
	    kryo.register(_clazz);
    }
    
}
