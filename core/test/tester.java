import com.hj.daggeroftime.DaggerOfTime;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jacob on 2/27/2017.
 */
public class tester {

    //gradle2.bat connectInstrumentTest
    @Test
    public void thisAlwaysPasses()
    {
        assertTrue(true);
    }

    private DaggerOfTime daggerOfTime = new DaggerOfTime();

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
        assertEquals(actualValue, expectedValue,0);
    }

    @Test
    public void testCoinShort() {

        float actualValue = DaggerOfTime.COIN_BIT;
        float expectedValue = 4;
        assertEquals(actualValue, expectedValue, 0);
    }

    @Test
    public void testDestroyedShort() {

        float actualValue = DaggerOfTime.DESTROYED_BIT;
        float expectedValue = 16;
        assertEquals(actualValue, expectedValue, 0);
    }

    @Test
    public void testPrinceShort() {

        float actualValue = DaggerOfTime.PRINCE_BIT;
        float expectedValue = 2;
        assertEquals(actualValue, expectedValue, 0);
    }

    @Test
    public void testSpikeShort() {

        float actualValue = DaggerOfTime.ENEMY_BIT;
        float expectedValue = 8;
        assertEquals(actualValue, expectedValue, 0);
    }

}
