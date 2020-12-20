package org.myproject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Animal implements IMapElement{
    private MapDirection dir;
    private Vector2d location;
    private RectangularWorldMap map;
    private List<IPositionChangeObserver> observers;
    private int energyLevel;
    private Genotype genes;
    public Circle representation;
    private Pane canvas;
    private Animal(){
        this.representation=new Circle();
        this.representation.setRadius(5);
        this.representation.setFill(Color.RED);
    }
    public Animal(RectangularWorldMap map, Vector2d initialPosition, int energyLevel, Pane canvas){
        this();
        this.canvas=canvas;
        this.dir=RandomGetter.getRandomMapDir();
        this.map=map;
        this.location = initialPosition;
        this.observers=new ArrayList<>();
        this.energyLevel=energyLevel;
        this.genes=new Genotype(32);
        this.canvas.getChildren().add(representation);
        map.place(this);
    }
    public Animal(Animal parent1,Animal parent2){
        this();
        this.dir=RandomGetter.getRandomMapDir();
        this.map=parent1.map;
        this.location=this.map.getLocForKid(parent1.getPosition());
        this.observers=new ArrayList<>();
        this.energyLevel= (int) (0.25*(parent2.getEnergyLevel()+ parent1.getEnergyLevel()));
        parent1.energyLevel-=0.25*parent1.energyLevel;
        parent2.energyLevel-=0.25* parent2.energyLevel;
        this.genes=new Genotype(parent1.getGenes(),parent2.getGenes(), parent1.getGenes().getGeneCode().length);
        this.canvas=parent1.canvas;
        this.canvas.getChildren().add(representation);
        map.place(this);
    }
    public int getEnergyLevel() {
        return energyLevel;
    }
    public Genotype getGenes(){
        return this.genes;
    }
    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    @Override
    public String toString(){
        return this.getPosition().toString()+" "+this.getEnergyLevel();
    }
    @Override
    public Vector2d getPosition(){
        return this.location;
    }
    @Override
    public void move(MapDirection direction)  {
        Vector2d op=this.location;
        this.map.Animals.remove(this.getPosition(),this);
        if(this.map.canMoveTo(this.location.add(direction.toUnitVector(),new Vector2d(this.map.getMapWidth(),this.map.mapHeight)))){
            Vector2d tmp=new Vector2d(this.location.x,this.location.y);
            this.location=this.location.add(direction.toUnitVector(),new Vector2d(this.map.getMapWidth(),this.map.mapHeight));
        }
        this.energyLevel-=this.map.moveEnergy;
        this.representation.setCenterY(this.location.y*5);
        this.representation.setCenterX(this.location.x*5);
        this.positionChanged(this,this);
    }
    void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }
    void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }
    void positionChanged(Animal oldAnimal,Animal newAnimal){
        for(IPositionChangeObserver observer:this.observers){
            observer.positionChanged(oldAnimal,newAnimal);
        }
    }

}