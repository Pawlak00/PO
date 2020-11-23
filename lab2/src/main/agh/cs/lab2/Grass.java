package agh.cs.lab2;

public class Grass implements IMapElement{
    Vector2d position;
    public Grass(Vector2d location){
        this.position=location;
    }
    @Override
    public Vector2d getPosition(){
        return this.position;
    }
    @Override
    public String toString(){
        return "*";
    }
    @Override
    public void move(MoveDirection dir) {
        return;
    }

}
