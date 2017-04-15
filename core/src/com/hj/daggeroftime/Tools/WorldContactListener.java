package com.hj.daggeroftime.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Sprites.Coin;
import com.hj.daggeroftime.Sprites.FireBreath;
import com.hj.daggeroftime.Sprites.InteractiveTileObject;
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

            case DaggerOfTime.PRINCE_BIT | DaggerOfTime.FIRE_BREATH:

                if(fixA.getFilterData().categoryBits == DaggerOfTime.PRINCE_BIT) {

                    ((Prince)fixA.getUserData()).hit(100);
                    ((FireBreath)fixB.getUserData()).onCollision();
                } else {

                    ((Prince)fixB.getUserData()).hit(100);
                    ((FireBreath)fixA.getUserData()).onCollision();
                }
                break;

            case DaggerOfTime.OBJECT_BIT | DaggerOfTime.FIRE_BREATH:

                if(fixA.getFilterData().categoryBits == DaggerOfTime.FIRE_BREATH) {

                    ((FireBreath)fixA.getUserData()).onCollision();
                    System.out.println(fixA.getUserData());

                } else {

                    ((FireBreath)fixB.getUserData()).onCollision();
                    System.out.println(fixB.getUserData());
                }
                break;
        }

        if(fixA.getUserData() == "prince" || fixB.getUserData() == "prince") {

            Fixture thePrince = fixA.getUserData() == "prince" ? fixA : fixB;
            Fixture object = thePrince == fixA ? fixB : fixA;

            if(object.getUserData() instanceof InteractiveTileObject) {

                ((InteractiveTileObject) object.getUserData()).onCollision();
            }
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
