package agh.cs.lab2;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class RectangularMap extends AbstractWorldMap {
    public RectangularMap(int width,int height){
        this.max_x=width;
        this.max_y=height;
    }
    @Override
    public boolean place(Animal animal) {
        if(!this.canMoveTo(animal.getPosition())) {
            throw new IllegalArgumentException("Position is out of range");
        }else{
            this.animals.put(animal.getPosition(), animal);
            return true;
        }
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return (abs(position.x)<= this.max_x && abs(position.y)<= this.max_y && this.isOccupied(position)==false );
    }
    @Override
    public void run(MoveDirection[] directions) {
        super.run(directions);
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position);
    }
    @Override
    public void getAnimals(){
        super.getAnimals();
    }
    @Override
    public Object objectAt(Vector2d position) {
        if(!animals.isEmpty()) {
            if (animals.containsKey(position)) {
                return animals.get(position);
            } else {
                return null;
            }
        }
        return null;
    }
    public String toString(){
        return super.toString();
    }
}
