package net.team.omega.core.database.table;



public class Account
{

    private int id;
    private String name;
    private String password;
    private String email;
    private short rights;
    private boolean isBanned;

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

    public String getPassword()
    {
	return password;
    }

    public void setPassword(String password)
    {
	this.password = password;
    }

    public String getEmail()
    {
	return email;
    }

    public void setEmail(String email)
    {
	this.email = email;
    }

    public short getRights()
    {
	return rights;
    }

    public void setRights(short rights)
    {
	this.rights = rights;
    }

    public boolean isBanned()
    {
	return isBanned;
    }

    public void setIsBanned(boolean isBanned)
    {
	this.isBanned = isBanned;
    }

}
