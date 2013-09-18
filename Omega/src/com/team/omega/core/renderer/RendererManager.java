package com.team.omega.core.renderer;

import com.artemis.Entity;
import com.artemis.utils.Bag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.Event;
import com.esotericsoftware.spine.Skeleton;
import com.team.omega.core.GameCore;
import com.team.omega.core.entity.component.render.SkeletonComponent;
import com.team.omega.core.entity.component.transform.PositionComponent;
import com.team.omega.core.entity.manager.PlayerManager;
import com.team.omega.core.screen.GameScreen;
import com.team.omega.core.screen.MasterScreen;


public class RendererManager
{

    private CameraRenderer camRenderer;
    private IsometricMapRenderer mapRenderer;
    private PlayerRenderer playerRenderer;
    
    private float lastTime;
    private Array<Event> events = new Array<>();
    
    private SpriteBatch batch;
    
    private String skin = "goblin";

    public RendererManager(TiledMap map)
    {
	batch = new SpriteBatch();
	
	camRenderer = new CameraRenderer(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	mapRenderer = new IsometricMapRenderer(map, 1f, batch);
	playerRenderer = new PlayerRenderer(batch);
	
	MasterScreen.getInputProcessor().addProcessor(new InputProcessor(){

	    @Override
	    public boolean keyDown(int keycode)
	    {
		if(keycode == Keys.A)
		{
		    if(skin.equals("goblin"))
			skin = "goblingirl";
		    else
			skin = "goblin";
		}
		
		return true;
	    }

	    @Override
	    public boolean keyUp(int keycode)
	    {
		return false;
	    }

	    @Override
	    public boolean keyTyped(char character)
	    {
		return false;
	    }

	    @Override
	    public boolean touchDown(int screenX, int screenY, int pointer, int button)
	    {
		return false;
	    }

	    @Override
	    public boolean touchUp(int screenX, int screenY, int pointer, int button)
	    {
		return false;
	    }

	    @Override
	    public boolean touchDragged(int screenX, int screenY, int pointer)
	    {
		return false;
	    }

	    @Override
	    public boolean mouseMoved(int screenX, int screenY)
	    {
		return false;
	    }

	    @Override
	    public boolean scrolled(int amount)
	    {
		return false;
	    }
	    
	});
    }

    public void render(float delta)
    {
	PlayerManager _playerMgr = GameCore.getInstance().getScreenManager().getScreen(GameScreen.class).getWorldManager().getManager(PlayerManager.class);
	Bag<Entity> _players = _playerMgr.getPlayers();
	
	camRenderer.render(delta);
	mapRenderer.setView(camRenderer.getCamera());
	mapRenderer.render(delta);
	
	for(int i = 0; i < _players.size(); i++)
	{
	    Entity _player = _players.get(i);
	    Skeleton _skeleton = _player.getComponent(SkeletonComponent.class).getSkeleton();
	    PositionComponent _position = _player.getComponent(PositionComponent.class);
	    
	    float _x = _position.getX();
	    float _y = _position.getY();
	    
	    _skeleton.setToSetupPose();
	    _skeleton.setSkin(skin);
	    _skeleton.setX(_x - camRenderer.getCamera().position.x + camRenderer.getCamera().viewportWidth / 2);
	    _skeleton.setY(_y - camRenderer.getCamera().position.y + camRenderer.getCamera().viewportHeight / 2);
	    events.clear();
	    _skeleton.getData().findAnimation("walk").apply(_skeleton, lastTime, lastTime + delta, true, events);
	    _skeleton.updateWorldTransform();
	    _skeleton.update(delta);
	    
	    batch.begin();
	    playerRenderer.render(_skeleton, delta);
	    batch.end();
	}
	
	lastTime += delta;
    }

    public CameraRenderer getCamRenderer()
    {
	return camRenderer;
    }

    public void setCamRenderer(CameraRenderer camRenderer)
    {
	this.camRenderer = camRenderer;
    }

    public IsometricMapRenderer getMapRenderer()
    {
	return mapRenderer;
    }

    public void setMapRenderer(IsometricMapRenderer mapRenderer)
    {
	this.mapRenderer = mapRenderer;
    }

    public PlayerRenderer getPlayerRenderer()
    {
	return playerRenderer;
    }

    public void setPlayerRenderer(PlayerRenderer playerRenderer)
    {
	this.playerRenderer = playerRenderer;
    }

}
