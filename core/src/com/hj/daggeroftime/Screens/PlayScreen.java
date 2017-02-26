package com.hj.daggeroftime.Screens;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Scenes.Hud;
import com.sun.org.apache.bcel.internal.Constants;
import com.sun.org.apache.bcel.internal.generic.FLOAD;


/**
 * Created by jacob on 2/22/2017.
 */
public class PlayScreen implements Screen {

    private DaggerOfTime game;
    private OrthographicCamera gameCamera;
    private Hud hud;
    private  Viewport gamePort;
    private String level;
    private TmxMapLoader mapLoader; // load map to game
    private TiledMap map; // map itself
    private OrthogonalTiledMapRenderer renderer; // render map into the screen

    //Box2d varibles
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;  // represent the body and fixtures of box 2d world
    /*
     @param game : passing game class
     @param level: passing level
     */
    public PlayScreen(DaggerOfTime game, String level){
        this.game = game;
        this.level = level;
        gameCamera = new OrthographicCamera();
        gamePort = new FillViewport(DaggerOfTime.screenWidth, DaggerOfTime.screenHeight, gameCamera); //width, height, gameCamera
        hud = new Hud(game.batch); // calling the Hud class to display scores and timer
        mapLoader = new TmxMapLoader();

        System.out.println(""+ level);
        map = mapLoader.load("Levels\\" +level.toString());

        renderer = new OrthogonalTiledMapRenderer(map);
        gameCamera.position.set(gamePort.getWorldWidth()/4, gamePort.getWorldHeight()/4, 0);

        /*@param1 vector2: for gravity,
          @param2 true: sleep object at the rest */
        world = new World(new Vector2(0,0), true);
        box2DDebugRenderer = new Box2DDebugRenderer();

        BodyDef bodyDef = new BodyDef(); // defining body
       PolygonShape polygonShape = new PolygonShape(); //polygon shape for the fixture
        FixtureDef fixtureDef = new FixtureDef(); //define fiture before add to the body
        Body body;
        CircleShape circleShape = new CircleShape();

        for(MapObject object : map.getLayers().get(10).getObjects().getByType(EllipseMapObject.class)) {

            Ellipse ellipse = ((EllipseMapObject) object).getEllipse();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(ellipse.x + ellipse.width/2  , ellipse.y + ellipse.height/2);

            body = world.createBody(bodyDef);

            circleShape.setRadius(ellipse.width/2);
            fixtureDef.shape = circleShape;
            body.createFixture(fixtureDef);
        }

        for(MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set(rectangle.getX() + rectangle.getWidth()/2 , rectangle.getY() + rectangle.getHeight()/2);

                body = world.createBody(bodyDef);

                polygonShape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
                fixtureDef.shape = polygonShape;
                body.createFixture(fixtureDef);
            }

        for(MapObject object : map.getLayers().get(12).getObjects().getByType(PolylineMapObject.class)) {
            Shape shape = createPolyLine((PolylineMapObject) object);

            bodyDef.type = BodyDef.BodyType.StaticBody;

            body = world.createBody(bodyDef);

            body.createFixture(shape, 1.0f);
          //  shape.dispose();
        }




    }

    public static ChainShape createPolyLine(PolylineMapObject polyline){
        // getting the vertices of the polyline objects
        float[] vertices = polyline.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length/2];
        for(int i = 0; i < worldVertices.length; i++){
            worldVertices[i] = new Vector2(vertices[i*2] /2 , vertices[i *2  + 1]/2);
            System.out.println("x : " + worldVertices[i]);

        }
        ChainShape cs = new ChainShape();
        cs.createChain(worldVertices);
        return cs;
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
