package net.team.omega.core.database.table;

import net.team.omega.core.network.gameserver.GameServerState;
import net.team.omega.core.network.loginserver.LoginServerFactory;
import net.team.omega.core.network.serialization.RawData;

public class GameServer extends RawData
{

    private byte id;
    private String name;
    private String ip;
    private int tcpPort;
    private int udpPort;
    private GameServerState state;

    public GameServer()
    {
	state = GameServerState.OFFLINE;
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

    public GameServerState getState()
    {
	return state;
    }

    public void setState(GameServerState state)
    {
	this.state = state;
	
	LoginServerFactory.getInstance().updateGameserverState();
    }

}
