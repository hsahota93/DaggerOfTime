package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Screens.PlayScreen;

/**
 * Created by Harman on 2/26/17.
 */

public class Prince extends Sprite {

    public World world;
    public Body b2body;
    private TextureRegion princeStand;

    //Constructor
    public Prince(World world, PlayScreen screen) {

        super(screen.getAtlas().findRegion("runningPrince"));
        this.world = world;
        definePrince();
        princeStand = new TextureRegion(getTexture(), 30, 15, 28, 60);
        setBounds(0, 40, 20 / DaggerOfTime.PPM, 41 / DaggerOfTime.PPM);
        setRegion(princeStand);
    }

    public void update(float dt) {

        //Putting the center of the sprite in the center of the circle
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
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
