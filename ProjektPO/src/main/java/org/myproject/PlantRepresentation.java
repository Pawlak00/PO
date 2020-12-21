package org.myproject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlantRepresentation {
    private Circle representation;
    private Pane canvas;
    private Vector2d position;
    private RectangularWorldMap map;
    public PlantRepresentation(Vector2d position,RectangularWorldMap map,Pane canvas){
        this.representation=new Circle();
        this.position=position;
        this.map=map;
        this.canvas=canvas;
        this.representation.setRadius(this.canvas.getHeight()/this.map.mapHeight/2);
        this.representation.setCenterX(this.position.x*this.canvas.getWidth()/this.map.getMapWidth());
        this.representation.setCenterY(this.position.y*this.canvas.getHeight()/this.map.getMapHeight());
        this.representation.setFill(Color.GREEN);
        this.canvas.getChildren().add(representation);
    }
    public void removeFromCanvas(){
        this.canvas.getChildren().removeAll(representation);
    }
}
