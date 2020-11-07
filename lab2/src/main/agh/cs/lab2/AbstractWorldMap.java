package agh.cs.lab2;

import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

abstract class AbstractWorldMap implements IWorldMap{
    protected List<Animal> animals;
    public int min_x=0;
    public int min_y=0;
    public int max_x=0;
    public int max_y=0;
    @Override
    public boolean place(Animal animal) {
        if(this.isOccupied(animal.getPosition()) && this.objectAt(animal.getPosition()) instanceof Animal){
            return false;
        }else{
            this.animals.add(animal);
            return true;
        }
    }

    @Override
    public void run(MoveDirection[] directions) {
        int n=animals.size();
        if(n!=0) {
            for (int i = 0; i < directions.length; i++) {
                Animal act_animal = animals.get(i % (n));
                act_animal.move(directions[i]);
                this.min_x=min(this.min_x,act_animal.getPosition().x);
                this.min_y=min(this.min_y,act_animal.getPosition().y);
                this.max_x=max(this.max_x,act_animal.getPosition().x);
                this.max_y=max(this.max_y,act_animal.getPosition().y);
            }
        }
    }

    @Override
    public void getAnimals() {
        for(Animal a:animals){
            System.out.println(a.toString()+a.getPosition().toString());
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return (this.objectAt(position)!=null);
    }

    @Override
    public String toString() {
        MapVisualizer MapImage=new MapVisualizer(this);
//        System.out.println(this.min_x+" "+this.min_y+" "+this.max_x+" "+this.max_y);
        String res= MapImage.draw(new Vector2d(this.min_x, this.min_y), new Vector2d(this.max_x, this.max_y));
        return res;
    }
}
