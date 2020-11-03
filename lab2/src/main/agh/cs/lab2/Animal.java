package agh.cs.lab2;

public class Animal {
    private MapDirection dir=MapDirection.NORTH;
    private Vector2d location=new Vector2d(2,2);
    private IWorldMap map;
    public Animal(IWorldMap map){
        this(map,new Vector2d(0,2));
    }
    public Animal(IWorldMap map,Vector2d initialPosition){
        this.map=map;
        this.location=initialPosition;
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
        if(direction.equals(MoveDirection.LEFT) || direction.equals(MoveDirection.RIGHT)){
            this.dir=this.dir.next();
        }else{
            if(map.canMoveTo(this.location.add(dir.toUnitVector()))){
                this.location=this.location.add(dir.toUnitVector());
                map.place(this);
            }
        }
    }
}
