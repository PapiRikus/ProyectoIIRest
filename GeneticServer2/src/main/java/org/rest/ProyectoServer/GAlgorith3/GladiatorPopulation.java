/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rest.ProyectoServer.GAlgorith3;

import java.util.Arrays;
import java.util.Comparator;
import org.rest.ProyectoServer.models.Chromosome;
import org.rest.ProyectoServer.models.Gladiador;

/**
 *
 * @author cp
 */
public class GladiatorPopulation {
    private Gladiador[]gladiator;
    int generacion;
    boolean poblacionIniciada=false;
  
    int Mutaciones=0;
    double MutationRate=0;
    int Iversiones=0;

   
   
    public GladiatorPopulation(int legth,boolean PIniciada){
        
        gladiator=new Gladiador[legth];
        this.poblacionIniciada=PIniciada;
        
        if (poblacionIniciada=true)
        this.initializePopulation();
    }
     public GladiatorPopulation(int legth){
        
        gladiator=new Gladiador[legth];
        if(poblacionIniciada==false){
            this.initializePopulation();
        }
   
     }
    public GladiatorPopulation initializePopulation(){
        for (int x=0;x<gladiator.length;x++){
            
            gladiator[x]=new Gladiador(new Chromosome(GAlgorithm.TARGET_CHromosome.length)
                    .initializeChromosome());
            
        }
        this.setPoblacionIniciada(true);
        sortGladiatorByFitness();
        return this;
    }
     public GladiatorPopulation juntarPoblaciones(GladiatorPopulation popu1,GladiatorPopulation popu2){
        GladiatorPopulation mezcladePoblaciones=new GladiatorPopulation(popu1.getGladiator().length+popu2.getGladiator().length);
        // System.out.println(mezcladePoblaciones.getChromosomes().length);
        for (int i=0;i<popu1.getGladiator().length;i++){
            mezcladePoblaciones.getGladiator()[i]=popu1.getGladiator()[i];
        }
        
        for (int j=popu1.getGladiator().length;j<mezcladePoblaciones.getGladiator().length;j++){
        mezcladePoblaciones.getGladiator()[j]=popu2.getGladiator()[j-popu2.getGladiator().length];
        }
        return mezcladePoblaciones;
    }
     public void sortGladiatorByFitness(){
        Arrays.sort(this.gladiator,(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int flag=0;
                Gladiador ch1=(Gladiador)o1;
                Gladiador ch2=(Gladiador)o2;
                
                if(ch1.getChromosome().getFitness()<ch2.getChromosome().getFitness()){
                    flag=1;
                }
                if (ch1.getChromosome().getFitness()>ch2.getChromosome().getFitness()){
                    flag=-1;
                }
                
                
                return flag;
            }
            
            
        })
            
        );
        
    
    }
     
    public Gladiador[] getGladiator() {
        return gladiator;
    }

    public void setGladiator(Gladiador[] gladiator) {
        this.gladiator = gladiator;
    }

    public int getGeneracion() {
        return generacion;
    }

    public void setGeneracion(int generacion) {
        this.generacion = generacion;
    }

    public boolean isPoblacionIniciada() {
        return poblacionIniciada;
    }
    public void setPoblacionIniciada(boolean poblacionIniciada) {
        this.poblacionIniciada = poblacionIniciada;
    }
    public int getMutaciones() {
        return Mutaciones;
    }

    public void setMutaciones(int Mutaciones) {
        this.Mutaciones = Mutaciones;
    }

    public double getMutationRate() {
        return MutationRate;
    }

    public void setMutationRate(double MutationRate) {
        this.MutationRate = MutationRate;
    }
     public int getIversiones() {
        return Iversiones;
    }

    public void setIversiones(int Iversiones) {
        this.Iversiones = Iversiones;
    }
}
