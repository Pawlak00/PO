package agh.cs.lab2;

public class Vector2d {
    final public int x;
    public final int y;
    public Vector2d(int x,int y){
        this.x=x;
        this.y=y;
    }
    public String toString(){
        return "("+this.x+", "+this.y+")";
    }
    public boolean precedes(Vector2d other){
        return this.x<=other.x && this.y<= other.y;
    }
    public boolean follows(Vector2d other){
        return this.x>=other.x && this.y>=other.y;
    }
    public Vector2d upperRight(Vector2d other){
        Vector2d upRight=new Vector2d(Integer.max(this.x,other.x),Integer.max(this.y,other.y));
        return upRight;
    }
    public Vector2d lowerLeft(Vector2d other){
        Vector2d downLeft=new Vector2d(Integer.min(this.x,other.x),Integer.min(this.y,other.y));
        return downLeft;
    }
    public Vector2d add(Vector2d other){
        Vector2d newVec=new Vector2d(this.x+other.x,this.y+other.y);
        return newVec;
    }
    public Vector2d opposite(){
        Vector2d newVec=new Vector2d(-this.x,-this.y);
        return newVec;
    }
    public Vector2d substract(Vector2d other){
        Vector2d newVec=new Vector2d(this.x-other.x,this.y-other.y);
        return newVec;
    }
    @Override
    public int hashCode(){
        int hash = 97;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return this.x==that.x && this.y==that.y;
    }
}
