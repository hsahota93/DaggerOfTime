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

    DaggerOfTime daggerOfTime = new DaggerOfTime();

    @Test
    public void testWidth(){
        int actaualValue = daggerOfTime.screenWidth;
        int expectedValue = 400;
        assertEquals(actaualValue,expectedValue);
    }

    @Test
    public void testHeight(){
        int actaualValue = daggerOfTime.screenHeight;
        int expectedValue = 300;
        assertEquals(actaualValue,expectedValue);
    }

    @Test
    public void testPPMValue(){
        float actaualValue = daggerOfTime.PPM;
        float expectedValue = 100;
        assertEquals(actaualValue,expectedValue,0);
    }

}
