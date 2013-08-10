package net.team.omega.core.network.serialization.connection.gameserver;

import java.util.ArrayList;

import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.datas.GameServer;

import com.team.omega.core.GameCore;
import com.team.omega.core.LocalizationHandler;
import com.team.omega.core.screen.GameServerSelectionScreen;
import com.team.omega.core.screen.IdentificationScreen;
import com.team.omega.ui.list.ListRow;

public class ConnectionGameServerList extends MessageData
{

    private ArrayList<GameServer> gameservers;

    @Override
    public void process()
    {
	ListRow[] _items = new ListRow[gameservers.size()];
	int i = 0;
        for(GameServer _gs : gameservers)
        {
            ListRow _item = new ListRow();
            String[] _value = new String[3];
            _value[0] = _gs.getName();
            
            String _state = "";
            switch(_gs.getState())
            {
        	case IDLE:
        	    _state = "gameserver.state.idle";
        	    
        	    break;
        	    
        	case OFFLINE:
        	    _state = "gameserver.state.offline";
        	    
        	    break;
        	    
        	case ONLINE:
        	    _state = "gameserver.state.online";
        	    
        	    break;
        	    
        	case FULL:
        	    _state = "gameserver.state.full";
        	    
        	    break;
        	    
        	default:
        	    _state = "common.error.undefined";
        	    
        	    break;
            }
            _state = LocalizationHandler.getInstance().getDialog(_state);
            _value[1] = _state;
            _value[2] = "9999";
            
            _item.setContent(_value);
            
            // Add datas to the row
            Object[] _datas = {_gs};
            _item.setStore(_datas);
            
            _items[i] = _item;
            
            i++;
        }
        
        GameCore.getInstance().getScreenManager().removeScreen(IdentificationScreen.class);
        GameServerSelectionScreen _screen = GameCore.getInstance().getScreenManager().addScreen(GameServerSelectionScreen.class);
        if(_screen != null)
        {
            _screen.getServerList().setItems(_items);
            if(_items.length > 1)
        	_screen.getServerList().setSelectedIndex(1);
        }
        
    }

}
