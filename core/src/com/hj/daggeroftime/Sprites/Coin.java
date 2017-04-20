package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Scenes.Hud;
import com.hj.daggeroftime.Screens.PlayScreen;

/**
 * Created by Harman on 3/12/17.
 */

public class Coin extends InteractiveTileObject {

    protected World world;
    protected TiledMap map;
    protected Body body;
    protected Ellipse bounds;

    private final int coinGraphicLayer = 3;

    public Coin(PlayScreen screen, Ellipse ellipse) {

        super(screen, ellipse, true);
        fixture.setUserData(this);
        setCategoryFilter(DaggerOfTime.COIN_BIT);
    }

    @Override
    public void onCollision() {

        //Gdx.app.log("Coin", "Collision");
        setCategoryFilter(DaggerOfTime.DESTROYED_BIT);
        getCell(coinGraphicLayer).setTile(null);
        Hud.addScore(10);
        Hud.setScoreLabel();
    }
}