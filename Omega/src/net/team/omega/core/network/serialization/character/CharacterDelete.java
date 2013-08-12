package net.team.omega.core.network.serialization.character;

import net.team.omega.core.network.serialization.MessageData;

public class CharacterDelete extends MessageData
{
    
    @SuppressWarnings("unused")
    private int id;
    
    public CharacterDelete(int id)
    {
        this.id = id;
    }
    
}
