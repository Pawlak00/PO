package agh.cs.lab2;

public class Grass{
    Vector2d position;
    public Grass(Vector2d location){
        this.position=location;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public String toString(){
        return "*";
    }
}
