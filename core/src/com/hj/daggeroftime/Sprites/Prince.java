package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
    public int health = 100;
    private PlayScreen screen;
    private boolean princeIsDead;

    //Constructor
    public Prince(World world, PlayScreen screen) {

        this.screen = screen;
        this.world = world;
        currentState = State.STANDING;
        prevState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i < 13; i++) {

            frames.add(new TextureRegion(screen.getAtlas().findRegion("runningPrince"), i * 16, 15, 13, 40));
            //frames.add(new TextureRegion(screen.getAtlas().findRegion("runningPrince"), i * 30, 0, 28, 60));
        }

        princeRun = new Animation(0.1f, frames);
        princeDead = new TextureRegion(screen.getAtlas().findRegion("runningPrince"), 0, 0, 0, 0);
        frames.clear();

        definePrince();

        princeStand = new TextureRegion(screen.getAtlas().findRegion("runningPrince"), 43, 15, 13, 40);
        //princeStand = new TextureRegion(getTexture(), 30, 15, 28, 60);
        setBounds(0, 0, 13 / DaggerOfTime.PPM, 40 / DaggerOfTime.PPM);
        setRegion(princeStand);
    }

    public void update(float dt) {

        if(currentState != State.DEAD) {

            //Putting the center of the sprite in the center of the circle
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

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

    public boolean isDead() {
        return princeIsDead;
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

        //Defining the body. Setting position and the type of body
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / DaggerOfTime.PPM, 64 / DaggerOfTime.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        //Telling the world to create the body
        b2body = world.createBody(bodyDef);

        //Defining the shape and radius of the body
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape lowerBody = new CircleShape();
        lowerBody.setRadius(6 / DaggerOfTime.PPM);
        fixtureDef.friction = 0.5f;
        fixtureDef.filter.categoryBits = DaggerOfTime.PRINCE_BIT;
        fixtureDef.filter.maskBits = DaggerOfTime.OBJECT_BIT |
                DaggerOfTime.COIN_BIT |
                DaggerOfTime.HAZARD_BIT |
                DaggerOfTime.ENEMY_BIT |
                DaggerOfTime.FIRE_BREATH;

        //Creates the fixture
        fixtureDef.shape = lowerBody;
        b2body.createFixture(fixtureDef).setUserData(this);
    }

    //When the prince gets hit
    public void hit(int damage) {

        DaggerOfTime.assetManager.get("Audio/Sounds/Damage.mp3", Sound.class).play();
        health -= damage;
        Gdx.app.log("Damage: " + damage,"Health: " + health);

        //If health is 0 or below, set state to dead and remove all sprites/animation.
        //Also stop music
        if (health < 1) {

            princeIsDead = true;
            currentState = State.DEAD;
            region.setRegion(princeDead);
            screen.clearPrince();
            DaggerOfTime.assetManager.get("Audio/Music/LevelOneMusic.mp3", Music.class).stop();

            Gdx.app.log("Prince is", "dead");
        }
    }
}
