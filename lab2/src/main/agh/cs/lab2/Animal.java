package agh.cs.lab2;

public class Animal {
    private MapDirection dir=MapDirection.NORTH;
    private Vector2d location=new Vector2d(2,2);
    public String toString(){
        return dir.toString()+' '+location.toString();
    }
    public void move(MoveDirection direction){
        if(direction==MoveDirection.LEFT || direction==MoveDirection.RIGHT){
            this.dir=this.dir.next();
        }else{
            Vector2d leftD=new Vector2d(0,0);
            Vector2d upperR=new Vector2d(4,4);
            if(direction==MoveDirection.BACKWARD){
                this.location=this.location.lowerLeft(this.location.add(this.dir.toUnitVector()));
                this.location=this.location.upperRight(leftD);
            }else if(direction==MoveDirection.FORWARD){
                this.location=this.location.upperRight(this.location.add(this.dir.toUnitVector()));
                this.location=this.location.lowerLeft(upperR);
            }
        }
    }
}
