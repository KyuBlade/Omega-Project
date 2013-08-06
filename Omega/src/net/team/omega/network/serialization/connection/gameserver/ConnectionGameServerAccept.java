package net.team.omega.network.serialization.connection.gameserver;

import net.team.omega.network.serialization.MessageData;


public class ConnectionGameServerAccept extends MessageData
{
    
    private boolean needCharacterCreation;
    
    @Override
    public void process()
    {
        if(needCharacterCreation)
        {
            
        }
        else
        {
            
        }
    }
    
}
