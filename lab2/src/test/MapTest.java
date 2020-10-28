import agh.cs.lab2.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static agh.cs.lab2.Animal.*;


public class MapTest {
    MoveDirection[] directions = new OptionsParser().parse(new String[]{"f","b", "r", "l", "f", "f", "r", "r" ,"f","f", "f", "f", "f" ,"f", "f" ,"f"});
    IWorldMap mapka = new RectangularMap(10, 5);
    @Test
    public void TestMap(){
        Animal dog=new Animal(mapka);
        mapka.place(dog);
        Animal monkey=new Animal(mapka,new Vector2d(3,4));
        mapka.place(monkey);
        mapka.run(directions);
        assertEquals(dog.getPosition().equals(new Vector2d(1,0)),true);
        assertEquals(monkey.getPosition().equals(new Vector2d(4,1)),true);
    }
}
