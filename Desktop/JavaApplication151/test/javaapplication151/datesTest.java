package javaapplication151;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class datesTest {

    public datesTest() {

    }

    @Test
    public void testdayto() {
        dates demo = new dates(12, 12, 2012);
        dates demo2 = new dates(10, 12, 2012);
        assertEquals("Testing days", 2, demo.daysto(demo2));
    }

    @Test
    public void testMonth() {
        dates demo = new dates(12, 12, 2012);
        dates demo2 = new dates(12, 11, 2012);
        assertEquals("Testing days", 3, demo.daysto(demo2));
    }

    @Test
    public void testYear() {
        dates demo = new dates(12, 12, 2012);
        dates demo2 = new dates(12, 12, 2013);
        assertEquals("Testing days", 30, demo.daysto(demo2));
    }
}
