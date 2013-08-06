package net.team.omega.core.database.table;



public class Account
{

    private int id;
    private String user;
    private String password;
    private String email;
    private short rights;
    private boolean banned;

    public int getId()
    {
	return id;
    }

    public void setId(int id)
    {
	this.id = id;
    }

    public String getUser()
    {
	return user;
    }

    public void setUser(String user)
    {
	this.user = user;
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
	return banned;
    }

    public void setBanned(boolean banned)
    {
	this.banned = banned;
    }

}
