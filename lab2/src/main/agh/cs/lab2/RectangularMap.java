package agh.cs.lab2;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class RectangularMap extends AbstractWorldMap {
    public RectangularMap(int width,int height){
        this.max_x=width;
        this.max_y=height;
        this.Boundary=new MapBoundary();
    }
    @Override
    public boolean place(Animal animal) {
        if(!this.canMoveTo(animal.getPosition())) {
            throw new IllegalArgumentException("Position is out of range");
        }else{
            this.elements.put(animal.getPosition(), animal);
            animal.addObserver(this);
            return true;
        }
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return (abs(position.x)<= this.max_x && abs(position.y)<= this.max_y && this.isOccupied(position)==false );
    }
    @Override
    public Object objectAt(Vector2d position) {
        if(!elements.isEmpty()) {
            if (elements.containsKey(position)) {
                return elements.get(position);
            } else {
                return null;
            }
        }
        return null;
    }
}
