package org.myproject;

public class Vector2d {
    public final int x;
    public final int y;
    public Vector2d(int x,int y){
        this.x=x;
        this.y=y;
    }
    public String toString(){
        return "("+this.x+", "+this.y+")";
    }
    public Vector2d add(Vector2d other,Vector2d bounds){
        int newX;
        int newY;
        if(this.x+other.x<0){
            newX= bounds.x-this.x-other.x;
        }else if(this.x+other.x>= bounds.x){
            newX=(this.x+ other.x)% bounds.x;
        }else{
            newX=this.x+other.x;
        }
        if(this.y+other.y<0){
            newY= bounds.y-this.y-other.y;
        }else if(this.y+other.y>= bounds.y){
            newY=(this.y+ other.y)% bounds.y;
        }else{
            newY=this.y+other.y;
        }
        return new Vector2d(newX,newY);
    }
    @Override
    public int hashCode(){
        int hash = 97;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }
    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return this.x==that.x && this.y==that.y;
    }
}
