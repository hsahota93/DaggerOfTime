import com.hj.daggeroftime.DaggerOfTime;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jacob on 2/27/2017.
 */
public class tester {
    @Test
    public void thisAlwaysPasses()
    {
        assertTrue(true);
    }

    @Test
    @Ignore
    public void thisIsIgnored()
    {
    }

    @Test
    public void testAdd(){
        DaggerOfTime demo = new DaggerOfTime();
        assertEquals(demo.add(), 7);
    }

}
