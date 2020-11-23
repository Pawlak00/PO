package agh.cs.lab2;

import java.util.Comparator;

public class YComparator implements Comparator<IMapElement> {
    @Override
    public int compare(IMapElement a,IMapElement b){
        Vector2d pa=a.getPosition();
        Vector2d pb=b.getPosition();
        if(pa.y!=pb.y){
            return pa.y-pb.y;
        }else if(pa.x!=pb.x){
            return pa.x-pb.x;
        }else{
            if(a instanceof Animal && b instanceof Grass){
                return -1;
            }
        }
        return 0;
    }
}
