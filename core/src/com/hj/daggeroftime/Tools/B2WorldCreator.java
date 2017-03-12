package com.hj.daggeroftime.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.hj.daggeroftime.Sprites.Coin;
import com.hj.daggeroftime.Sprites.Ground;

/**
 * Created by Harman on 2/26/17.
 */

public class B2WorldCreator {

    public B2WorldCreator(World world, TiledMap map, String level) {

        if(level.compareTo("Levels/level2.tmx") == 0) {

            //For ground, gets all the objects in layer "11"
            for (MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                //Creates all the rectangle objects in layer "11"
                new Ground(world, map, rectangle);

            }

            //For Coins, gets all the objects in layer "9"
            for (MapObject object : map.getLayers().get(10).getObjects().getByType(EllipseMapObject.class)) {

                Ellipse ellipse = ((EllipseMapObject) object).getEllipse();

                //Creates all the circle objects in layer "9"
                new Coin(world, map, ellipse);
            } //End for

            //If level1 is selected
        } else {

            //For ground, gets all the objects in layer "4"
            for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                //Creates all the rectangle objects in layer "4"
                new Ground(world, map, rectangle);
            } //End for

            //For Coins, gets all the objects in layer "9"
            for (MapObject object : map.getLayers().get(9).getObjects().getByType(EllipseMapObject.class)) {

                Ellipse ellipse = ((EllipseMapObject) object).getEllipse();

                //Creates all the circle objects in layer "9"
                new Coin(world, map, ellipse);
            } //End for
        }  //End if-else
    } //End constructor
} //End B2WorldCreator
