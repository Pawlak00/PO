package org.myproject;

public enum MapDirection {
    EAST,
    NORTH,
    SOUTH,
    WEST,
    NORTH_WEST,
    NORTH_EAST,
    SOUTH_WEST,
    SOUTH_EAST;
    public String toString() {
        switch (this){
            case EAST: return "wschod";
            case WEST: return "zachod";
            case NORTH: return "północ";
            case SOUTH: return "poludnie";
            case NORTH_EAST: return "polnocny wschod";
            case NORTH_WEST: return "polnocny zachod";
            case SOUTH_EAST: return "poludniowy wschod";
            case SOUTH_WEST: return "poludniowy zachod";
            default: return "argh";
        }
    }
    public MapDirection next() {
        switch (this){
            case EAST: return SOUTH_EAST;
            case SOUTH_EAST: return SOUTH;
            case SOUTH: return SOUTH_WEST;
            case SOUTH_WEST: return WEST;
            case WEST: return NORTH_WEST;
            case NORTH_WEST: return NORTH;
            case NORTH: return NORTH_EAST;
            case NORTH_EAST: return EAST;
            default: return WEST;
        }
    }
    public MapDirection previous() {
        return this.next().next().next().next().next().next().next();
    }
    public Vector2d toUnitVector() {
        switch (this){
            case EAST: return new Vector2d(1,0);
            case WEST: return new Vector2d(-1,0);
            case NORTH: return new Vector2d(0,1);
            case SOUTH: return new Vector2d(0,-1);
            case NORTH_EAST: return new Vector2d(1,1);
            case NORTH_WEST: return new Vector2d(-1,1);
            case SOUTH_EAST: return new Vector2d(1,-1);
            case SOUTH_WEST: return new Vector2d(-1,-1);
            default: return new Vector2d(0,0);
        }
    }
}
