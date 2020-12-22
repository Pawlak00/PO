package org.myproject;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement{
    private Vector2d location;
    private RectangularWorldMap map;
    private final List<IPositionChangeObserver> observers;
    private int energyLevel;
    private Genotype genes;
    private AnimalRepresentation representation;
    private int age;
    private Pane canvas;
    private int deathAge;
    public AnimalsAncestors ancestors;
    private Animal(){
        this.deathAge=0;
        this.age=0;
        this.observers=new ArrayList<>();
        this.ancestors=new AnimalsAncestors();
    }
    public Animal(RectangularWorldMap map, Vector2d location, int energyLevel, Pane canvas){
        this();
        this.canvas=canvas;
        this.map=map;
        this.location = location;
        this.energyLevel=energyLevel;
        this.genes=new Genotype(32);
        this.representation=new AnimalRepresentation(this,this.map.getStartEnergy());
        this.map.addGenotypeToCounter(this);
        map.place(this);
    }
    public Animal(Animal parent1,Animal parent2){
        this();
        this.map=parent1.map;
        parent1.getAncestors().addKid(this);
        parent2.getAncestors().addKid(this);
        this.location=this.map.getLocForKid(parent1.getPosition());
        this.energyLevel= (int) (0.25*(parent2.getEnergyLevel()+ parent1.getEnergyLevel()));
        parent1.energyLevel-=0.25*parent1.energyLevel;
        parent2.energyLevel-=0.25* parent2.energyLevel;
        this.genes=new Genotype(parent1.getGenes(),parent2.getGenes(), parent1.getGenes().getGeneCode().size()-1);
        this.map.addGenotypeToCounter(this);
        this.canvas=parent1.canvas;
        this.representation=new AnimalRepresentation(this,this.map.getStartEnergy());
        map.place(this);
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
        this.map.removeFromAnimals(this);
        this.representation.removeAnimalRepresentation();
        if(this.map.canMoveTo(this.location.add(direction.toUnitVector(),new Vector2d(this.map.getMapWidth(), this.map.getMapHeight())))){
            this.location=this.location.add(direction.toUnitVector(),new Vector2d(this.map.getMapWidth(),this.map.getMapWidth()));
        }
        this.setEnergyLevel(this.energyLevel-this.map.getMoveEnergy());
        this.representation.moveTo(this.location);
        this.map.addAvailableField(this.getPosition());
        this.positionChanged(this,this);
    }
    void positionChanged(Animal oldAnimal,Animal newAnimal){
        for(IPositionChangeObserver observer:this.observers){
            observer.positionChanged(oldAnimal,newAnimal);
        }
    }
    public void removeAnimalFromMap(){
        this.map.getStatistics().addDeadAnimal(this);
        this.map.removeFromAnimals(this);
        this.representation.removeAnimalRepresentation();
        this.map.getGenotypes().remove(this.genes.getGeneCode(),this);
    }
    public int getDeathAge(){ return this.deathAge; }
    public boolean canBreed(){
        return this.energyLevel>this.map.getStartEnergy()*0.5;
    }
    public void setDeathAge(int age){
        this.deathAge=age;
    }
    void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }
    public boolean isDead(){
        return  this.energyLevel<=0;
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
    public int getEnergyLevel() {
        return energyLevel;
    }
    public Genotype getGenes(){
        return this.genes;
    }
    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

}