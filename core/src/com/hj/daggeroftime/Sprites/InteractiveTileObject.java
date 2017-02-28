package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.hj.daggeroftime.DaggerOfTime;

/**
 * Created by Harman on 2/27/17.
 */

public abstract class InteractiveTileObject {

    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds) {

        this.world = world;
        this.map = map;
        this.bounds = bounds;

        //Stupid GitHub

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
        body.createFixture(fixtureDef);
    }
}