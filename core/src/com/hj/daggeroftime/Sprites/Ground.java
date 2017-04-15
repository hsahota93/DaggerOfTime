package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.hj.daggeroftime.DaggerOfTime;

/**
 * Created by Harman on 2/27/17.
 */

public class Ground extends InteractiveTileObject {

    public Ground(World world, TiledMap map, Rectangle bounds) {

        super(world, map, bounds, false);
        fixture.setUserData(this);
        setCategoryFilter(DaggerOfTime.OBJECT_BIT);
    }

    @Override
    public void onCollision() {

        //Gdx.app.log("Ground", "Collision");
    }
}
