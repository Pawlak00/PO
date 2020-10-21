package agh.cs.lab2;

public class World {
    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        Animal dog=new Animal();
        dog.move(MoveDirection.RIGHT);
        dog.move(MoveDirection.FORWARD);
        dog.move(MoveDirection.FORWARD);
        dog.move(MoveDirection.FORWARD);
        dog.move(MoveDirection.FORWARD);
        System.out.println(dog.toString());
        MoveDirection steering[]=new MoveDirection[args.length];
        OptionsParser parser=new OptionsParser();
        steering=parser.parse(args);
        for(MoveDirection n:steering) {
            System.out.println(n.toString());
        }
    }
}
