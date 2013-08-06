package net.team.omega.communication.serialization.connection.gameserver;

import net.team.omega.communication.serialization.MessageData;


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
