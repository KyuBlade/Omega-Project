package net.team.omega.core.network.communication.serialization.data;


public class GameServer
{

    private byte id;
    private String name;
    private String ip;
    private int tcpPort;
    private int udpPort;
    
    public GameServer()
    {
	
    }

    public byte getId()
    {
        return id;
    }

    public void setId(byte id)
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

    public String getIp()
    {
        return ip;
    }
    
    public void setIp(String ip)
    {
        this.ip = ip;
    }
    
    public int getTcpPort()
    {
        return tcpPort;
    }
    
    public void setTcpPort(int tcpPort)
    {
        this.tcpPort = tcpPort;
    }
    
    public int getUdpPort()
    {
        return udpPort;
    }
    
    public void setUdpPort(int udpPort)
    {
        this.udpPort = udpPort;
    }
    
}
