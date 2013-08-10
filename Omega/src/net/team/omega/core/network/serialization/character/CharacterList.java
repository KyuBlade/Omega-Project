package net.team.omega.core.network.serialization.character;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.team.omega.core.GameCore;
import com.team.omega.core.screen.CharacterSelectionScreen;

import net.team.omega.core.network.serialization.MessageData;
import net.team.omega.core.network.serialization.datas.SamplePlayer;

public class CharacterList extends MessageData
{

    private List<SamplePlayer> characterList = new ArrayList<>();
    
    public CharacterList()
    {
    }

    @Override
    public void process()
    {
	for(SamplePlayer _sample : characterList)
	    Gdx.app.debug("CharacterList", _sample.getName());
	
	GameCore.getInstance().getScreenManager().addScreen(CharacterSelectionScreen.class);
    }
    
}
