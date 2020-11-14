package agh.cs.lab2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.*;

public class GrassField extends AbstractWorldMap{
    private int n;
    protected List<Grass> grass;
    public GrassField(int n){
        Random rand=new Random();
        this.grass= new ArrayList<>();
//        System.out.println("tworze nowe pole trawy");
        this.n=n;
//        this.grass.add(new Grass(new Vector2d(0,0)));
        for(int i=0;i<n;i++){
            int x=rand.nextInt((int)sqrt(10*n));
            int y=rand.nextInt((int)sqrt(10*n));
            Vector2d v=new Vector2d(x,y);
//            System.out.println(x+", "+y);
            this.min_x=min(this.min_x,x);
            this.min_y=min(this.min_y,y);
            this.max_x=max(this.max_x,x);
            this.max_y=max(this.max_y,y);
            this.grass.add(new Grass(v));
        }
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        if(this.isOccupied(position) && this.objectAt(position) instanceof Animal){
            return false;
        }else{
            return true;
        }
    }
    @Override
    public void getAnimals(){
        super.getAnimals();
    }
    @Override
    public boolean place(Animal animal) {
        return super.place(animal);
    }
    @Override
    public void run(MoveDirection[] directions) {
//        System.out.println("ruszam zwierzatkami z GrassField");
        super.run(directions);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if(animals.containsKey(position)){
            return animals.get(position);
        }
        for (Grass g:grass){
            if(g.getPosition().equals(position)){
                return g;
            }
        }
        return null;
    }
    public String toString(){
        return super.toString();
    }
}
