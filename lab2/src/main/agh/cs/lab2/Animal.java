package agh.cs.lab2;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Animal implements IMapElement{
    private MapDirection dir=MapDirection.NORTH;
    private Vector2d location=new Vector2d(2,2);
    private IWorldMap map;
    private List<IPositionChangeObserver> observers;
    public Animal(IWorldMap map){
        this(map,new Vector2d(0,2));
    }
    public Animal(IWorldMap map,Vector2d initialPosition){
        this.map=map;
//        System.out.println(this.map.isOccupied(initialPosition));
        this.location = initialPosition;
        this.observers=new ArrayList<>();
        map.place(this);
    }
    @Override
    public String toString(){
        switch (this.dir) {
            case EAST:
                return "E";
            case WEST:
                return "W";
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            default:
                return "ERRRRRORRRRRRRRRRRRRR";
        }
    }
    @Override
    public Vector2d getPosition(){
        return this.location;
    }
    @Override
    public void move(MoveDirection direction){
        Vector2d op=this.location;
        if(direction.equals(MoveDirection.FORWARD)){
            if(this.map.canMoveTo(this.location.add(this.dir.toUnitVector()))){
                this.location=this.location.add(this.dir.toUnitVector());
            }
        }
        if(direction.equals(MoveDirection.BACKWARD)){
            if(this.map.canMoveTo(this.location.add(this.dir.toUnitVector()).opposite())){
                this.location=this.location.add(this.dir.toUnitVector().opposite());
            }
        }
        if(direction.equals(MoveDirection.LEFT)){
            this.dir=this.dir.previous();
        }
        if(direction.equals(MoveDirection.RIGHT)){
            this.dir=this.dir.next();
        }
        this.positionChanged(op,this.location);
    }
    void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }
    void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }
    void positionChanged(Vector2d oldPosition,Vector2d newPosition){
        for(IPositionChangeObserver observer:this.observers){
            observer.positionChanged(oldPosition,newPosition);
        }
    }
}
