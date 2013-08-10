package net.team.omega.core.network.serialization.datas;

public class SamplePlayer
{

    private int id;
    private String name;
    private int level;
    private short breed;

    public SamplePlayer()
    {
	
    }
    
    public SamplePlayer(int id, String name, short breed, int level)
    {
	this.id = id;
	this.name = name;
	this.breed = breed;
	this.level = level;
    }

    public int getId()
    {
	return id;
    }

    public String getName()
    {
	return name;
    }

    public int getLevel()
    {
	return level;
    }

    public short getBreed()
    {
	return breed;
    }

}
