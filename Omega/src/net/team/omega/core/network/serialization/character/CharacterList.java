package net.team.omega.core.network.serialization.character;

import java.util.ArrayList;
import java.util.List;

import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.datas.SamplePlayer;

import com.team.omega.core.GameCore;
import com.team.omega.core.screen.CharacterSelectionScreen;

public class CharacterList extends MessageData
{

    private List<SamplePlayer> characterList = new ArrayList<>();
    
    public CharacterList()
    {
    }

    @Override
    public void process()
    {
	GameCore.getInstance().getScreenManager().addScreen(CharacterSelectionScreen.class).addPlayers(characterList);
    }
    
}
