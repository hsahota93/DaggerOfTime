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

/**
 * Created by Harman on 2/26/17.
 */

public class B2WorldCreator {

    public B2WorldCreator(World world, TiledMap map, String level) {

        //Stuff to make the ground, water, key, etc...
        BodyDef bodyDef = new BodyDef();                        //defining body
        PolygonShape polygonShape = new PolygonShape();         //polygon shape for the fixture
        FixtureDef fixtureDef = new FixtureDef();               //define fixture before add to the body

        Body body;
        CircleShape circleShape = new CircleShape();

        if(level.compareTo("Levels/level2.tmx") == 0) {

/*
            //For coin
            for (MapObject object : map.getLayers().get(10).getObjects().getByType(EllipseMapObject.class)) {

                Ellipse ellipse = ((EllipseMapObject) object).getEllipse();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((ellipse.x + ellipse.width / 2) / DaggerOfTime.PPM,
                        (ellipse.y + ellipse.height / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                circleShape.setRadius((ellipse.width / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = circleShape;
                body.createFixture(fixtureDef);
            }
*/
            //For ground
            for (MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getY() + rectangle.getHeight() / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                polygonShape.setAsBox((rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getHeight() / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = polygonShape;
                body.createFixture(fixtureDef);

            }
        } else {            //If level1 is selected

    /*
            //For coins
            for (MapObject object : map.getLayers().get(9).getObjects().getByType(EllipseMapObject.class)) {

                Ellipse ellipse = ((EllipseMapObject) object).getEllipse();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((ellipse.x + ellipse.width / 2) / DaggerOfTime.PPM,
                        (ellipse.y + ellipse.height / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                circleShape.setRadius((ellipse.width / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = circleShape;
                body.createFixture(fixtureDef);
            }
    */

            //For the ground
            for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getY() + rectangle.getHeight() / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                polygonShape.setAsBox((rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getHeight() / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = polygonShape;
                body.createFixture(fixtureDef);
            }

/*
            //For the Water/Acid
            for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getY() + rectangle.getHeight() / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                polygonShape.setAsBox((rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getHeight() / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = polygonShape;
                body.createFixture(fixtureDef);
            }

            //For the spikes
            for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getY() + rectangle.getHeight() / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                polygonShape.setAsBox((rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getHeight() / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = polygonShape;
                body.createFixture(fixtureDef);
            }


            //For the fire
            for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getY() + rectangle.getHeight() / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                polygonShape.setAsBox((rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getHeight() / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = polygonShape;
                body.createFixture(fixtureDef);

            }

            //For the key
            for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getY() + rectangle.getHeight() / 2) / DaggerOfTime.PPM);

                body = world.createBody(bodyDef);

                polygonShape.setAsBox((rectangle.getWidth() / 2) / DaggerOfTime.PPM,
                        (rectangle.getHeight() / 2) / DaggerOfTime.PPM);
                fixtureDef.shape = polygonShape;
                body.createFixture(fixtureDef);
            }
*/
        }  //End if-else
    }
}
