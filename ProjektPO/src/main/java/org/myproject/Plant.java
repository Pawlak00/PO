package org.myproject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Plant implements IMapElement{
    public Vector2d position;
    public int plantEnergy;
    private final RectangularWorldMap map;
    public Circle representation;
    private Pane canvas;
    public Plant(RectangularWorldMap map,Vector2d location,int EnergyAmount, Pane canvas){
        this.map=map;
        this.representation=new Circle();
        this.representation.setRadius(5);
        this.representation.setFill(Color.GREEN);
        this.representation.setCenterX(location.x*5);
        this.representation.setCenterY(location.y*5);
        this.position=location;
        this.plantEnergy=EnergyAmount;
        this.canvas=canvas;
        this.canvas.getChildren().add(representation);
    }
    @Override
    public Vector2d getPosition(){
        return this.position;
    }
    @Override
    public String toString(){
        return "*";
    }
    @Override
    public void move(MapDirection dir) { }

}