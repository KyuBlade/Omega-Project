package net.team.omega.communication.serialization.datas;

import net.team.omega.communication.serialization.RawData;


public class GameServer extends RawData
{

    private byte id;
    private String name;
    private String ip;
    private int tcpPort;
    private int udpPort;
    private byte state;
    
    public GameServer()
    {
	
    }
    
    public GameServer(String host, int tcp, int udp)
    {
        this.ip = host;
        this.tcpPort = tcp;
        this.udpPort = udp;
    }

    public byte getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getIp()
    {
        return ip;
    }
    
    public int getTcpPort()
    {
        return tcpPort;
    }
    
    public int getUdpPort()
    {
        return udpPort;
    }
    
    public byte getState()
    {
        return state;
    }
    
}
