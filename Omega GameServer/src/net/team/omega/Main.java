package net.team.omega;

import net.team.omega.core.ServerCore;


public class Main
{

    private static ServerCore serverCore;
    
    public static void main(String[] args)
    {
	serverCore = new ServerCore();
	serverCore.start();
    }

}
