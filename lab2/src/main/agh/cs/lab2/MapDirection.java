package agh.cs.lab2;

public enum MapDirection {
    EAST,
    NORTH,
    SOUTH,
    WEST;
    public String toString() {
        switch (this){
            case EAST: return "wschod";
            case WEST: return "zachod";
            case NORTH: return "północ";
            case SOUTH: return "poludnie";
            default: return "argh";
        }
    }
    public MapDirection next() {
        switch (this){
            case EAST: return SOUTH;
            case WEST: return NORTH;
            case NORTH: return EAST;
            case SOUTH: return WEST;
            default: return WEST;
        }
    }
    public MapDirection previous() {
        switch (this){
            case EAST: return NORTH;
            case WEST: return SOUTH;
            case NORTH: return WEST;
            case SOUTH: return EAST;
            default: return WEST;
        }
    }
    public Vector2d toUnitVector() {
        switch (this){
            case EAST: return new Vector2d(1,0);
            case WEST: return new Vector2d(-1,0);
            case NORTH: return new Vector2d(0,1);
            case SOUTH: return new Vector2d(0,-1);
            default: return new Vector2d(0,0);
        }
    }
}
