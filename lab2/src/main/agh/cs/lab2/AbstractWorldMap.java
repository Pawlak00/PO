package agh.cs.lab2;

import java.awt.*;
import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Map<Vector2d,IMapElement> elements = new HashMap<>();
    public MapBoundary Boundary;
    public int min_x=0;
    public int min_y=0;
    public int max_x=0;
    public int max_y=0;
    public void getDimensions(Animal act_animal){
        this.min_x=min(this.min_x,act_animal.getPosition().x);
        this.min_y=min(this.min_y,act_animal.getPosition().y);
        this.max_x=max(this.max_x,act_animal.getPosition().x);
        this.max_y=max(this.max_y,act_animal.getPosition().y);
    }   

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
            IMapElement a=elements.get(oldPosition);
            elements.remove(oldPosition);
            elements.put(newPosition,a);
    }
    @Override
    public void run(MoveDirection[] directions) {
        Collection<IMapElement> elems = elements.values();
        ArrayList<Animal> animals1 = new ArrayList<>();
        for(IMapElement elem: elems){
            if(elem instanceof Animal){
                animals1.add((Animal) elem);
            }
        }
        int n=animals1.size();
        if(n!=0) {
            for (int i = 0; i < directions.length; i++) {
                IMapElement act_animal = animals1.get(i%n);
                if(act_animal instanceof Animal) {
                    Vector2d p1=act_animal.getPosition();
                    act_animal.move(directions[i]);
                    if(!p1.equals(act_animal.getPosition())) {
                        this.Boundary.xSorted.add(act_animal);
                        this.Boundary.ySorted.add(act_animal);
                    }
                }
            }
        }
    }

    @Override
    public void getAnimals() {
        for(IMapElement a:elements.values()){
            System.out.println(a.toString()+a.getPosition().toString());
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return (this.objectAt(position)!=null);
    }

    @Override
    public String toString() {
        MapVisualizer MapImage=new MapVisualizer(this);
        System.out.println(this.Boundary.xSorted.first().getPosition().x);
        System.out.println(this.Boundary.ySorted.first().getPosition().y);
        System.out.println(this.Boundary.xSorted.last().getPosition().x);
        System.out.println(this.Boundary.ySorted.last().getPosition().y);
        for(IMapElement e:this.Boundary.xSorted){
            System.out.println(e.getPosition().toString());
        }
        for(IMapElement e:this.Boundary.ySorted){
            System.out.println(e.getPosition().toString());
        }
        System.out.println(this.Boundary.ySorted);
        String res= MapImage.draw(new Vector2d(this.Boundary.xSorted.first().getPosition().x,this.Boundary.ySorted.first().getPosition().y ), new Vector2d(this.Boundary.xSorted.last().getPosition().x, this.Boundary.ySorted.last().getPosition().y));
        return res;
    }
}
