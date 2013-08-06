package net.team.omega;



public class Constants
{

    // GameServer bind
    public static final int SERVER_TCP_PORT = 30456;
    public static final int SERVER_UDP_PORT = 30458;
    
    // Internal connection to LoginServer
    public static final String LOGIN_SERVER_HOST = "25.185.18.225";
    public static final int LOGIN_SERVER_TCP_PORT = 49442;
    public static final int LOGIN_SERVER_UDP_PORT = 49443;
    public static final int LOGIN_SERVER_TIMEOUT = 5000;
    public static final int LOGIN_SERVER_RETRY = 5000;
    
    // Database connection
    public static final String DATABASE_HOST = "127.0.0.1";
    public static final String DATABASE_USER = "root";
    public static final String DATABASE_PASSWORD = "5PHUKFRzmvnYwFBB";
    public static final String DATABASE_BASE = "omega_game";

    // GameServer states
    public static final byte GAME_SERVER_STATE_OFFLINE = 1;
    public static final byte GAME_SERVER_STATE_ONLINE = 2;
    public static final byte GAME_SERVER_STATE_IDLE = 3;
    
    // Character data
    public static final int CHARACTER_MAX_CAPACITY = 3;
    public static final int CHARACTER_DATA_MAX_LIFE = 100;
    public static final int CHARACTER_NAME_MIN_LENGTH = 3;
    public static final int CHARACTER_NAME_MAX_LENGTH = 12;
    
}
