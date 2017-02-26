package com.hj.daggeroftime.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Scenes.Hud;

/**
 * Created by jacob on 2/22/2017.
 */
public class PlayScreen implements Screen {

    private DaggerOfTime game;
    private OrthographicCamera gameCamera;
    private Hud hud;
    private  Viewport gamePort;
    private TmxMapLoader mapLoader; // load map to game
    private TiledMap map; // map itself
    private OrthogonalTiledMapRenderer renderer; // render map into the screen
    /*
     @param game : passing game class
      */
    public PlayScreen(DaggerOfTime game, String level){
        this.game = game;
        gameCamera = new OrthographicCamera();
        gamePort = new FillViewport(DaggerOfTime.screenWidth, DaggerOfTime.screenHeight, gameCamera); //width, height, gameCamera
        hud = new Hud(game.batch); // calling the Hud class to display scores and timer
        mapLoader = new TmxMapLoader();

        map = mapLoader.load(level);

        renderer = new OrthogonalTiledMapRenderer(map);
        gameCamera.position.set(gamePort.getWorldWidth()/4, gamePort.getWorldHeight()/4, 0);
    }

    @Override
    public void show() {

    }
    /*To handle interactions*/
    public void handleInput(float dt){
        if(Gdx.input.isTouched()){
            gameCamera.position.x +=100*dt;
        }
    }

    public void update(float dt){
        handleInput(dt);
        gameCamera.update();
        renderer.setView(gameCamera);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render(); // rendering the map
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }


    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
