import agh.cs.lab2.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static agh.cs.lab2.Animal.*;


public class GrassFieldTest {
    @Test
    public void TestMap(){
        for(int i=0;i<100000;i++) {
            MoveDirection[] directions = new OptionsParser().parse(new String[]{"f", "r", "f", "r", "f", "r", "f"});
            MoveDirection[] directions3x3 = new OptionsParser().parse(new String[]{"f", "f", "f", "f", "f"});
            MoveDirection[] directions5x5 = new OptionsParser().parse(new String[]{"f", "r", "r", "l", "f"});
            MoveDirection[] directions10x10 = new OptionsParser().parse(new String[]{"f", "f", "r", "f", "r", "f", "l", "f", "f"});
            IWorldMap map2x2 = new GrassField(2);
            IWorldMap map3x3 = new GrassField(3);
            IWorldMap map5x5 = new GrassField(5);
            IWorldMap map10x10 = new GrassField(10);
            Animal monkey = new Animal(map2x2, new Vector2d(0, 0));
            map2x2.run(directions);
            assertEquals(monkey.getPosition().equals(new Vector2d(0, 0)), true);
            Animal monkey1 = new Animal(map3x3, new Vector2d(0, 0));
            map3x3.run(directions3x3);
            assertEquals(monkey1.getPosition().equals(new Vector2d(0, 5)), true);
            Animal monkey2 = new Animal(map5x5, new Vector2d(0, 0));
            map5x5.run(directions5x5);
            assertEquals(monkey2.getPosition().equals(new Vector2d(1, 1)), true);
            Animal monkey3 = new Animal(map10x10, new Vector2d(0, 0));
            Animal monkey4 = new Animal(map10x10, new Vector2d(0, 1));
            map10x10.run(directions10x10);
            assertEquals(monkey3.getPosition().equals(new Vector2d(1, 0)), true);
            assertEquals(monkey4.getPosition().equals(new Vector2d(0, 5)), true);
        }
    }
}
