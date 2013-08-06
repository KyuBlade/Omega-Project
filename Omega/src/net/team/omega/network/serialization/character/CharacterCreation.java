package net.team.omega.network.serialization.character;

import net.team.omega.network.serialization.MessageData;

public class CharacterCreation extends MessageData
{

    private int modelID;
    private String name;

    public CharacterCreation()
    {
        
    }

    public void setModelID(int modelID)
    {
        this.modelID = modelID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
