/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rest.ProyectoServer.models;

import java.util.Arrays;
import java.util.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.rest.ProyectoServer.GAlgorith3.GAlgorithm;
/**
 *
 * @author cp
 */
@XmlRootElement(name="Chromosome")
//@XStreamAlias("Chromosome")
public class Chromosome {
    private boolean isFitnessChage=true;
    private int []genes;
    private int fitness=0;
    public Chromosome(int length){
        genes=new int[length];
    }
    
    public Chromosome(){
        
    }
    public Chromosome(int []genes){
        this.genes=genes;
        this.recalculateFitness();
    }
    public Chromosome initializeChromosome(){
        for (int x=0;x<genes.length;x++){
            if (Math.random()>=0.99){
                genes[x]=1;
            }
            else{
                    genes[x]=0;
            
            }
        }
        return this;
    }
    public int recalculateFitness(){
//        int n=0;
//        int numero=0;
//            int gene=0;
//            for(int i =0;i<Gladiador.totalgenes;i++){
//            for (int j=n;j<(Gladiador.genSize+n);j++){
//                   // System.err.println("Esto se corrio de "+n);
//                numero=(int)Gladiador.convertirBinarioADecimal(genes,0,genes.length)/2;
//                    
//                   }
//               }
//            return numero;
            
        int chromosomeFitness=0;
        for (int x=0;x<genes.length;x++){
            if (genes[x]==1){                
                chromosomeFitness+=1;
                if (this.genes[x]>Gladiador.totalgenes/2){
                    chromosomeFitness+=1;                
                }
            }
        }
        return chromosomeFitness;
    }
    
    
    
    @Override
    public String toString(){
        return Arrays.toString(this.genes);
    }
    @XmlElement
    public int getFitness(){
        if(isFitnessChage){
            fitness=recalculateFitness();
            isFitnessChage=false;
        }
        
        return this.fitness;
    }
    @XmlElement
    public int[] getGenes(){
        return genes;
    }
    
}
