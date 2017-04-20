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
import com.hj.daggeroftime.Sprites.Door;
import com.hj.daggeroftime.Sprites.Dragon;
import com.hj.daggeroftime.Sprites.Ground;
import com.hj.daggeroftime.Sprites.Hazard;
import com.hj.daggeroftime.Sprites.Key;

/**
 * Created by Harman on 2/26/17.
 */

public class B2WorldCreator {

    private Array<Dragon> dragon;

    public B2WorldCreator(PlayScreen screen) {

        World world = screen.getWorld();
        TiledMap map = screen.getMap();

        //For 'Door'. Creates the door on the level
        for (MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            new Door(screen, rectangle, true);
        } //End for

        //For 'Key'. Creates the key on the level
        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            new Key(screen, rectangle);
        } //End for

        //For ground, gets all the objects in layer "4"
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            //Creates all the rectangle objects in layer "4"
            new Ground(screen, rectangle);
        } //End for

        //For hazards, gets all the objects in layer "5"
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            //Creates all the rectangle objects in layer "7"
            new Hazard(screen, rectangle);
        } //End for

        //For Coins, gets all the objects in layer "7"
        for (MapObject object : map.getLayers().get(7).getObjects().getByType(EllipseMapObject.class)) {

            Ellipse ellipse = ((EllipseMapObject) object).getEllipse();

            //Creates all the circle objects in layer "7"
            new Coin(screen, ellipse);
        } //End for

        //Array to hold the dragons
        dragon = new Array<Dragon>();

        //Getting position of all the dragons from the sprite
        for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            dragon.add(new Dragon(screen, rectangle.getX() / DaggerOfTime.PPM,
                    rectangle.getY() / DaggerOfTime.PPM, world));
        }
    } //End constructor

    //Getter for dragon array
    public Array<Dragon> getDragon() {
        return dragon;
    }

} //End B2WorldCreator
