package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Screens.PlayScreen;

/**
 * Created by Harman on 2/26/17.
 */

public class Prince extends Sprite {

    public enum State {FALLING, JUMPING, STANDING, RUNNING}

    public static State currentState;
    public State prevState;
    public World world;
    public Body b2body;
    private TextureRegion princeStand;
    private Animation princeRun;
    private Animation princeJump;
    private boolean runningRight;
    private float stateTimer;

    //Constructor
    public Prince(World world, PlayScreen screen) {

        super(screen.getAtlas().findRegion("runningPrince"));
        this.world = world;
        currentState = State.STANDING;
        prevState = State.STANDING;
        stateTimer = 0;
        runningRight = true;
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 1; i < 13; i++) {

            frames.add(new TextureRegion(getTexture(), i * 40, 0, 28, 60));
        }

        princeRun = new Animation(0.1f, frames);
        frames.clear();

        definePrince();
        princeStand = new TextureRegion(getTexture(), 30, 15, 28, 60);
        setBounds(0, 40, 16 / DaggerOfTime.PPM, 41 / DaggerOfTime.PPM);
        setRegion(princeStand);
    }

    public void update(float dt) {

        //Putting the center of the sprite in the center of the circle
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {

        currentState = getState();


        TextureRegion region;
        switch (currentState) {

            case JUMPING:
                region = princeStand; //(TextureRegion) princeJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = princeStand; //(TextureRegion) princeRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = princeStand;
                break;
        }

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {

            region.flip(true, false);
            runningRight = false;
        } else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {

            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == prevState ? stateTimer + dt : 0;
        prevState = currentState;

        return region;
    }

    public State getState() {

        if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y > 0 && prevState == State.JUMPING)) {

            return State.JUMPING;
        } else if(b2body.getLinearVelocity().y < 0) {

            return State.FALLING;
        } else if(b2body.getLinearVelocity().x != 0) {

            return State.RUNNING;
        } else {

            return State.STANDING;
        }
    }

    //Creating the prince
    public void definePrince() {

        //Defining the body. Setting position and the type of body
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / DaggerOfTime.PPM, 64 / DaggerOfTime.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        //Telling the world to create the body
        b2body = world.createBody(bodyDef);

        //Defining the shape and radius of the body
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / DaggerOfTime.PPM);
        fixtureDef.filter.categoryBits = DaggerOfTime.PRINCE_BIT;
        fixtureDef.filter.maskBits = DaggerOfTime.DEFAULT_BIT | DaggerOfTime.COIN_BIT;

        //Creates the fixture
        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef).setUserData("prince");
    }
}
