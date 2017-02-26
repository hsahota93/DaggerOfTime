package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.hj.daggeroftime.DaggerOfTime;

/**
 * Created by Harman on 2/26/17.
 */

public class Prince extends Sprite {

    public World world;
    public Body b2body;

    //Constructor
    public Prince(World world) {

        this.world = world;
        definePrince();
    }

    //Creating the prince
    public void definePrince() {

        //Defining the body. Setting position and the type of body
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / DaggerOfTime.PPM, 64 / DaggerOfTime.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        //Telling the world to create the body
        b2body = world.createBody(bodyDef);


        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / DaggerOfTime.PPM);

        //Creates the fixture
        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef);

    }
}
