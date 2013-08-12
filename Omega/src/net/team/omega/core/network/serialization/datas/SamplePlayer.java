package net.team.omega.core.network.serialization.datas;

public class SamplePlayer
{

    private int id;
    private String name;
    private int level;
    private int breed;
    private int sex;

    public SamplePlayer()
    {
	
    }
    
    public SamplePlayer(int id, String name, short breed, int sex, int level)
    {
	this.id = id;
	this.name = name;
	this.breed = breed;
	this.sex = sex;
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

    public int getBreed()
    {
	return breed;
    }
    
    public int getSex()
    {
	return sex;
    }

}
