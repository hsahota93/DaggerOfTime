package com.hj.daggeroftime.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Screens.PlayScreen;

/**
 * Created by Harman on 2/27/17.
 */

public class Ground extends InteractiveTileObject {

    public Ground(PlayScreen screen, Rectangle bounds) {

        super(screen, bounds, false);
        fixture.setUserData(this);
        setCategoryFilter(DaggerOfTime.OBJECT_BIT);
    }

    @Override
    public void onCollision() {

        //Gdx.app.log("Ground", "Collision");
    }
}
