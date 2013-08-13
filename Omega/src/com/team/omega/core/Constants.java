package com.team.omega.core;


public final class Constants
{

    // Localization
    public static final String DIALOGS_PATH = "data/langs/";
    public static final String DIALOGS_PREFIX = "dialogs_";
    
    // LoginServer config
    public static final String LOGIN_SERVER_HOST = "127.0.0.1";
    public static final int LOGIN_SERVER_TIMEOUT = 5000;
    public static final int LOGIN_SERVER_TCP_PORT = 54555;
    public static final int LOGIN_SERVER_UDP_PORT = 54666;
    
    public static final int GAME_SERVER_TIMEOUT = 5000;
    
    // Character constraints
    public static final int CHARACTER_NAME_MIN_LENGTH = 3;
    public static final int CHARACTER_NAME_MAX_LENGTH = 12;
    public static final int CHARACTER_MAX_CAPACITY = 3;
    
    // GameServer states
    public static final byte GAME_SERVER_STATE_OFFLINE = 1;
    public static final byte GAME_SERVER_STATE_ONLINE = 2;
    public static final byte GAME_SERVER_STATE_IDLE = 3;
    
    // Screens depth
    public static final int IDENTIFICATION_SCREEN_DEPTH = 1;
    public static final int GAMESERVER_SELECTION_SCREEN_DEPTH = 2;
    public static final int CHARACTER_SELECTION_SCREEN_DEPTH = 3;
    public static final int GAME_SCREEN_DEPTH = 4;
    public static final int WAITING_SCREEN_DEPTH = 19;
    public static final int DEBUG_SCREEN_DEPTH = 20;
    
}
