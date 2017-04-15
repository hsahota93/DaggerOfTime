package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Screens.PlayScreen;

/**
 * Created by jacob on 4/9/2017.
 */
public class Dragon extends  Enemy{
    private float stateTime;
    private Animation dragonAnimation;
    private Array<TextureRegion> frames;
    public static Array<FireBreath> fireBreathsArray;
    private FireBreath fireBreath;
    private float xPosition, yPosition;
    private PlayScreen screen;
    private float resetTimer = 0;

    public Dragon(PlayScreen screen, float x, float y, World world) {
        super(screen, x, y, world);
        this.xPosition = x;
        this.yPosition = y;
        this.screen = screen;

        //Array to store FireBreath
        fireBreathsArray = new Array<FireBreath>();
        frames= new Array<TextureRegion>();
        TextureRegion temp;
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 3; j++) {
                temp = new TextureRegion(screen.getDragonAtlas().findRegion("dragon1"), j * 140, i * 90, 140, 90);
                temp.flip(true,false);
                frames.add(temp);
            }

        //Adding frames to the animation
        dragonAnimation = new Animation(0.10f, frames);
        stateTime = 0;

        setBounds(getX(), getY(), 70/DaggerOfTime.PPM, 45/DaggerOfTime.PPM);
    }

    public void update(float dt){
        resetTimer +=dt;

        if(resetTimer > 5) {
            fireBreath = new FireBreath(world, screen, xPosition - (10/DaggerOfTime.PPM), yPosition + (10/DaggerOfTime.PPM));
            fireBreathsArray.add(fireBreath);
            System.out.println("Print: " + fireBreath);

            resetTimer = 0;
        }

        for(int i =0; i < fireBreathsArray.size; i++) {
            (fireBreathsArray.get(i)).update(dt);
        }

        stateTime+=dt;

        setPosition(b2body.getPosition().x - getWidth()/2 , b2body.getPosition().y- getHeight()/2);
        setRegion((TextureRegion) dragonAnimation.getKeyFrame(stateTime, true));
    }

    @Override
    protected void defineEnemy() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        //Telling the world to create the body
        b2body = world.createBody(bodyDef);

        //Defining the shape and radius of the body
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10/DaggerOfTime.PPM);


        fixtureDef.filter.categoryBits = DaggerOfTime.ENEMY_BIT;
        fixtureDef.filter.maskBits = DaggerOfTime.OBJECT_BIT |
                DaggerOfTime.PRINCE_BIT;

        //Creates the fixture
        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef);

    }

    //Getter for fireBreathArray
    public Array<FireBreath> getFireList2(){
        return fireBreathsArray;
    }


}
