package org.myproject;

public enum MoveDirection {
    FORWARD,
    BACKWARD,
    RIGHT,
    LEFT,
    LEFT_FORWARD,
    LEFT_BACKWARD,
    RIGHT_FORWARD,
    RIGHT_BACKWARD;
    public String toString(){
        switch (this){
            case FORWARD: return "f";
            case BACKWARD: return "b";
            case RIGHT: return "r";
            case LEFT: return "l";
            case LEFT_BACKWARD: return "lb";
            case LEFT_FORWARD: return "lf";
            case RIGHT_FORWARD: return "rf";
            case RIGHT_BACKWARD: return "rb";
            default: return "argh";
        }
    }
}
