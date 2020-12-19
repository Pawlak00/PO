package org.myproject;

import java.util.Random;

public class RandomGetter {
    static public MapDirection getRandomMapDir(){
        Random rd=new Random();
        MapDirection[] names=MapDirection.values();
        return names[rd.nextInt(names.length)];
    }
    public static int getRandom(int upperBound){
        Random rd=new Random();
        return rd.nextInt(upperBound);
    }
    static public MoveDirection GetRandomMoveDir(Genotype genes){
        Random rd=new Random();
        int dir=genes.getGeneCode()[rd.nextInt(genes.getGeneCode().length)];
        MoveDirection[] names=MoveDirection.values();
        return names[dir];
    }
}
