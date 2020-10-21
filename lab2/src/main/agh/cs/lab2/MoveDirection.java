package agh.cs.lab2;

public enum MoveDirection {
    FORWARD,
    BACKWARD,
    RIGHT,
    LEFT;
    public String toString(){
        switch (this){
            case FORWARD: return "f";
            case BACKWARD: return "b";
            case RIGHT: return "r";
            case LEFT: return "l";
            default: return "argh";
        }
    }
}
