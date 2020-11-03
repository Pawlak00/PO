package agh.cs.lab2;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public MoveDirection[] parse(String[] arr){
        int iter=0;
        List<MoveDirection> directions = new ArrayList<MoveDirection>();
        for(int i=0;i< arr.length;i++){
            switch(arr[i]) {
                case "forward":
                    directions.add(MoveDirection.FORWARD);
                    break;
                case "f":
                    directions.add(MoveDirection.FORWARD);
                    break;
                case "backward":
                    directions.add(MoveDirection.BACKWARD);
                    break;
                case "b":
                    directions.add(MoveDirection.BACKWARD);
                    break;
                case "left":
                    directions.add(MoveDirection.LEFT);
                    break;
                case "l":
                    directions.add(MoveDirection.LEFT);
                    break;
                case "right":
                    directions.add(MoveDirection.RIGHT);
                    break;
                case "r":
                    directions.add(MoveDirection.RIGHT);
                    break;

            }
        }
        MoveDirection[] arr1 = new MoveDirection[directions.size()];
        arr1 = directions.toArray(arr1);
        return arr1;
    }
}
