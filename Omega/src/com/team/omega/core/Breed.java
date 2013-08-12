package com.team.omega.core;


public enum Breed
{

    WARRIOR(1, "Warrior"), 
    ASSASSIN(2, "Assassin"), 
    TEMPLAR(3, "Templar"), 
    MAGE(4, "Mage"), 
    ARCHER(5, "Archer"), 
    MUSICIAN(6, "Musician");

    private int id;
    private String name;

    private Breed(int id, String name)
    {
	this.id = id;
	this.name = name;
    }

    public int getId()
    {
	return id;
    }

    public String getName()
    {
	return name;
    }

}
