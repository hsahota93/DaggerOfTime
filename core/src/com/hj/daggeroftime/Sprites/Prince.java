package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
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

    public enum State {FALLING, JUMPING, STANDING, RUNNING, DEAD}

    public static State currentState;
    public State prevState;
    public World world;
    public Body b2body;
    private TextureRegion princeStand;
    private TextureRegion princeDead;
    private Animation princeRun;
    private Animation princeJump;
    private boolean runningRight;
    private float stateTimer;
    private TextureRegion region;
    private int health = 100;
    private PlayScreen screen;

    private boolean princeIsDead;
    private boolean hasKey;
    private boolean reachedDoor;

    //Constructor
    public Prince(PlayScreen screen) {

        super(screen.getAtlas().findRegion("RunningPrince2"));
        this.screen = screen;
        this.world = screen.getWorld();
        currentState = State.STANDING;
        prevState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int j = 1; j < 7; j++) {

            frames.add(new TextureRegion(getTexture(), j * 70, 0, 70, 56));
        }

        princeRun = new Animation(0.1f, frames);
        princeDead = new TextureRegion(getTexture(), 0, 0, 0, 0);
        frames.clear();

        definePrince();

        princeStand = new TextureRegion(getTexture(), 0, 0, 70, 56);
        setBounds(getX(), getY(), 70 / DaggerOfTime.PPM, 80 / DaggerOfTime.PPM);
        setRegion(princeStand);
    }

    public void update(float dt) {

        if(currentState != State.DEAD) {

            //Putting the center of the sprite in the center of the circle
            setPosition(b2body.getPosition().x - getWidth() / 2,
                    b2body.getPosition().y - getHeight() / 2);

            setRegion(getFrame(dt));
        }
    }

    public TextureRegion getFrame(float dt) {

        currentState = getState();

        switch (currentState) {

            case JUMPING:
                region = princeStand; //(TextureRegion) princeJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = (TextureRegion) princeRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = princeStand;
                break;
        }

        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {

            region.flip(true, false);
            runningRight = false;
        } else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {

            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == prevState ? stateTimer + dt : 0;
        prevState = currentState;

        return region;
    }

    public float getStateTimer() {
        return stateTimer;
    }

    public State getState() {

        if ((b2body.getLinearVelocity().y > 0 && currentState != State.DEAD) || (b2body.getLinearVelocity().y > 0 && prevState == State.JUMPING)) {

            return State.JUMPING;
        } else if (b2body.getLinearVelocity().y < 0 && currentState != State.DEAD) {

            return State.FALLING;
        } else if (b2body.getLinearVelocity().x != 0 && currentState != State.DEAD) {

            return State.RUNNING;
        } else if (currentState == State.DEAD) {

            return State.DEAD;
        } else {

            return State.STANDING;
        }
    }

    //Creating the prince
    public void definePrince() {

        Vector2 spawnPosition = new Vector2(32 / DaggerOfTime.PPM, 120 / DaggerOfTime.PPM);

        //Defining the body. Setting position and the type of body
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(spawnPosition.add(0, 10 / DaggerOfTime.PPM));
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        //Telling the world to create the body
        b2body = world.createBody(bodyDef);

        //Defining the shape and radius of the body
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / DaggerOfTime.PPM);
        fixtureDef.friction = 0.3f;
        fixtureDef.filter.categoryBits = DaggerOfTime.PRINCE_BIT;
        fixtureDef.filter.maskBits = DaggerOfTime.OBJECT_BIT |
                DaggerOfTime.COIN_BIT |
                DaggerOfTime.HAZARD_BIT |
                DaggerOfTime.ENEMY_BIT |
                DaggerOfTime.FIRE_BREATH_BIT |
                DaggerOfTime.BOUND_BIT |
                DaggerOfTime.KEY_BIT |
                DaggerOfTime.DOOR_BIT;

        //Creates the fixture
        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef).setUserData(this);

        shape.setPosition(new Vector2(0, -14 / DaggerOfTime.PPM));
        b2body.createFixture(fixtureDef).setUserData(this);

        shape.setPosition(new Vector2(0, -28 / DaggerOfTime.PPM));
        b2body.createFixture(fixtureDef).setUserData(this);
    }

    //When the prince gets hit
    public void hit(int damage) {

        DaggerOfTime.assetManager.get("Audio/Sounds/Damage.mp3", Sound.class).play();
        health -= damage;

        //If health is 0 or below, set state to dead and remove all sprites/animation.
        //Also stop music
        if (health < 1) {

            princeIsDead = true;
            currentState = State.DEAD;
            region.setRegion(princeDead);
            screen.clearPrince();
            DaggerOfTime.assetManager.get("Audio/Music/LevelOneMusic.mp3", Music.class).stop();
        }
    }

    //Called when the prince collides with a key
    public void obtainKey() {
        hasKey = true;
    }

    //Called when the prince collides with the door and already has the key
    public void setReachedDoor() {

        if (hasKey) {

            reachedDoor = true;
        }
    }

    //Returns true if the prince has collided with the door
    public boolean getReachedDoor() {
        return reachedDoor;
    }

    public int getHealth() {
        return health;
    }
}
