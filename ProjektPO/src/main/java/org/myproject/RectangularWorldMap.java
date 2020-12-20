package org.myproject;
import java.util.*;
import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class RectangularWorldMap implements IPositionChangeObserver {
    private int mapWidth;
    public int mapHeight;
    public int startEnergy;
    public int moveEnergy;
    public int plantEnergy;
    public int jungleRatio;
    public MapLord mapLord;
    public SortedSetMultimap<Vector2d, Animal> Animals;
    public Map<Vector2d,Plant> Plants;
    public Map<Vector2d,Vector2d>availableFields;
    private Pane canvas;
    public RectangularWorldMap(WorldDescription mapDesc,Pane canvas){
        this.canvas=canvas;
        this.mapWidth=mapDesc.mapWidth;
        this.mapHeight=mapDesc.mapHeight;
        this.startEnergy=mapDesc.startEnergy;
        this.moveEnergy=mapDesc.moveEnergy;
        this.plantEnergy=mapDesc.plantEnergy;
        this.jungleRatio= (int) mapDesc.jungleRatio;
        this.Animals=TreeMultimap.create(
                Comparator.comparing(Vector2d::getX),
                Comparator.comparing(Animal::getEnergyLevel).reversed());
        this.Plants=new HashMap<>();
        this.availableFields=new HashMap<>();
        for(int i=0;i<mapWidth;i++){
            for(int j=0;j<mapHeight;j++){
                this.availableFields.put(new Vector2d(i,j),new Vector2d(i,j));
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
        Animals.remove(oldAnimal.getPosition(),oldAnimal);
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
        List<Vector2d>animalsPositions=new ArrayList<>(Animals.keys());
        for(Vector2d pos: animalsPositions){
            List<Animal>animalsAtPosition=new ArrayList<>(Animals.get(pos));
            for(Animal a:animalsAtPosition){
                if(a.getEnergyLevel()<=0){
                    a.representation.setFill(Color.TRANSPARENT);
                    Animals.remove(a.getPosition(),a);
                }
            }
        }
    }
    public void breedAnimals() {
        List<Vector2d>animalsPositions=new ArrayList<>(Animals.keys());
        for(Vector2d pos:animalsPositions){
            List<Animal>animalsAtPosition=new ArrayList<>(Animals.get(pos));
            if(animalsAtPosition.size()>=2 && availableFields.size()>0 && animalsAtPosition.get(0).getEnergyLevel()>0.5*this.startEnergy && animalsAtPosition.get(1).getEnergyLevel()>0.5*this.startEnergy) {
                Animal kid = new Animal(animalsAtPosition.get(0), animalsAtPosition.get(1));
                this.place(kid);
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
        List<Vector2d>animalsPositions=new ArrayList<>(Animals.keys());
        for(Vector2d pos:animalsPositions){
            List<Animal>animalsAtPosition=new ArrayList<>(Animals.get(pos));
            for(Animal a:animalsAtPosition){
                MapDirection dir=RandomGetter.GetRandomMapDir(a.getGenes());
                a.move(dir);
            }
        }
    }

    public void feedAnimals() {
        if(this.Animals.isEmpty()){
            return;
        }
        List<Vector2d>animalsPositions=new ArrayList<>(Animals.keys());
        for(Vector2d pos: animalsPositions){
            List<Animal>animalsAtPosition=new ArrayList<>(Animals.get(pos));
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
                plant.representation.setFill(Color.TRANSPARENT);
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
    }
    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public int getStartEnergy() {
        return startEnergy;
    }

    public void setStartEnergy(int startEnergy) {
        this.startEnergy = startEnergy;
    }

    public int getMoveEnergy() {
        return moveEnergy;
    }

    public void setMoveEnergy(int moveEnergy) {
        this.moveEnergy = moveEnergy;
    }

    public int getPlantEnergy() {
        return plantEnergy;
    }

    public void setPlantEnergy(int plantEnergy) {
        this.plantEnergy = plantEnergy;
    }

    public int getJungleRatio() {
        return jungleRatio;
    }

    public void setJungleRatio(int jungleRatio) {
        this.jungleRatio = jungleRatio;
    }

    public MapLord getMapLord() {
        return mapLord;
    }

    public void setMapLord(MapLord mapLord) {
        this.mapLord = mapLord;
    }

    public SortedSetMultimap<Vector2d, Animal> getAnimals() {
        return Animals;
    }

    public void setAnimals(SortedSetMultimap<Vector2d, Animal> animals) {
        Animals = animals;
    }

    public Map<Vector2d, Plant> getPlants() {
        return Plants;
    }

    public void setPlants(Map<Vector2d, Plant> plants) {
        Plants = plants;
    }

    public Map<Vector2d, Vector2d> getAvailableFields() {
        return availableFields;
    }

    public void setAvailableFields(Map<Vector2d, Vector2d> availableFields) {
        this.availableFields = availableFields;
    }

    public Pane getCanvas() {
        return canvas;
    }

    public void setCanvas(Pane canvas) {
        this.canvas = canvas;
    }
}
