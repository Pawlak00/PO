package agh.cs.lab2;

import java.util.Comparator;

public class XComparator implements Comparator<IMapElement> {
    public int compare(IMapElement a,IMapElement b){
        if(a.getPosition().x>b.getPosition().x){
            return  1;
        }else if(a.getPosition().x<b.getPosition().x){
            return -1;
        }else{
            if(a.getPosition().y<b.getPosition().y){
                return -1;
            }else if(a.getPosition().y>b.getPosition().y){
                return  1;
            }else if(a instanceof Animal && b instanceof Grass){
                return  1;
            }
        }
        return 0;
    }
}
