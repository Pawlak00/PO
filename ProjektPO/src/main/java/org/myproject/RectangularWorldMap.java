package org.myproject;

import java.util.*;

import com.google.common.collect.ArrayListMultimap;

import com.google.common.collect.HashMultimap;

import com.google.common.collect.Multimap;

import com.google.common.collect.SortedSetMultimap;

import com.google.common.collect.TreeMultimap;
import static java.lang.Math.abs;

public class RectangularWorldMap implements IPositionChangeObserver {
    public int mapWidth;
    public int mapHeight;
    public int startEnergy;
    public int moveEnergy;
    public int plantEnergy;
    public int jungleRatio;
    public MapLord mapLord;
    public SortedSetMultimap<Vector2d, Animal> Animals;
    public Map<Vector2d,Plant> Plants;
    public Map<Vector2d,Vector2d>availableFields;
    public RectangularWorldMap(WorldDescription mapDesc){
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
        Animal animal=new Animal(this,pos,startEnergy);
        this.place(animal);
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
            Plant plant = new Plant(pos, plantEnergy);
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
            System.out.println(animal.getPosition().x+" "+animal.getPosition().y);
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
    public Object animalsAt(Vector2d position) {
        if(!Animals.get(position).isEmpty()) {
            if (Animals.containsKey(position)) {
                return Animals.get(position);
            } else {
                return null;
            }
        }
        return null;
    }
    public Object plantAt(Vector2d position){
        if(!Plants.isEmpty()) {
            if (Plants.containsKey(position)) {
                return Plants.get(position);
            } else {
                return null;
            }
        }
        return null;
    }
    public void deleteDeadAnimals(){
        if(this.Animals.isEmpty()){
            return;
        }
        List<Vector2d>animalsPositions=new ArrayList<>(Animals.keys());
//        System.out.println("usuwam zdechle");
        for(Vector2d pos: animalsPositions){
            List<Animal>animalsAtPosition=new ArrayList<>(Animals.get(pos));
            for(Animal a:animalsAtPosition){
                if(a.getEnergyLevel()<=0){
                    Animals.remove(a.getPosition(),a);
                }
            }
        }
    }
    public void breedAnimals() {
        List<Vector2d>animalsPositions=new ArrayList<>(Animals.keys());
//        System.out.println("Rozmnazanko");
        for(Vector2d pos:animalsPositions){
            List<Animal>animalsAtPosition=new ArrayList<>(Animals.get(pos));
//            System.out.println(Animals.get(pos));
            if(animalsAtPosition.size()>=2 && availableFields.size()>0 && animalsAtPosition.get(0).getEnergyLevel()>0.5*this.startEnergy && animalsAtPosition.get(1).getEnergyLevel()>0.5*this.startEnergy) {
                Animal kid = new Animal(animalsAtPosition.get(0), animalsAtPosition.get(1));
                this.place(kid);
            }
        }
    }
    public Vector2d getLocForKid(Vector2d pos){
        Vector2d neighbour=new Vector2d(0,0);
        for(MapDirection vec:MapDirection.values()){
            neighbour=neighbour.add(vec.toUnitVector(),new Vector2d(this.mapWidth,this.mapHeight));
            if(availableFields.containsKey(neighbour)){
                return neighbour;
            }
        }
        System.out.println("ilosc wolnych pol "+availableFields.size());
        List<Vector2d>availableFieldsList=new ArrayList<>(availableFields.values());
        if(availableFields.size()>0) {
            return availableFieldsList.get(RandomGetter.getRandom(availableFields.size()));
        }
        return null;
    }

    public void rotateAndMoveAnimals() throws CloneNotSupportedException {
//        System.out.println("ruszam zwierzakami");
        List<Vector2d>animalsPositions=new ArrayList<>(Animals.keys());
        for(Vector2d pos:animalsPositions){
            List<Animal>animalsAtPosition=new ArrayList<>(Animals.get(pos));
            for(Animal a:animalsAtPosition){

                a.move(RandomGetter.GetRandomMoveDir(a.getGenes()));
            }
        }
    }

    public void feedAnimals() {
        if(this.Animals.isEmpty()){
            return;
        }
        List<Vector2d>animalsPositions=new ArrayList<>(Animals.keys());
//        System.out.println("karmie zwierzeta");
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
                for (Animal a : animalsAtPosition) {
                    if (a.getEnergyLevel() == maxEnergyLevel) {
                        a.setEnergyLevel(a.getEnergyLevel() + foodAmount);
                    }
                }
            }
        }
    }

    public void addPlants(int nOfPlants) {
//        System.out.println("Dodaje roslinki");
        for(int i=0;i<nOfPlants;i++){
            this.addRandomPlant();
        }
    }
}
