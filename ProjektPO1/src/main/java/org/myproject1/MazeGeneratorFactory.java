package org.myproject1;

public class MazeGeneratorFactory {
    public MazeGenerator getGenerator(String MazeGenName,Vector2d dimensions){
        if(MazeGenName.equalsIgnoreCase("RECURSIVE_DIVISION")){
            return new RecursiveDivision();
        }else if(MazeGenName.equalsIgnoreCase("DfsGenerator")){
            return new DfsGenerator();
        }else if(MazeGenName.equalsIgnoreCase("RecursiveBacktracker")){
            return new RecursiveDivisionHorizontal();
        }
        return null;
    }
}
