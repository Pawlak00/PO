package org.myproject;

import javafx.scene.layout.Pane;

public class Plant implements IMapElement{
    public Vector2d position;
    public int plantEnergy;
    private final RectangularWorldMap map;
    public PlantRepresentation representation;
    private final Pane canvas;
    public Plant(RectangularWorldMap map,Vector2d location,int EnergyAmount, Pane canvas){
        this.map=map;
        this.position=location;
        this.plantEnergy=EnergyAmount;
        this.canvas=canvas;
        this.representation=new PlantRepresentation(position,map,canvas);
    }
    public void removeFromMap(){
        this.representation.removeFromCanvas();
        this.map.removePlant(this);
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