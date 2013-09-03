package com.team.omega.core.screen;

import com.artemis.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.esotericsoftware.spine.Skeleton;
import com.team.omega.core.Constants;
import com.team.omega.core.GameCore;
import com.team.omega.core.asset.loader.SkeletonLoader;
import com.team.omega.core.entity.WorldManager;
import com.team.omega.core.entity.component.render.CameraComponent;
import com.team.omega.core.entity.component.render.MapRendererComponent;
import com.team.omega.core.entity.system.RenderSystem;
import com.team.omega.core.renderer.CameraRenderer;
import com.team.omega.core.renderer.IsometricMapRenderer;
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
	/*rendererMgr = new RendererManager();
	
	rendererMgr.setCamRenderer(new CameraRenderer(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
	rendererMgr.setMapRenderer(new IsometricMapRenderer(map, 1f));
	rendererMgr.setPlayerRenderer(new PlayerRenderer());*/
	
	worldMgr = new WorldManager("mainWorld");
	worldMgr.setup();
	worldMgr.start();
	
	OrthographicCamera _cam = new OrthographicCamera();
	_cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	
	Entity _camRenderer = worldMgr.createEntity();
	_camRenderer.addComponent(new CameraComponent(_cam));
	_camRenderer.addToWorld();
	
	Entity _mapRenderer = worldMgr.createEntity();
	_mapRenderer.addComponent(new MapRendererComponent(new IsometricMapRenderer(map)));
	_mapRenderer.addToWorld();
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
	worldMgr.getSystem(RenderSystem.class).process();
    }
    
}
