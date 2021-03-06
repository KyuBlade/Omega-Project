package net.team.omega.core.database.table;

public class Player
{

    private int id;
    private String name;
    private int breed;
    private int sex;
    private int level;
    private int life;
    private float x, y;
    private int accountId;

    public int getId()
    {
	return id;
    }

    public void setId(int id)
    {
	this.id = id;
    }

    public String getName()
    {
	return name;
    }

    public void setName(String name)
    {
	this.name = name;
    }

    public int getBreed()
    {
	return breed;
    }

    public void setBreed(int breed)
    {
	this.breed = breed;
    }

    public int getSex()
    {
        return sex;
    }
    
    public void setSex(int sex)
    {
        this.sex = sex;
    }
    
    public int getLevel()
    {
	return level;
    }

    public void setLevel(int level)
    {
	this.level = level;
    }

    public int getLife()
    {
	return life;
    }

    public void setLife(int life)
    {
	this.life = life;
    }

    public float getX()
    {
	return x;
    }

    public void setX(float x)
    {
	this.x = x;
    }

    public float getY()
    {
	return y;
    }

    public void setY(float y)
    {
	this.y = y;
    }

    public int getAccountId()
    {
	return accountId;
    }

    public void setAccountId(int accountId)
    {
	this.accountId = accountId;
    }

}
