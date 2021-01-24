package org.myproject1;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MazeFieldRepresentation {
    private Rectangle representation;
    private Pane Canvas;
    private Vector2d position;
    private Maze maze;
    private MazeField mazeField;
    public MazeFieldRepresentation(MazeField mazeField,Maze maze) {
        this.representation=new Rectangle();
        this.Canvas=maze.getCanvas();
        this.maze=maze;
        this.mazeField=mazeField;
        this.position= mazeField.getPosition();
        this.representation.setX(this.position.x*(this.Canvas.getWidth()/this.maze.getDimensions().x));
        this.representation.setY(this.position.y*(this.Canvas.getHeight()/this.maze.getDimensions().y));
        this.representation.setWidth(this.Canvas.getWidth()/this.maze.getDimensions().x);
        this.representation.setHeight(this.Canvas.getHeight()/this.maze.getDimensions().y);
        this.representation.setFill(Color.rgb((int)(255*mazeField.getWall()),(int)(255*mazeField.getWall()),(int)(255*mazeField.getWall())));
        Canvas.getChildren().add(representation);
    }
    public void setRepresentationColour(String ColorName){
        if(ColorName.equals("BLUE")){
            this.representation.setFill(Color.BLUE);
        }
        if(ColorName.equals("YELLOW")){
            this.representation.setFill(Color.YELLOW);
        }
        if(ColorName.equals("RED")){
            this.representation.setFill(Color.RED);
        }
        if(ColorName.equals("GREEN")){
            this.representation.setFill(Color.GREEN);
        }
    }
}
