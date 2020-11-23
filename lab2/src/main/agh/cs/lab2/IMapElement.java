package agh.cs.lab2;

public interface IMapElement {
    public String toString();
    public Vector2d getPosition();
    public void move(MoveDirection dir);
}
