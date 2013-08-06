package net.team.omega.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BasicUtils
{

    public static void onErrorExit()
    {
	LogHandler.system("Press any key to continue ...");
	BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
	try
	{
	    buffReader.readLine();
	} catch (IOException e)
	{
	    e.printStackTrace();
	}
	System.exit(1);

    }
    
}
