package com.hj.daggeroftime.Screens;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Scenes.Hud;
import com.hj.daggeroftime.Sprites.Enemy;
import com.hj.daggeroftime.Sprites.Prince;

import com.hj.daggeroftime.Tools.B2WorldCreator;
import com.hj.daggeroftime.Tools.WorldContactListener;

/**
 * Created by jacob on 2/22/2017.
 */

//TODO add empty constructor for testing

public class PlayScreen implements Screen {

    private boolean destroyPrince;

    //Reference to our Game, used to set Screens
    private DaggerOfTime game;
    private TextureAtlas atlas;

    private TextureAtlas fireBreathAtlas; // dragon atlas
    private TextureAtlas  myDragonAtlas; // fire breath atlas
    private B2WorldCreator creator; //instance of B2WorldCreator

    //Basic playscreen variables
    private OrthographicCamera gameCamera;
    private Hud hud;
    private Viewport gamePort;

    //Tiled map variables
    private TmxMapLoader mapLoader;                     // load map to game
    private TiledMap map;                               // map itself
    private OrthogonalTiledMapRenderer renderer;        // render map into the screen

    //The body that will be controlled by the player (the main actor)
    private Prince player;

    //Max speed 'player' can travel in x-axis
    private int maxPlayerSpeed = 2;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;  // represent the body and fixtures of box 2d world

    private Music music;
    private String level;

    public PlayScreen(DaggerOfTime obj) {
        setSplashScreen(obj);
    }

    //@param game: passing game class @param level: passing level
    public PlayScreen(DaggerOfTime game, String level) {

        atlas = new TextureAtlas("RunningPrince2.pack");
        fireBreathAtlas = new TextureAtlas("fire.pack");
        myDragonAtlas = new TextureAtlas(Gdx.files.internal("test.pack"));

        this.game = game;
        this.level = level;
        gameCamera = new OrthographicCamera();
        gamePort = new FitViewport(DaggerOfTime.screenWidth / DaggerOfTime.PPM,
                DaggerOfTime.screenHeight / DaggerOfTime.PPM, gameCamera); //width, height, gameCamera
        hud = new Hud(game.batch, level); // calling the Hud class to display scores and timer
        mapLoader = new TmxMapLoader();

        map = mapLoader.load(level);

        renderer = new OrthogonalTiledMapRenderer(map, 1 / DaggerOfTime.PPM);
        gameCamera.position.set(gamePort.getWorldWidth() / 4, gamePort.getWorldHeight() / 3, 0);

        /*@param1 vector2: for gravity,
          @param2 true: sleep object at the rest */
        world = new World(new Vector2(0, -10), true);
        box2DDebugRenderer = new Box2DDebugRenderer();

        creator = new B2WorldCreator(this);

        player = new Prince(this);

        world.setContactListener(new WorldContactListener());

        music = DaggerOfTime.assetManager.get("Audio/Music/LevelOneMusic.mp3", Music.class);
        music.setLooping(true);
        music.play();
    }  //End constructor

    public TextureAtlas getAtlas() {
        return atlas;
    }

    //Getter got fire breath atlas
    public TextureAtlas getFireAtlas(){
        return fireBreathAtlas;
    }

    //Getter for the dragon atlas
    public TextureAtlas getDragonAtlas(){
        return myDragonAtlas;
    }

    public void clearPrince() {
        destroyPrince = true;
    }

    @Override
    public void show() {

    }

    /*To handle interactions*/
    private void handleInput(float dt) {

        //Only handle input if prince is alive
        if (Prince.currentState != Prince.State.DEAD) {
            //If the 'UP' key is pressed apply linear impulse upwards
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) &&
                    (Prince.currentState != Prince.State.JUMPING && Prince.currentState != Prince.State.FALLING)) {

                player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);
                DaggerOfTime.assetManager.get("Audio/Sounds/Jump.mp3", Sound.class).play();
            }

            //If the 'RIGHT' key is pressed apply linear impulse to the right as long as velocity
            //is slower than 'maxPlayerSpeed'
            if ((Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) &&
                    player.b2body.getLinearVelocity().x <= maxPlayerSpeed) {

                player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
            }

            //If the 'LEFT' key is pressed apply linear impulse to the right as long as velocity
            //is slower than 'maxPlayerSpeed'
            if ((Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) &&
                    player.b2body.getLinearVelocity().x >= -maxPlayerSpeed) {

                player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
            }
        }
    } //End handleInput

    public void update(float dt) {

        gameCamera.position.x = player.b2body.getPosition().x;
        gameCamera.update();

        if (hud.getTimer() == 0) {
            player.hit(100);
        }

        handleInput(dt);

        world.step(1 / 60f, 6, 2);
        player.update(dt);
        hud.updateHealth(player.getHealth());
        hud.update(dt);

        //updating the dragon
        for(Enemy enemy: creator.getDragon()) {
            enemy.update(dt);
        }

        renderer.setView(gameCamera);
    }

    //Getter for the current map
    public TiledMap getMap(){
        return map;
    }

    //Getter for the current world
    public World getWorld(){
        return world;
    }

    @Override
    public void render(float delta) {

        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render(); // rendering the map
        //box2DDebugRenderer.render(world, gameCamera.combined); // debug line

        //Draws the sprite
        game.batch.setProjectionMatrix(gameCamera.combined);
        game.batch.begin();
        player.draw(game.batch);

        //Drawing the dragon and fire
        for(Enemy enemy: creator.getDragon()) {
            enemy.draw(game.batch);

            for(int i =0; i < enemy.getFireList2().size;i++ ) {
                enemy.getFireList2().get(i).draw(game.batch);
            }
        }

        game.batch.end();

        //Draws the hud
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        //If the prince's health is 0 create the game over screen
        if(player.getHealth() <= 0) {

            game.maxLives--;

            if (game.maxLives < 0) {
                Database demo = new Database();
                game.setScreen(new GameOverScreen(game));
                game.maxLives = 2;
            } else {

                game.setScreen(new PlayScreen(game, level));
            }

            dispose();
        }

        //If the player has the key and has reached the door
        if(player.getReachedDoor()) {

            //Stop playing the music
            DaggerOfTime.assetManager.get("Audio/Music/LevelOneMusic.mp3", Music.class).stop();

            //If he is on level 1 set the game to level 2
            if (level.compareTo("Levels/level1.tmx") == 0) {

                game.setScreen(new PlayScreen(game, "Levels/level2.tmx"));
            } else {
                Database demo = new Database();
                //Otherwise show the WinScreen (all levels beaten)
                game.setScreen(new WinScreen(game));
            }

            dispose();
        }
    }

    public void setSplashScreen(DaggerOfTime obj) {
        this.game = obj;
        obj.setScreen(new SplashScreen(obj));
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

        map.dispose();
        renderer.dispose();
        world.dispose();
        box2DDebugRenderer.dispose();
        hud.dispose();
    }
}
