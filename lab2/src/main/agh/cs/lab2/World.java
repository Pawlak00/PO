package agh.cs.lab2;

public class World {
    public static void main(String[] args) {
        try {
            MoveDirection[] directions = new OptionsParser().parse(args);
            IWorldMap map2 = new GrassField(2);
            Animal dog = new Animal(map2,new Vector2d(0,0));
            map2.run(directions);
            System.out.println(map2.toString());
            System.out.println(dog.getPosition().toString());
        }catch (IllegalArgumentException ex){
            System.out.println(ex);
        }
    }
}
