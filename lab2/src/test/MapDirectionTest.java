import org.junit.Test;

import static agh.cs.lab2.MapDirection.*;
import static org.junit.Assert.assertEquals;

public class MapDirectionTest {
    @Test
    public void TestMap(){
        assertEquals(SOUTH.next(),WEST);
        assertEquals(WEST.next(),NORTH);
        assertEquals(NORTH.next(),EAST);
        assertEquals(EAST.next(),SOUTH);
        assertEquals(SOUTH.previous(),EAST);
        assertEquals(EAST.previous(),NORTH);
        assertEquals(WEST.previous(),SOUTH);
        assertEquals(NORTH.previous(),WEST);
    }
}

