package org.myproject;

import java.util.*;

public class GeneJoiner {
    Genotype g1;
    Genotype g2;
    public GeneJoiner(Genotype g1,Genotype g2){
        this.g1=g1;
        this.g2=g2;
    }
    public List<Integer> join(int length){
        List<Integer> ans=new ArrayList<>();
        int p1=RandomGetter.getRandom(length-2);
        int p2= (int) ((Math.random() * (length-1 - p1)) + p1);
        int arr1= RandomGetter.getRandom(2);
        int arr2= RandomGetter.getRandom(2);
        int arr3= RandomGetter.getRandom(2);
        for(int i=0;i<p1;i++){
            if(arr1==0) {
                ans.add(g1.getGeneCode().get(i));
            }else{
                ans.add(g2.getGeneCode().get(i));
            }
        }
        for(int i=p1;i<=p2;i++){
            if(arr2==0) {
                ans.add(g1.getGeneCode().get(i));
            }else{
                ans.add(g2.getGeneCode().get(i));
            }
        }
        for(int i=p2;i<length;i++){
            if(arr3==0) {
                ans.add(g1.getGeneCode().get(i));
            }else{
                ans.add(g2.getGeneCode().get(i));
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
