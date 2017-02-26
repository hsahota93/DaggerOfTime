package com.hj.daggeroftime.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Scenes.Hud;
import com.hj.daggeroftime.Sprites.Prince;

/**
 * Created by jacob on 2/22/2017.
 */
public class PlayScreen implements Screen {

    private DaggerOfTime game;
    private OrthographicCamera gameCamera;
    private Hud hud;
    private Viewport gamePort;
    private TmxMapLoader mapLoader;                     // load map to game
    private TiledMap map;                               // map itself
    private OrthogonalTiledMapRenderer renderer;        // render map into the screen
    private Prince player;

    private int maxPlayerSpeed = 2;

    //Box2d varibles
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;  // represent the body and fixtures of box 2d world
    /*
     @param game : passing game class
     @param level: passing level
     */
    public PlayScreen(DaggerOfTime game, String level) {

        this.game = game;
        gameCamera = new OrthographicCamera();
        gamePort = new FitViewport(DaggerOfTime.screenWidth / DaggerOfTime.PPM,
                DaggerOfTime.screenHeight / DaggerOfTime.PPM, gameCamera); //width, height, gameCamera
        hud = new Hud(game.batch); // calling the Hud class to display scores and timer
        mapLoader = new TmxMapLoader();

        map = mapLoader.load(level);

        renderer = new OrthogonalTiledMapRenderer(map, 1 / DaggerOfTime.PPM);
        gameCamera.position.set(gamePort.getWorldWidth()/4, gamePort.getWorldHeight()/4, 0);

        /*@param1 vector2: for gravity,
          @param2 true: sleep object at the rest */
        world = new World(new Vector2(0, -10), true);
        box2DDebugRenderer = new Box2DDebugRenderer();

        player = new Prince(world);

        //Stuff to make the ground, water, key, etc...
        BodyDef bodyDef = new BodyDef();                        //defining body
        PolygonShape polygonShape = new PolygonShape();         //polygon shape for the fixture
        FixtureDef fixtureDef = new FixtureDef();               //define fixture before add to the body

        Body body;
        CircleShape circleShape = new CircleShape();

        if(level.compareTo("Levels/level2.tmx") == 0) {

/*
            //For coin
            for (MapObject object : map.getLayers().get(10).getObjects().getByType(EllipseMapObject.class)) {

                Ellipse ellipse = ((EllipseMapObject) object).getEllipse();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((ellipse.x + ellipse.width / 2) / DaggerOfTime.PPM,
                        (ellipse.y + ellipse.height / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                circleShape.setRadius((ellipse.width / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = circleShape;
                body.createFixture(fixtureDef);
            }
*/
            //For ground
            for (MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getY() + rectangle.getHeight() / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                polygonShape.setAsBox((rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getHeight() / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = polygonShape;
                body.createFixture(fixtureDef);

            }
        } else {            //If level1 is selected

    /*
            //For coins
            for (MapObject object : map.getLayers().get(9).getObjects().getByType(EllipseMapObject.class)) {

                Ellipse ellipse = ((EllipseMapObject) object).getEllipse();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((ellipse.x + ellipse.width / 2) / DaggerOfTime.PPM,
                        (ellipse.y + ellipse.height / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                circleShape.setRadius((ellipse.width / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = circleShape;
                body.createFixture(fixtureDef);
            }
    */

            //For the ground
            for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getY() + rectangle.getHeight() / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                polygonShape.setAsBox((rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getHeight() / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = polygonShape;
                body.createFixture(fixtureDef);
            }

/*
            //For the Water/Acid
            for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getY() + rectangle.getHeight() / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                polygonShape.setAsBox((rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getHeight() / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = polygonShape;
                body.createFixture(fixtureDef);
            }

            //For the spikes
            for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getY() + rectangle.getHeight() / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                polygonShape.setAsBox((rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getHeight() / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = polygonShape;
                body.createFixture(fixtureDef);
            }


            //For the fire
            for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getY() + rectangle.getHeight() / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                polygonShape.setAsBox((rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getHeight() / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = polygonShape;
                body.createFixture(fixtureDef);

            }

            //For the key
            for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getY() + rectangle.getHeight() / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                polygonShape.setAsBox((rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getHeight() / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = polygonShape;
                body.createFixture(fixtureDef);
            }
*/
        }  //End if-else
    }  //End constructor

    @Override
    public void show() {

    }

    /*To handle interactions*/
    public void handleInput(float dt) {

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= maxPlayerSpeed)
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -maxPlayerSpeed)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
    }

    public void update(float dt) {

        handleInput(dt);

        world.step(1/60f, 6, 2);
        gameCamera.position.x = player.b2body.getPosition().x;

        gameCamera.update();
        renderer.setView(gameCamera);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render(); // rendering the map
        box2DDebugRenderer.render(world, gameCamera.combined);

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
