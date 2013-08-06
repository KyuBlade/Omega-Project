package net.team.omega;


public class Constants
{

    // LoginServer bind
    public static final int SERVER_TCP_PORT = 54555;
    public static final int SERVER_UDP_PORT = 54666;
    
    // InternalLoginServer bind
    public static final int INTERNAL_SERVER_TCP_PORT = 49442;
    public static final int INTERNAL_SERVER_UDP_PORT = 49443;
    
    // Database connection
    public static final String DATABASE_HOST = "127.0.0.1";
    public static final String DATABASE_USER = "root";
    public static final String DATABASE_PASSWORD = "5PHUKFRzmvnYwFBB";
    public static final String DATABASE_BASE = "omega_login";
    
    // GameServer states
    public static final byte GAME_SERVER_STATE_OFFLINE = 1;
    public static final byte GAME_SERVER_STATE_ONLINE = 2;
    public static final byte GAME_SERVER_STATE_IDLE = 3;
    
}
