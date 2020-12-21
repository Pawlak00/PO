package org.myproject;
import java.util.*;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import javafx.scene.layout.Pane;

public class RectangularWorldMap implements IPositionChangeObserver {
    private final int mapWidth;
    public int mapHeight;
    public int startEnergy;
    public int moveEnergy;
    public int plantEnergy;
    public double jungleRatio;
    public MapLord mapLord;
    public Multimap<Vector2d,Animal> Animals;
    public Map<Vector2d,Plant> Plants;
    public Map<Vector2d,Vector2d>availableFields;
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
    public boolean place(Animal animal) {
        if(!this.canMoveTo(animal.getPosition())) {
            throw new IllegalArgumentException("Position is out of range");
        }else{
            this.Animals.put(animal.getPosition(), animal);
            this.availableFields.remove(animal.getPosition());
            animal.addObserver(this);
            return true;
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
                if(a.getEnergyLevel()<=0){
                    a.getRepresentation().removeAnimalRepresentation();
                    Animals.remove(a.getPosition(),a);
                }
            }
        }
    }
    public void breedAnimals() {
        List<Vector2d>animalsPositions=new ArrayList<>(Animals.keySet());
        for (Vector2d animalsPosition : animalsPositions) {
            List<Animal> animalsAtPosition = new ArrayList<>(Animals.get(animalsPosition));
            animalsAtPosition.sort(Comparator.comparingInt(Animal::getEnergyLevel).reversed());
            if (animalsAtPosition.size() >= 2 && availableFields.size() > 0 && animalsAtPosition.get(0).getEnergyLevel() > 0.5 * this.startEnergy
                    && animalsAtPosition.get(1).getEnergyLevel() > 0.5 * this.startEnergy) {
                Animal kid = new Animal(animalsAtPosition.get(0), animalsAtPosition.get(1));
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

    public void rotateAndMoveAnimals() {
        List<Vector2d>animalsPositions=new ArrayList<>(Animals.keySet());
        for(Vector2d pos:animalsPositions){
            List<Animal>animalsAtPosition=new ArrayList<>(Animals.get(pos));
            for(Animal a:animalsAtPosition){
                MapDirection dir=RandomGetter.getRandomMapDir(a.getGenes());
                a.move(dir);
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
                plant.representation.removeFromCanvas();
                this.Plants.remove(pos);
                for (Animal a : animalsAtPosition) {
                    if (a.getEnergyLevel() == maxEnergyLevel) {
                        a.setEnergyLevel(a.getEnergyLevel() + foodAmount);
                    }
                }
            }
        }
    }
    public void addPlants(int nOfPlants) {
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
    public double getMapHeight() {
        return this.mapHeight;
    }
}
