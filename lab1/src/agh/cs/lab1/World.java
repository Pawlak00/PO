package agh.cs.lab1;

public class World {
    public static void run(Directions[] dir){
        System.out.print("going forward\n");
        for(int i=0;i<dir.length;i++){
            switch(dir[i]){
                case FORWARD:
                    System.out.print("ide w przod\n");
                    break;
                case BACKWARD:
                    System.out.print("ide w tyl\n");
                    break;
                case LEFT:
                    System.out.print("ide w lewo\n");
                    break;
                case RIGHT:
                    System.out.print("ide w prawo\n");
                    break;
            }
        }
    }
    public static Directions[] convert_args(String[] directions){
        Directions[] ans=new Directions[directions.length];
        for(int i=0;i<directions.length;i++){
            switch(directions[i]){
                case "f":
                    ans[i]=Directions.FORWARD;
                    break;
                case "b":
                    ans[i]=Directions.BACKWARD;
                    break;
                case "l":
                    ans[i]=Directions.LEFT;
                    break;
                case "r":
                    ans[i]=Directions.RIGHT;
                    break;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.print("begin\n");
        Directions[] ans=convert_args(args);
        run(ans);
        System.out.print("end");
    }
}
