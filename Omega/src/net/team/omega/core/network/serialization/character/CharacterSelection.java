package net.team.omega.core.network.serialization.character;

import net.team.omega.core.network.serialization.MessageData;

public class CharacterSelection extends MessageData
{

    @SuppressWarnings("unused")
    private int id;
    
    public CharacterSelection(int id)
    {
        this.id = id;
    }
    
}
