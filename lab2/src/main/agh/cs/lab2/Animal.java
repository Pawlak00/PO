package agh.cs.lab2;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Animal {
    private MapDirection dir=MapDirection.NORTH;
    private Vector2d location=new Vector2d(2,2);
    private IWorldMap map;
    public Animal(IWorldMap map){
        this(map,new Vector2d(0,2));
    }
    public Animal(IWorldMap map,Vector2d initialPosition){
        this.map=map;
//        System.out.println(this.map.isOccupied(initialPosition));
        if((this.map.isOccupied(initialPosition) && this.map.objectAt(initialPosition) instanceof Grass) || !this.map.isOccupied(initialPosition)) {
//            System.out.println("dodaje nowe zwierze");
            this.location = initialPosition;
            this.map.place(this);
        }
    }
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
    public Vector2d getPosition(){
        return this.location;
    }
    public void move(MoveDirection direction){
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

    }
}
