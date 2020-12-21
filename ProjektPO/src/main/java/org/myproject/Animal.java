package org.myproject;

import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.w3c.dom.events.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement{
    private Vector2d location;
    private RectangularWorldMap map;
    private List<IPositionChangeObserver> observers;
    private int energyLevel;
    private Genotype genes;
    private AnimalRepresentation representation;
    private int age;
    private Pane canvas;
    private List<Animal>kids;
    public AnimalsAncestors ancestors;
    private Animal(){
        this.observers=new ArrayList<>();
        this.age=0;
        this.ancestors=new AnimalsAncestors();
    }
    EventHandler<javafx.scene.input.MouseEvent>eventHandler=new EventHandler<javafx.scene.input.MouseEvent>() {
        @Override
        public void handle(javafx.scene.input.MouseEvent mouseEvent) {
            System.out.println("kliklem");
        }
    };
    public Animal(RectangularWorldMap map, Vector2d initialPosition, int energyLevel, Pane canvas){
        this();
        this.canvas=canvas;
        this.map=map;
        this.location = initialPosition;
        this.energyLevel=energyLevel;
        this.genes=new Genotype(32);
        this.representation=new AnimalRepresentation(this);
        map.place(this);
    }
    public Animal(Animal parent1,Animal parent2){
        this();
        System.out.println(parent1+" "+parent2);
        this.map=parent1.map;
        parent1.getAncestors().addKid(this);
        parent2.getAncestors().addKid(this);
        this.location=this.map.getLocForKid(parent1.getPosition());
        this.energyLevel= (int) (0.25*(parent2.getEnergyLevel()+ parent1.getEnergyLevel()));
        parent1.energyLevel-=0.25*parent1.energyLevel;
        parent2.energyLevel-=0.25* parent2.energyLevel;
        this.genes=new Genotype(parent1.getGenes(),parent2.getGenes(), parent1.getGenes().getGeneCode().length);
        this.canvas=parent1.canvas;
        this.representation=new AnimalRepresentation(this);
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
        this.representation.removeAnimalRepresentation();
        this.map.availableFields.put(this.getPosition(),this.getPosition());
        if(this.map.canMoveTo(this.location.add(direction.toUnitVector(),new Vector2d(this.map.getMapWidth(),this.map.mapHeight)))){
            Vector2d tmp=new Vector2d(this.location.x,this.location.y);
            this.location=this.location.add(direction.toUnitVector(),new Vector2d(this.map.getMapWidth(),this.map.mapHeight));
        }
        this.energyLevel-=this.map.moveEnergy;
        this.representation.moveTo(this.location);
        this.map.availableFields.put(this.getPosition(),this.getPosition());
        this.positionChanged(this,this);
    }
    void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }
    void positionChanged(Animal oldAnimal,Animal newAnimal){
        for(IPositionChangeObserver observer:this.observers){
            observer.positionChanged(oldAnimal,newAnimal);
        }
    }
    public int getAge(){
        return this.age;
    }
    public AnimalRepresentation getRepresentation(){
        return this.representation;
    }
    public Pane getCanvas() {
        return this.canvas;
    }
    public RectangularWorldMap getMap() {
        return this.map;
    }
    public AnimalsAncestors getAncestors(){
        return this.ancestors;
    }
}