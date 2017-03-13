package com.hj.daggeroftime.Scenes;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jacob on 3/12/2017.
 */
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class HudTest {

    @Test
    public void testAddScore(){

        Hud.addScore(10);
        Integer expInteger = 10;
        assertEquals(expInteger,Hud.score);
    }

    @Test
    public void testTimerLabelForNull(){
        Hud hud = new Hud();
        hud.initiateVariables();
        assertNotNull(hud.worldTimer);
    }

    @Test
    public void testTimeCountForNull(){
        Hud hud = new Hud();
        hud.initiateVariables();
        assertNotNull(hud.timeCount);
    }

    @Test
    public void testScoreForNull(){
        Hud hud = new Hud();
        hud.initiateVariables();
        assertNotNull(Hud.score);
    }




    @Test
    public void testTimeTimerValue(){
        Hud hud = new Hud();
        hud.initiateVariables();
        Integer actuaValue = hud.worldTimer;
        Integer expectedValue = hud.timeLimit;
        assertEquals(actuaValue,expectedValue);
    }
    @Test
    public void testHudTimerUpdate(){
        Hud hud = new Hud();
        hud.initiateVariables();
        hud.worldTimerDecrementer();
        Integer expectedValue = hud.timeLimit -1;
        Integer actualValue = hud.worldTimer;
        assertEquals(expectedValue, actualValue);
    }

}