package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.hj.daggeroftime.DaggerOfTime;

/**
 * Created by Harman on 3/12/17.
 */

public class Coin extends InteractiveTileObject {

    protected World world;
    protected TiledMap map;
    protected Body body;
    protected Ellipse bounds;

    public Coin(World world, TiledMap map, Ellipse bounds) {

        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((bounds.x + bounds.width / 2) / DaggerOfTime.PPM, (bounds.y + bounds.height / 2) / DaggerOfTime.PPM);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        //Telling the world to create the body
        body = world.createBody(bodyDef);

        //Defining the shape and radius of the body
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / DaggerOfTime.PPM);

        //Creates the fixture
        fixtureDef.shape = shape;
        //fixtureDef.isSensor = true;
        body.createFixture(fixtureDef);
    }

    @Override
    public void onCollision() {

        Gdx.app.log("Coin:", "Collision");
    }
}
