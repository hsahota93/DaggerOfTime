package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.hj.daggeroftime.DaggerOfTime;

/**
 * Created by Harman on 3/30/17.
 */

public class Hazard extends InteractiveTileObject {

    public Hazard(World world, TiledMap map, Rectangle bounds) {

        super(world, map, bounds, true);
        fixture.setUserData(this);

        setCategoryFilter(DaggerOfTime.HAZARD_BIT);
    }

    @Override
    public void onCollision() {

        Gdx.app.log("Water/Acid", "Collision");
    }
}
