package net.team.omega.communication.serialization.connection;

import net.team.omega.communication.serialization.MessageData;

public class ConnectionLogin extends MessageData
{

    private String user;
    private String password;

    public ConnectionLogin()
    {
    }

    public ConnectionLogin(String user, String password)
    {
        this.user = user;
        this.password = password;
    }

    @Override
    public void process()
    {
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
