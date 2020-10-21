package agh.cs.lab2;

public class OptionsParser {
    public MoveDirection[] parse(String[] arr){
        MoveDirection[] directions=new MoveDirection[arr.length];
        for(int i=0;i< arr.length;i++){
            switch(arr[i]) {
                case "forward":
                    directions[i]=MoveDirection.FORWARD;
                    break;
                case "f":
                    directions[i]=MoveDirection.FORWARD;
                    break;
                case "backward":
                    directions[i]=MoveDirection.BACKWARD;
                    break;
                case "b":
                    directions[i]=MoveDirection.BACKWARD;
                    break;
                case "left":
                    directions[i]=MoveDirection.LEFT;
                    break;
                case "l":
                    directions[i]=MoveDirection.LEFT;
                    break;
                case "right":
                    directions[i]=MoveDirection.RIGHT;
                    break;
                case "r":
                    directions[i]=MoveDirection.RIGHT;
                    break;
                default:
                    throw new IllegalStateException("ERROR bad luck");
            }
        }
        return directions;
    }
}
