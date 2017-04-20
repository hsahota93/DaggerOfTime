package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Screens.PlayScreen;


/**
 * Created by Harman on 2/27/17.
 */

public abstract class InteractiveTileObject {

    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected Ellipse ellipse;
    protected Body body;
    protected Fixture fixture;

    //Creates any rectangle TileObject in the world (i.e Ground)
    public InteractiveTileObject(PlayScreen screen, Rectangle bounds, boolean sensor) {

        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = bounds;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / DaggerOfTime.PPM,
                (bounds.getY() + bounds.getHeight() / 2) / DaggerOfTime.PPM);

        body = world.createBody(bodyDef);

        polygonShape.setAsBox((bounds.getWidth() / 2) / DaggerOfTime.PPM,
                (bounds.getHeight() / 2) / DaggerOfTime.PPM);
        fixtureDef.shape = polygonShape;
        fixtureDef.isSensor = sensor;
        fixture = body.createFixture(fixtureDef);
    }

    public InteractiveTileObject(PlayScreen screen, Ellipse ellipse, boolean sensor) {

        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.ellipse = ellipse;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((ellipse.x + ellipse.width / 2) / DaggerOfTime.PPM,
                (ellipse.y + ellipse.height / 2) / DaggerOfTime.PPM);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        //Telling the world to create the body
        body = world.createBody(bodyDef);

        //Defining the shape and radius of the body
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / DaggerOfTime.PPM);

        //Creates the fixture
        fixtureDef.shape = shape;
        fixtureDef.isSensor = sensor;
        body.createFixture(fixtureDef);
        fixture = body.createFixture(fixtureDef);
    }

    public abstract void onCollision();

    public void setCategoryFilter(short filterBit) {

        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(int graphicLayer) {

        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(graphicLayer);

        return layer.getCell((int) (body.getPosition().x * DaggerOfTime.PPM / 16),
                (int) (body.getPosition().y * DaggerOfTime.PPM / 16));
    }
}
