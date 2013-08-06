package net.team.omega.communication.serialization.connection;

import net.team.omega.communication.core.GameServerFactory;
import net.team.omega.communication.serialization.MessageData;
import net.team.omega.communication.serialization.datas.GameServer;

public class FakeConnection extends MessageData
{

    public FakeConnection()
    {
        GameServerFactory.getInstance().start(new GameServer("25.185.18.225", 30456, 30458));
        
        GameServerFactory.getInstance().sendTCP(this);
    }
    
}
