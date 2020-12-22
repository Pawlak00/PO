package org.myproject;

import java.util.Random;

public class RandomGetter {
    public static int getRandom(int upperBound){
        Random rd=new Random();
        return rd.nextInt(upperBound);
    }
    static public MapDirection getRandomMapDir(Genotype genes){
        Random rd=new Random();
        int dir= genes.getGeneCode().get(rd.nextInt(genes.getGeneCode().size()));
        MapDirection[] names=MapDirection.values();
        return names[dir];
    }
}
