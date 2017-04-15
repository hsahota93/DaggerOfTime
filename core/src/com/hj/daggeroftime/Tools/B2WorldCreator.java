package com.hj.daggeroftime.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Screens.PlayScreen;
import com.hj.daggeroftime.Sprites.Coin;
import com.hj.daggeroftime.Sprites.Dragon;
import com.hj.daggeroftime.Sprites.Ground;
import com.hj.daggeroftime.Sprites.Water_And_Acid;

/**
 * Created by Harman on 2/26/17.
 */

public class B2WorldCreator {

    private Array<Dragon> dragon;

    public B2WorldCreator(PlayScreen screen, String level) {

        World world = screen.getWorld();
        TiledMap map = screen.getMap();

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

            //Array to hold the dragon
            dragon = new Array<Dragon>();
            //Getting the position of all the dragons from the sprites
            for (MapObject object : map.getLayers().get(13).getObjects().getByType(RectangleMapObject.class)) {

                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                dragon.add(new Dragon(screen,rectangle.getX()/ DaggerOfTime.PPM, rectangle.getY()/DaggerOfTime.PPM, world));
            }

            //If level1 is selected
        } else {

            //For ground, gets all the objects in layer "4"
            for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                //Creates all the rectangle objects in layer "4"
                new Ground(world, map, rectangle);
            } //End for

            //For water and acid, gets all the objects in layer "5"
            for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                //Creates all the rectangle objects in layer "7"
                new Water_And_Acid(world, map, rectangle);
            } //End for

            //For Coins, gets all the objects in layer "7"
            for (MapObject object : map.getLayers().get(7).getObjects().getByType(EllipseMapObject.class)) {

                Ellipse ellipse = ((EllipseMapObject) object).getEllipse();

                //Creates all the circle objects in layer "9"
                new Coin(world, map, ellipse);
            } //End for

            //Array to hold the dragons
            dragon = new Array<Dragon>();
            // Getting position of all the dragons from the sprite
            for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                dragon.add(new Dragon(screen,rectangle.getX()/ DaggerOfTime.PPM, rectangle.getY()/DaggerOfTime.PPM, world));
            }
        }  //End if-else
    } //End constructor

    //Getter for dragon array
    public Array<Dragon> getDragon() {
        return dragon;
    }

} //End B2WorldCreator
