package agh.cs.lab2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.*;

public class GrassField extends AbstractWorldMap{
    private int n;

    public GrassField(int n){
        this.Boundary=new MapBoundary();
        Random rand=new Random();
        this.n=n;
        for(int i=0;i<n;i++){
            int x=rand.nextInt((int)sqrt(10*n));
            int y=rand.nextInt((int)sqrt(10*n));
            Vector2d v=new Vector2d(x,y);
            this.min_x=min(this.min_x,x);
            this.min_y=min(this.min_y,y);
            this.max_x=max(this.max_x,x);
            this.max_y=max(this.max_y,y);
            Grass g=new Grass(v);
            this.elements.put(v,g);
            this.Boundary.ySorted.add(g);
            this.Boundary.xSorted.add(g);
        }
    }
    @Override
    public boolean place(Animal animal) {
        if(this.isOccupied(animal.getPosition()) && this.objectAt(animal.getPosition()) instanceof Animal){
            throw new IllegalArgumentException("Position is already occupied");
        }else{
            this.elements.put(animal.getPosition(), animal);
            animal.addObserver(this);
            this.Boundary.xSorted.add(animal);
            this.Boundary.ySorted.add(animal);
            return true;
        }
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        super.positionChanged(oldPosition,newPosition);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(this.isOccupied(position) && this.objectAt(position) instanceof Animal){
            return false;
        }else{
            return true;
        }
    }
    @Override
    public Object objectAt(Vector2d position) {
        if(elements.containsKey(position)){
            return elements.get(position);
        }
        return null;
    }
}
