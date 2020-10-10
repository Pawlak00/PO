import agh.cs.lab2.Vector2d;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static agh.cs.lab2.Vector2d.*;
public class Vector2dTest {
    @Test
    public void TestVector2d(){
//        test equals
        Vector2d v1=new Vector2d(3,2);
        Vector2d v2=new Vector2d(1,2);
        Vector2d v3=new Vector2d(3,2);
        assertEquals(v1.equals(v2),false);
        assertEquals(v3.equals(v1),true);
        assertEquals(v3.equals(v3),true);
//    test toString
        assertEquals(v1.toString(),"(3, 2)");
        assertEquals(v2.toString(),"(1, 2)");
//        test precedes
        assertEquals(v1.precedes(v2),false);
        assertEquals(v2.precedes(v1),true);
//        test follows
        assertEquals(v1.follows(v2),true);
        assertEquals(v2.follows(v1),false);
//    test upperRight
        assertEquals(v1.upperRight(v2).toString(),"(3, 2)");
        assertEquals(v2.upperRight(v1).toString(),"(3, 2)");
//        test lowerLeft
        assertEquals(v1.lowerLeft(v2).toString(),"(1, 2)");
        assertEquals(v2.lowerLeft(v1).toString(),"(1, 2)");
//    test add
        assertEquals(v1.add(v2).toString(),"(4, 4)");
        assertEquals(v2.add(v1).toString(),"(4, 4)");
//        test substract
        assertEquals(v1.substract(v2).toString(),"(2, 0)");
        assertEquals(v2.substract(v1).toString(),"(-2, 0)");
//        test opposite
        assertEquals(v1.opposite().toString(),"(-3, -2)");
        assertEquals(v2.opposite().toString(),"(-1, -2)");
    }


}
