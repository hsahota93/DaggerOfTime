package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Screens.PlayScreen;

/**
 * Created by Harman on 4/18/17.
 */

public class Door extends InteractiveTileObject {

    protected World world;
    protected Body body;

    public Door(PlayScreen screen, Rectangle bounds, boolean sensor) {

        super(screen, bounds, sensor);
        fixture.setUserData(this);
        setCategoryFilter(DaggerOfTime.DOOR_BIT);
    }

    @Override
    public void onCollision() {

    }
}