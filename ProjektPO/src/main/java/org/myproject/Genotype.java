package org.myproject;

import java.util.Arrays;
import java.util.Random;
import java.util.SplittableRandom;

public class Genotype {
    private int length;
    private int[] genes;
    public Genotype(Genotype parent1,Genotype parent2,int length){
        this.genes=new int[length];
        GeneJoiner geneJoiner=new GeneJoiner(parent1, parent2);
        this.genes=geneJoiner.join(length);
    }
    public Genotype(int length){
        this.genes=new int[length];
        Random rd=new Random();
        for(int i=0;i<length;i++){
            this.genes[i]=rd.nextInt(8);
        }
        Arrays.sort(genes);
    }

    public int[] getGeneCode() {
        return genes;
    }
}
