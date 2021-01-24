package org.myproject1;

import java.util.List;

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
    public Vector2d add(Vector2d other){
        int newX;
        int newY;
        newX=this.x+other.x;
        newY=this.y+other.y;
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
    public boolean is_inside(Vector2d boundary){
        return (this.x>=0 && this.x<= boundary.x && this.y>=0 && this.y<= boundary.y);
    }
}