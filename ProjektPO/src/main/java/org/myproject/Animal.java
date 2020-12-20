package org.myproject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement{
    private Vector2d location;
    private RectangularWorldMap map;
    private List<IPositionChangeObserver> observers;
    private int energyLevel;
    private Genotype genes;
    public Circle representation;
    private Pane canvas;
    private int age;
    private Animal(){
        this.age=0;
        this.representation=new Circle();
        this.representation.setFill(Color.RED);
        this.observers=new ArrayList<>();
    }
    public Animal(RectangularWorldMap map, Vector2d initialPosition, int energyLevel, Pane canvas){
        this();
        this.canvas=canvas;
        this.map=map;
        this.location = initialPosition;
        this.energyLevel=energyLevel;
        this.genes=new Genotype(32);
        this.representation.setRadius(this.canvas.getHeight()/this.map.mapHeight/2);
        this.representation.setCenterX(this.location.x*this.canvas.getWidth()/this.map.getMapWidth());
        this.representation.setCenterY(this.location.y*this.canvas.getHeight()/this.map.mapHeight);
        this.canvas.getChildren().add(representation);
        map.place(this);
    }
    public Animal(Animal parent1,Animal parent2){
        this();
        this.map=parent1.map;
        this.location=this.map.getLocForKid(parent1.getPosition());
        this.energyLevel= (int) (0.25*(parent2.getEnergyLevel()+ parent1.getEnergyLevel()));
        parent1.energyLevel-=0.25*parent1.energyLevel;
        parent2.energyLevel-=0.25* parent2.energyLevel;
        this.genes=new Genotype(parent1.getGenes(),parent2.getGenes(), parent1.getGenes().getGeneCode().length);
        this.canvas=parent1.canvas;
        this.representation.setRadius(this.canvas.getHeight()/this.map.mapHeight/2);
        this.representation.setCenterX(this.location.x*this.canvas.getWidth()/this.map.getMapWidth());
        this.representation.setCenterY(this.location.y*this.canvas.getHeight()/this.map.mapHeight);
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
        this.age++;
        Vector2d op=this.location;

        this.map.Animals.remove(this.getPosition(),this);
        this.canvas.getChildren().removeAll(representation);
        this.map.availableFields.put(this.getPosition(),this.getPosition());
        if(this.map.canMoveTo(this.location.add(direction.toUnitVector(),new Vector2d(this.map.getMapWidth(),this.map.mapHeight)))){
            Vector2d tmp=new Vector2d(this.location.x,this.location.y);
            this.location=this.location.add(direction.toUnitVector(),new Vector2d(this.map.getMapWidth(),this.map.mapHeight));
        }
        this.energyLevel-=this.map.moveEnergy;

        this.representation.setCenterX(this.location.x*this.canvas.getWidth()/this.map.getMapWidth());
        this.representation.setCenterY(this.location.y*this.canvas.getHeight()/this.map.getMapHeight());
        this.canvas.getChildren().add(representation);
        this.map.availableFields.put(this.getPosition(),this.getPosition());
        this.positionChanged(this,this);
    }
    public void remove(){
//        usun z animals
//        usun z canvas
    }
    public void add(){
//        add to animals
//    add to canvas
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
    public int getAge(){
        return this.age;
    }

}