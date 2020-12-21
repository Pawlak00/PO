package org.myproject;


public interface IMapElement {
    String toString();
    Vector2d getPosition();
    void move(MapDirection dir) ;
}