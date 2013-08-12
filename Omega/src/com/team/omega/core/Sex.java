package com.team.omega.core;


public enum Sex
{

    MALE(1, "Male"), 
    FEMALE(2, "Female");

    private int id;
    private String name;

    private Sex(int id, String name)
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
