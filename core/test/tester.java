import com.hj.daggeroftime.DaggerOfTime;
import com.hj.daggeroftime.Screens.SplashScreen;

import org.junit.Ignore;
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

}
