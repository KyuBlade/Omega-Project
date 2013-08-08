package net.team.omega.core.network.serialization.connection;

import com.team.omega.core.GameCore;
import com.team.omega.core.LocalizationHandler;
import com.team.omega.core.screen.IdentificationScreen;

import net.team.omega.core.network.LoginServerFactory;
import net.team.omega.core.network.serialization.MessageData;


public class ConnectionLoginBanned extends MessageData
{
    
    @Override
    public void process()
    {
        GameCore.getInstance().getScreenManager().getScreen(IdentificationScreen.class).showPopup(LocalizationHandler.getInstance().getDialog("login.error.banned"));
        LoginServerFactory.getInstance().disconnect();
    }
        
}
