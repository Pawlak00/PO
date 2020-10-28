package agh.cs.lab2;
import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {
    private int width;
    private int height;
    private List<Animal> animals = new ArrayList<Animal>();
    private Animal Map[][];
    public RectangularMap(int width,int height){
        this.width=width;
        this.height=height;
        this.Map=new Animal[this.width+1][this.height+1];
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return (position.x<=width && position.x>=0 && position.y>=0 && position.y<=height && this.isOccupied(position)==false);
    }

    @Override
    public boolean place(Animal animal) {
        if(!this.isOccupied(animal.getPosition())){
            animals.add(animal);
            Map[animal.getPosition().x][animal.getPosition().y]=animal;
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void run(MoveDirection[] directions) {
       int n=animals.size();
       for(int  i=0;i<directions.length;i++){
            Animal act_animal=animals.get(i%(n));
//            System.out.println(" rusza sie zwierze "+(i%n)+" w kierunku "+directions[i].toString()+" z pozycji "+act_animal.getPosition().x+" "+act_animal.getPosition().y);
            act_animal.move(directions[i]);
        }
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        if(objectAt(position)==null){
            return false;
        }
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if(this.Map[position.x][position.y]==null){
            return null;
        }
        return this.Map[position.x][position.y];
    }
    public String toString(){
        MapVisualizer MapImage=new MapVisualizer(this);
        String res= MapImage.draw(new Vector2d(0, 0), new Vector2d(this.width, this.height));
        return res;
    }
}
