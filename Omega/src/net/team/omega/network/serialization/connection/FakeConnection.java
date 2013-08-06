package net.team.omega.network.serialization.connection;

import net.team.omega.network.core.GameServerFactory;
import net.team.omega.network.serialization.MessageData;
import net.team.omega.network.serialization.datas.GameServer;

public class FakeConnection extends MessageData
{

    public FakeConnection()
    {
        GameServerFactory.getInstance().start(new GameServer("25.185.18.225", 30456, 30458));
        
        GameServerFactory.getInstance().sendTCP(this);
    }
    
}
