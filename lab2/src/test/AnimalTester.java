import agh.cs.lab2.Animal;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.OptionsParser;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static agh.cs.lab2.Animal.*;

public class AnimalTester {

    private Animal dog=new Animal();
    private OptionsParser parser=new OptionsParser();
    String DirectionsString[]=new String[]{"r","f","f"};//Array of directions in format "f","forward" etc.
    MoveDirection[] DirectionsMove=parser.parse(DirectionsString); //parsed to MoveDirection
    @Test
    public  void TestParser(){
        for(int i=0;i< DirectionsString.length;i++){
            String ans=DirectionsMove[i].toString();//checking if value before parse is egual to value after parse
            assertEquals(ans.charAt(0),DirectionsString[i].charAt(0));//added toString in MoveDirection enum
        }
    }
    @Test
    public void TestAnimalLocation(){
        for(MoveDirection dir:DirectionsMove){
            dog.move(dir);
        }
        String ans="wschod (4, 2)";
        assertEquals(dog.toString(),ans);
    }
}
