package agh.cs.lab2;

public interface IPositionChangeObserver {
    public void positionChanged(Vector2d oldPosition,Vector2d newPosition);
}
