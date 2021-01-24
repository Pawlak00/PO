package org.myproject1;

public class MazeSolverFactory {
    public MazeSolver getSolver(String algorithmName) {
        if(algorithmName.equalsIgnoreCase("BFS")){
            return new BfsSolver();
        }else if(algorithmName.equalsIgnoreCase("DFS")){
            return new DfsSolver();
        }else if(algorithmName.equalsIgnoreCase("A*")){
            return new ASolver();
        }
        return null;
    }
}
