import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Scenes.Hud;
import com.hj.daggeroftime.Screens.GetInfo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jacob on 2/27/2017.
 */

public class tester {

    @Test
    public void testLives() {

        int actualValue = DaggerOfTime.maxLives;
        int expectedValue = 2;

        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testStorySeen() {

        boolean actualValue = DaggerOfTime.seenStory;
        boolean expectedValue = false;

        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testHudScore() {

        int actualValue = Hud.score;
        int expectedValue = 0;

        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testGetInfoName() {

        String actualValue = GetInfo.name;
        String expectedValue = "TEST";

        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void thisAlwaysPasses() {
        assertTrue(true);
    }

    @Test
    public void testWidth() {

        int actualValue = DaggerOfTime.screenWidth;
        int expectedValue = 400;
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testHeight() {

        int actualValue = DaggerOfTime.screenHeight;
        int expectedValue = 300;
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testPPMValue() {

        float actualValue = DaggerOfTime.PPM;
        float expectedValue = 100;
        assertEquals(actualValue, expectedValue, 0);
    }

    @Test
    public void testCoinShort() {

        float actualValue = DaggerOfTime.COIN_BIT;
        float expectedValue = 8;
        assertEquals(actualValue, expectedValue, 0);
    }

    @Test
    public void testDestroyedShort() {

        float actualValue = DaggerOfTime.DESTROYED_BIT;
        float expectedValue = 32;
        assertEquals(actualValue, expectedValue, 0);
    }

    @Test
    public void testPrinceShort() {

        float actualValue = DaggerOfTime.PRINCE_BIT;
        float expectedValue = 4;
        assertEquals(actualValue, expectedValue, 0);
    }

    @Test
    public void testSpikeShort() {

        float actualValue = DaggerOfTime.ENEMY_BIT;
        float expectedValue = 16;
        assertEquals(actualValue, expectedValue, 0);
    }

}
