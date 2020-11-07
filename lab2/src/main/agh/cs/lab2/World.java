package agh.cs.lab2;

public class World {
    public static void main(String[] args) {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map2 = new GrassField(20);
        Animal dog = new Animal(map2,new Vector2d(2,2));
        Animal monkey = new Animal(map2,new Vector2d(3,4));
//        System.out.println("wypisuje zwierzaczki");
//        map2.getAnimals();
        map2.run(directions);
        System.out.println(map2.toString());
        System.out.println(dog.getPosition().toString());
        System.out.println(monkey.getPosition().toString());
//        MoveDirection[] directions = new OptionsParser().parse(args);
//        IWorldMap map2x2 = new GrassField(2);
//        Animal monkey = new Animal(map2x2, new Vector2d(0, 0));
//        map2x2.run(directions);
    }
}
