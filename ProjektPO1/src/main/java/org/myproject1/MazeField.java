package org.myproject1;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class MazeField {
    private Vector2d position;
    private MazeFieldRepresentation representation;
    private double isWall;
    private Pane Canvas;
    private Maze maze;
    public MazeField(Vector2d position, Maze maze,double isWall){
        this.position=position;
        this.isWall=isWall;
        this.Canvas=maze.getCanvas();
        this.representation=new MazeFieldRepresentation(this, maze);
        this.maze=maze;
    }
    public Vector2d getPosition() {
        return position;
    }

    public Maze getCanvas() {
        return this.maze;
    }
    public MazeFieldRepresentation getRepresentation(){
        return this.representation;
    }
    public double getWall(){
        return this.isWall;
    }
}
