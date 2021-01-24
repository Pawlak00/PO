package org.myproject1;

import javafx.scene.layout.Pane;

public class MazeEngine {
    private Maze maze;
    private Pane Canvas;
    public MazeEngine(Vector2d dimensions, Pane Canvas){
        this.maze=new Maze(dimensions,Canvas);
        this.Canvas=Canvas;
    }
    public void genMaze(){
        this.maze.generateMaze();
    }
    public void changeMazeSize(Vector2d dimensions){
        maze.setSize(dimensions);
    }
    public void changeMazeSolver(String algorithmName){
        System.out.println(algorithmName);
        maze.setMazeSolver(algorithmName);
    }
    public void changeMazeGenerator(String algorithmName){
        maze.setMazeGenerator(algorithmName);
    }

    public Maze getMaze() {
        return maze;
    }

    public void resetMaze(){
        maze.clearCanvas();
    }
}
