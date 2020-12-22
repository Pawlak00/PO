package org.myproject;

import java.util.*;


public class Genotype {
    private int length;
    private List<Integer> genes;
    public Genotype(Genotype parent1,Genotype parent2,int length){
        this.length=length;
        this.genes=new ArrayList<>();
        GeneJoiner geneJoiner=new GeneJoiner(parent1, parent2);
        this.genes=geneJoiner.join(length);
    }
    public Genotype(int length){
        this.genes=new ArrayList<>();
        Random rd=new Random();
        for(int i=0;i<length;i++){
            this.genes.add(rd.nextInt(8));
        }
        Collections.sort(genes);
    }
    public String getGeneCodeString(){
        return this.genes.toString();
    }
    public List<Integer> getGeneCode() {
        return genes;
    }
}
