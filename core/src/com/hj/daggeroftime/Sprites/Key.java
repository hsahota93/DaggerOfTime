package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Screens.PlayScreen;

/**
 * Created by Harman on 4/17/17.
 */

public class Key extends InteractiveTileObject {

    protected World world;
    protected TiledMap map;
    protected Body body;
    protected Ellipse bounds;

    private final int keyGraphicLayer = 2;

    public Key(PlayScreen screen, Rectangle bounds) {

        super(screen, bounds, true);
        fixture.setUserData(this);
        setCategoryFilter(DaggerOfTime.KEY_BIT);
    }

    @Override
    public void onCollision() {
        
        setCategoryFilter(DaggerOfTime.DESTROYED_BIT);
        getCell(keyGraphicLayer).setTile(null);
    }
}
