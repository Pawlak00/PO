package org.myproject;

public class JungleDimensionFinder {
    public static Vector2d findDimensions(int area){
        System.out.println(area);
        return new Vector2d((int)Math.sqrt(area),(int)Math.sqrt(area));
    }
}
