package com.hj.daggeroftime.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Sprites.Coin;
import com.hj.daggeroftime.Sprites.FireBreath;
import com.hj.daggeroftime.Sprites.Key;
import com.hj.daggeroftime.Sprites.Prince;

/**
 * Created by Harman on 3/12/17.
 */

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef) {

            //If prince collides with a hazard
            case DaggerOfTime.PRINCE_BIT | DaggerOfTime.HAZARD_BIT:

                if(fixA.getFilterData().categoryBits == DaggerOfTime.PRINCE_BIT) {

                    ((Prince)fixA.getUserData()).hit(100);
                } else {

                    ((Prince)fixB.getUserData()).hit(100);
                }
                break;

            //If prince collides with coin
            case DaggerOfTime.PRINCE_BIT | DaggerOfTime.COIN_BIT:

                if(fixA.getFilterData().categoryBits == DaggerOfTime.COIN_BIT) {

                    ((Coin)fixA.getUserData()).onCollision();
                } else {

                    ((Coin)fixB.getUserData()).onCollision();
                }
                break;

            //If prince collides with an enemy
            case DaggerOfTime.PRINCE_BIT | DaggerOfTime.ENEMY_BIT:

                if(fixA.getFilterData().categoryBits == DaggerOfTime.PRINCE_BIT) {

                    ((Prince)fixA.getUserData()).hit(25);
                } else {

                    ((Prince)fixB.getUserData()).hit(25);
                }
                break;

            //If the Prince collides with firebreath (fireballs)
            case DaggerOfTime.PRINCE_BIT | DaggerOfTime.FIRE_BREATH_BIT:

                if(fixA.getFilterData().categoryBits == DaggerOfTime.PRINCE_BIT) {

                    ((Prince)fixA.getUserData()).hit(100);
                    //((FireBreath)fixB.getUserData()).onCollision();
                } else {

                    ((Prince)fixB.getUserData()).hit(100);
                    //((FireBreath)fixA.getUserData()).onCollision();
                }

            //If the firebreath (fireball) collide with the ground
            case DaggerOfTime.FIRE_BREATH_BIT | DaggerOfTime.OBJECT_BIT:

                if (fixA.getFilterData().categoryBits == DaggerOfTime.FIRE_BREATH_BIT) {

                    ((FireBreath)fixA.getUserData()).onCollision();
                } else {

                    ((FireBreath)fixB.getUserData()).onCollision();
                }
                break;

            //If the Prince collides with a Key
            case DaggerOfTime.PRINCE_BIT | DaggerOfTime.KEY_BIT:

                if(fixA.getFilterData().categoryBits == DaggerOfTime.PRINCE_BIT) {

                    ((Prince)fixA.getUserData()).obtainKey();
                    ((Key)fixB.getUserData()).onCollision();
                } else {

                    ((Prince)fixB.getUserData()).obtainKey();
                    ((Key)fixA.getUserData()).onCollision();
                }
                break;

            //If the Prince collides with the door
            case DaggerOfTime.PRINCE_BIT | DaggerOfTime.DOOR_BIT:

                if(fixA.getFilterData().categoryBits == DaggerOfTime.PRINCE_BIT) {

                    ((Prince)fixA.getUserData()).setReachedDoor();
                } else {

                    ((Prince)fixB.getUserData()).setReachedDoor();
                }
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
