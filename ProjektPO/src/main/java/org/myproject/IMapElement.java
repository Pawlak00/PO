package org.myproject;


public interface IMapElement {
    public String toString();
    public Vector2d getPosition();
    public void move(MapDirection dir) ;
}