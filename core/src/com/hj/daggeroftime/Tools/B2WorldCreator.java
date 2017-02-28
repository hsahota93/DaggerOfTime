package com.hj.daggeroftime.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Sprites.Ground;

/**
 * Created by Harman on 2/26/17.
 */

public class B2WorldCreator {

    public B2WorldCreator(World world, TiledMap map, String level) {

        Body body;
        CircleShape circleShape = new CircleShape();

        if(level.compareTo("Levels/level2.tmx") == 0) {

            //For ground
            for (MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                new Ground(world, map, rectangle);

            }
        } else {            //If level1 is selected

            //For the ground
            for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                new Ground(world, map, rectangle);
            } //End for
        }  //End if-else
    } //End constructor
}
