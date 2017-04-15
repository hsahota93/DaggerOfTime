package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.hj.daggeroftime.Screens.PlayScreen;

/**
 * Created by jacob on 4/9/2017.
 */
public abstract class Enemy extends Sprite {
    protected  World world;
    protected Screen screen;
    public Body b2body;
    public static Array<FireBreath> fireBreathsArray;
    public Enemy(PlayScreen screen, float x, float y, World world){
        this.world = world;
        this.screen = screen;
        setPosition(x,y);
        defineEnemy();
    }


    protected abstract void defineEnemy();
    public abstract void update(float dt);
    public abstract Array<FireBreath> getFireList2();

}