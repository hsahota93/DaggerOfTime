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
import com.hj.daggeroftime.Scenes.Hud;

/**
 * Created by Harman on 3/12/17.
 */

public class Coin extends InteractiveTileObject {

    protected World world;
    protected TiledMap map;
    protected Body body;
    protected Ellipse bounds;

    public Coin(World world, TiledMap map, Ellipse ellipse) {

        super(world, map, ellipse);
        fixture.setUserData(this);
        setCategoryFilter(DaggerOfTime.COIN_BIT);
    }

    @Override
    public void onCollision() {

     //   Gdx.app.log("Coin", "Collision");
        setCategoryFilter(DaggerOfTime.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(10);
        Hud.setScoreLabel();
    }
}
