package net.team.omega.core.network.serialization.character;

import net.team.omega.core.network.serialization.MessageData;

@SuppressWarnings("unused")
public class CharacterCreation extends MessageData
{
    
    private String name;
    private int breed;
    private int sex;

    public CharacterCreation()
    {
        
    }

    public CharacterCreation(String name, int breed, int sex)
    {
	this.name = name;
        this.breed = breed;
        this.sex = sex;
    }

}
