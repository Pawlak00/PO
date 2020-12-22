package org.myproject;
import java.util.*;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import javafx.scene.layout.Pane;

public class RectangularWorldMap implements IPositionChangeObserver {
    private final int mapWidth;
    private Multimap<List<Integer>,Animal> Genotypes;
    private int mapHeight;
    private int startEnergy;
    private int moveEnergy;
    private int plantEnergy;
    private double jungleRatio;
    private Multimap<Vector2d,Animal> Animals;
    private Map<Vector2d,Plant> Plants;
    private Map<Vector2d,Vector2d>availableFields;
    private final Pane canvas;
    private final MapStatistics statistics;
    private final Jungle jungle;
    public RectangularWorldMap(WorldDescription mapDesc,Pane canvas){
        this.statistics=new MapStatistics(this);
        this.canvas=canvas;
        this.mapWidth=mapDesc.mapWidth;
        this.mapHeight=mapDesc.mapHeight;
        this.startEnergy=mapDesc.startEnergy;
        this.moveEnergy=mapDesc.moveEnergy;
        this.plantEnergy=mapDesc.plantEnergy;
        this.jungleRatio=mapDesc.jungleRatio;
        this.jungle=new Jungle(JungleDimensionFinder.findDimensions((int)(jungleRatio*mapWidth*mapHeight)),this);
        this.Animals= ArrayListMultimap.create();
        this.Genotypes=ArrayListMultimap.create();
        this.Plants=new HashMap<>();
        this.availableFields=new HashMap<>();
        for(int i=0;i<mapWidth;i++){
            for(int j=0;j<mapHeight;j++){
                if(!this.jungle.belongsToJungle(new Vector2d(i,j))){
                    this.availableFields.put(new Vector2d(i,j),new Vector2d(i,j));
                }
            }
        }
    }
    public void addRandomAnimal(){
        Random rd=new Random();
        Vector2d pos=new Vector2d(rd.nextInt(this.mapWidth),rd.nextInt(this.mapHeight));
        Animal animal=new Animal(this,pos,startEnergy,this.canvas);
    }
    @Override
    public void positionChanged(Animal oldAnimal, Animal newAnimal) {
        Animals.put(newAnimal.getPosition(),newAnimal);
        if(!Animals.containsKey(oldAnimal.getPosition())){
            this.availableFields.put(oldAnimal.getPosition(),oldAnimal.getPosition());
        }
        this.availableFields.remove(newAnimal.getPosition());
    }
    public void addRandomPlant(){
        Random rd=new Random();
        Vector2d pos=new Vector2d(rd.nextInt(this.mapWidth),rd.nextInt(this.mapHeight));
        if(!Animals.containsKey(pos)) {
            Plant plant = new Plant(this,pos, plantEnergy,this.canvas);
            this.Plants.put(pos, plant);
        }
    }
    @Override
    public String toString() {
        return "RectangularMap{" +
                "mapWidth=" + mapWidth +
                ", mapHeight=" + mapHeight +
                ", startEnergy=" + startEnergy +
                ", moveEnergy=" + moveEnergy +
                ", plantEnergy=" + plantEnergy +
                ", jungleRatio=" + jungleRatio +
                '}';
    }
    public void place(Animal animal) {
        if(!this.canMoveTo(animal.getPosition())) {
            throw new IllegalArgumentException("Position is out of range");
        }else{
            this.Animals.put(animal.getPosition(), animal);
            this.availableFields.remove(animal.getPosition());
            animal.addObserver(this);
        }
    }
    public boolean canMoveTo(Vector2d position) {
        return position.x< this.mapWidth && position.x>=0 && position.y>=0 && position.y< this.mapHeight;
    }
    public void deleteDeadAnimals(){
        if(this.Animals.isEmpty()){
            return;
        }
        List<Vector2d>animalsPositions=new ArrayList<>(Animals.keySet());
        for(Vector2d pos: animalsPositions){
            List<Animal>animalsAtPosition=new ArrayList<>(Animals.get(pos));
            for(Animal a:animalsAtPosition){
                if(a.isDead()){
                    a.removeAnimalFromMap();
                }
            }
        }
    }
    public void breedAnimals(int era) {
        List<Vector2d>animalsPositions=new ArrayList<>(Animals.keySet());
        for (Vector2d animalsPosition : animalsPositions) {
            List<Animal> animalsAtPosition = new ArrayList<>(Animals.get(animalsPosition));
            animalsAtPosition.sort(Comparator.comparingInt(Animal::getEnergyLevel).reversed());
            if(animalsAtPosition.size() >= 2 && availableFields.size() > 0) {
                Animal parent1 = animalsAtPosition.get(0);
                Animal parent2 = animalsAtPosition.get(1);
                if (parent1.canBreed() && parent2.canBreed()) {
                    Animal kid = new Animal(animalsAtPosition.get(0), animalsAtPosition.get(1));
                    if (parent1.isDead()) {
                        parent1.setDeathAge(era);
                    }
                    if (parent2.isDead()) {
                        parent2.setDeathAge(era);
                    }
                }
            }
        }
    }
    public Vector2d getLocForKid(Vector2d pos){
        Vector2d neighbour=pos;
        for(MapDirection vec:MapDirection.values()){
            neighbour=neighbour.add(vec.toUnitVector(),new Vector2d(this.mapWidth,this.mapHeight));
            if(availableFields.containsKey(neighbour)){
                return neighbour;
            }
        }
        List<Vector2d>availableFieldsList=new ArrayList<>(availableFields.values());
        if(availableFields.size()>0) {
            return availableFieldsList.get(RandomGetter.getRandom(availableFields.size()));
        }
        return null;
    }

    public void rotateAndMoveAnimals(int era) {
        List<Vector2d>animalsPositions=new ArrayList<>(Animals.keySet());
        for(Vector2d pos:animalsPositions){
            List<Animal>animalsAtPosition=new ArrayList<>(Animals.get(pos));
            for(Animal a:animalsAtPosition){
                MapDirection dir=RandomGetter.getRandomMapDir(a.getGenes());
                a.move(dir);
                if(a.isDead()){
                    a.setDeathAge(era);
                }
            }
        }
    }

    public void feedAnimals() {
        if(this.Animals.isEmpty()){
            return;
        }
        List<Vector2d>animalsPositions=new ArrayList<>(Animals.keySet());
        for(Vector2d pos: animalsPositions){
            List<Animal>animalsAtPosition=new ArrayList<>(Animals.get(pos));
            animalsAtPosition.sort(Comparator.comparingInt(Animal::getEnergyLevel).reversed());
            int maxEnergyLevel=animalsAtPosition.get(0).getEnergyLevel();
            int maxCounter=0;
            for(Animal a:animalsAtPosition) {
                if (a.getEnergyLevel() == maxEnergyLevel) {
                    maxCounter++;
                }
            }
            if(this.Plants.containsKey(pos)) {
                int foodAmount = this.Plants.get(pos).plantEnergy / maxCounter;
                Plant plant=this.Plants.get(pos);
                plant.removeFromMap();
                for (Animal a : animalsAtPosition) {
                    if (a.getEnergyLevel() == maxEnergyLevel) {
                        a.setEnergyLevel(a.getEnergyLevel() + foodAmount);
                    }
                }
            }
        }
    }
    public void addPlants() {
        this.addRandomPlant();
        this.jungle.addRandomPlant();
    }
    public int getMapWidth() {
        return mapWidth;
    }
    public Multimap<Vector2d, Animal> getAnimals() {
        return Animals;
    }
    public Jungle getJungle() {
        return jungle;
    }
    public Pane getCanvas() {
        return canvas;
    }
    public MapStatistics getStatistics() {
        return statistics;
    }
    public int getMapHeight() {
        return this.mapHeight;
    }
    public void removeFromAnimals(Animal animal){
        this.Animals.remove(animal.getPosition(),animal);
    }
    public int getStartEnergy(){
        return this.startEnergy;
    }
    public int getMoveEnergy(){
        return this.moveEnergy;
    }
    public int getPlantEnergy(){
        return this.plantEnergy;
    }
    public void addPlant(Plant plant){
        this.Plants.put(plant.getPosition(),plant);
    }
    public void removePlant(Plant plant){
        this.Plants.remove(plant.getPosition());
    }
    public Map<Vector2d,Plant> getPlants(){
        return this.Plants;
    }
    public void addAvailableField(Vector2d pos){
        this.availableFields.put(pos,pos);
    }
    public Multimap<List<Integer>, Animal> getGenotypes(){
        return this.Genotypes;
    }
    public void addGenotypeToCounter(Animal animal){
        this.Genotypes.put(animal.getGenes().getGeneCode(), animal);
    }

}
