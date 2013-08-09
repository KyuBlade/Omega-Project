package net.team.omega.core.network.serialization.connection;

import net.team.omega.core.network.GameServerFactory;
import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.datas.GameServer;

public class FakeConnection extends MessageData
{

    public FakeConnection()
    {
        GameServerFactory.getInstance().start(new GameServer("25.185.18.225", 30456, 30458), "");
        
        GameServerFactory.getInstance().sendTCP(this);
    }
    
}
