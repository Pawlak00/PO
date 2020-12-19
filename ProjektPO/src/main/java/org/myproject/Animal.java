package org.myproject;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Animal implements IMapElement,Cloneable{
    private MapDirection dir;
    private Vector2d location;
    private RectangularWorldMap map;
    private List<IPositionChangeObserver> observers;
    private int energyLevel;
    private Genotype genes;
    private Vector2d lastPosition;
    public Animal(RectangularWorldMap map){
        this(map,new Vector2d(0,2),0);
    }
    public Animal(RectangularWorldMap map,Vector2d initialPosition,int energyLevel){
        this.dir=RandomGetter.getRandomMapDir();
        this.map=map;
//        System.out.println(this.map.isOccupied(initialPosition));
        this.location = initialPosition;
        this.observers=new ArrayList<>();
        map.place(this);
        this.energyLevel=energyLevel;
        this.genes=new Genotype(32);
        this.lastPosition=new Vector2d(0,0);
    }
    public Animal(Animal parent1,Animal parent2){
        this.dir=RandomGetter.getRandomMapDir();
        this.map=parent1.map;
        this.location=this.map.getLocForKid(parent1.getPosition());
        this.observers=new ArrayList<>();
        this.energyLevel= (int) (0.25*(parent2.getEnergyLevel()+ parent1.getEnergyLevel()));
        parent1.energyLevel-=0.25*parent1.energyLevel;
        parent2.energyLevel-=0.25* parent2.energyLevel;
        map.place(this);
        this.genes=new Genotype(parent1.getGenes(),parent2.getGenes(), parent1.getGenes().getGeneCode().length);
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
    public void move(MoveDirection direction)  {
        Vector2d op=this.location;
        this.lastPosition=new Vector2d(this.getPosition().x,this.getPosition().y);
        try {
            Animal oldAnimal = (Animal) this.clone();
            if(this.map.canMoveTo(this.location.add(this.dir.toUnitVector(),new Vector2d(this.map.mapWidth,this.map.mapHeight)))){
                this.location=this.location.add(this.dir.toUnitVector(),new Vector2d(this.map.mapWidth,this.map.mapHeight));
            }
            this.positionChanged(this,oldAnimal);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

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
