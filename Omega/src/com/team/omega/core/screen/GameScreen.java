package com.team.omega.core.screen;


import com.artemis.Entity;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.esotericsoftware.spine.Skeleton;
import com.team.omega.core.Constants;
import com.team.omega.core.GameCore;
import com.team.omega.core.asset.loader.SkeletonLoader;
import com.team.omega.core.entity.WorldManager;
import com.team.omega.core.entity.component.VelocityComponent;
import com.team.omega.core.entity.component.render.SkeletonComponent;
import com.team.omega.core.entity.component.transform.PositionComponent;
import com.team.omega.core.renderer.RendererManager;


public class GameScreen extends BaseScreen
{

    private RendererManager rendererMgr;
    private WorldManager worldMgr;
    
    private TiledMap map;
    
    public GameScreen(ScreenManager screenManager)
    {
	super(screenManager, Constants.GAME_SCREEN_DEPTH);
	
	GameCore.getInstance().getAssetManager().setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
	GameCore.getInstance().getAssetManager().setLoader(Skeleton.class, new SkeletonLoader(new InternalFileHandleResolver()));
	GameCore.getInstance().getAssetManager().load("data/maps/big.tmx", TiledMap.class);
	GameCore.getInstance().getAssetManager().load("data/spine/goblins.json", Skeleton.class);
	
	GameCore.getInstance().getAssetManager().finishLoading();
	
	map = GameCore.getInstance().getAssetManager().get("data/maps/big.tmx");
	
	// Initialize rendering
	rendererMgr = new RendererManager(map);
	
	worldMgr = new WorldManager("mainWorld");
	worldMgr.setup();
	worldMgr.start();
	
	Entity _e = worldMgr.createEntity();
	_e.addComponent(new SkeletonComponent("data/spine/goblins.json"));
	_e.addComponent(new PositionComponent(100f, 100f));
	_e.addToWorld();
	
	Entity _e3 = worldMgr.createEntity();
	_e3.addComponent(new SkeletonComponent("data/spine/goblins.json"));
	_e3.addComponent(new PositionComponent(500f, 400f));
	_e3.addToWorld();
	
	Entity _e2 = worldMgr.createEntity();
	_e2.addComponent(new VelocityComponent(1.0f));
	_e2.addToWorld();
    }
    
    @Override
    public void show()
    {
	super.show();
	
	screenManager.removeScreen(WaitingScreen.class);
    }
    
    @Override
    public void render(float delta)
    {
	//worldMgr.getSystem(RenderSystem.class).process();
	rendererMgr.render(delta);
    }
    
    public WorldManager getWorldManager()
    {
	return worldMgr;
    }
    
}
