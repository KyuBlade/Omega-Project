package net.team.omega.core.network.serialization.connection.gameserver;

import net.team.omega.core.network.serialization.MessageData;


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