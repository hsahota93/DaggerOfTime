package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Screens.PlayScreen;

/**
 * Created by jacob on 4/3/2017.
 */
public class FireBreath extends Sprite {


    public World world;
    public Body b2body;
    protected float polygonSize = 5 / DaggerOfTime.PPM;
    protected float positionX ;
    protected float positionY ;
    public Animation fireAnimation;
    public Array<TextureRegion> frames;
    private float stateTIme;
    public  float elapsedTime;
    PlayScreen screen;
    BodyDef bodyDef;

    //@param world: take the world that enemy generated in
    public FireBreath(World world, PlayScreen screen, float positionX, float positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.world = world;
        this.screen = screen;
        defineStaticEnemy();

        frames = new Array<TextureRegion>();
        for(int i = 0; i < 2; i++)
            frames.add(new TextureRegion(screen.getFireAtlas().findRegion("attachment"), i*60, 0, 60,60));
        fireAnimation = new Animation(0.4f,frames);
        stateTIme = 0;
        setBounds(0, 40, 14 / DaggerOfTime.PPM, 42 / DaggerOfTime.PPM);
    }

    public void defineStaticEnemy() {

        bodyDef = new BodyDef();
        bodyDef.position.set(positionX, positionY);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(polygonSize, polygonSize);

        fixtureDef.filter.categoryBits = DaggerOfTime.FIRE_BREATH;
        fixtureDef.filter.maskBits = DaggerOfTime.OBJECT_BIT |
                DaggerOfTime.PRINCE_BIT;

        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef).setUserData(this);
        b2body.setGravityScale(0);
    }

    public void update(float dt) {
        stateTIme+=dt;

        elapsedTime += dt;

        if(elapsedTime >0.5) {
            b2body.applyLinearImpulse(new Vector2(-0.1f, 0), b2body.getWorldCenter(), true);
            elapsedTime = 0;
        }

        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y-getHeight()/2);
        setRegion((TextureRegion) fireAnimation.getKeyFrame(stateTIme, true));
    }

    public void onCollision() {

        //Gdx.app.log("Clearing", "frames");
    }
}
