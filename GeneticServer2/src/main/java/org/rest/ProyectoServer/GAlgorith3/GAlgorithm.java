/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rest.ProyectoServer.GAlgorith3;

import org.rest.ProyectoServer.models.Chromosome;
import org.rest.ProyectoServer.models.Gladiador;


/**
 *
 * @author cp
 */
public class GAlgorithm {
    public static final int PopulationSize=8;
    
    public static final int[] TARGET_CHromosome={1,1,0,0,0,0,0,0,1,1,0,0,0,0,1,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,1,0,1,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1};
    
    private static final double Mutation_Rate=0.25;
    private static final double Inversion_Rate=0.05;
    
    public static final int Numb_Elite_Chromosomes=1;
    public static final int Tournament_Selection_size=2;

    public int Mutaciones=0;
    public int CrossOvers=0;
    public int Inversiones=0;

    public double SuperMutationRate;
     public GladiatorPopulation evolve(GladiatorPopulation population){
       // GladiatorPopulation popuAEvolcionada=mutatePopulation(crossoverPopulation(population));
      //  System.out.println(popuaux.getChromosomes().length+"    "+ popuAEvolcionada.getChromosomes().length);
        //GladiatorPopulation popuaux=population.juntarPoblaciones(population, popuAEvolcionada);
        //popuaux.sortGladiatorByFitness();
        //return popuaux;
       // population.sortGladiatorByFitness();
         System.out.println("esto deberia ser numero"+population.getGeneracion());
        return crossoverPopulation(mutateAndInvertPopulation(population)); 
     
     }
    private GladiatorPopulation crossoverPopulation(GladiatorPopulation population){
      GladiatorPopulation crossoverPopulation=new GladiatorPopulation(
              population.getGladiator().length);
      for (int x=0;x<Numb_Elite_Chromosomes;x++){
          crossoverPopulation.getGladiator()[x]=population.getGladiator()[x];
      }
      for (int x=Numb_Elite_Chromosomes;x<population.getGladiator().length;x++){
          
          Chromosome chromo1=selectTournamentPopulation(population).getGladiator()[0].getChromosome();
          Chromosome chromo2=selectTournamentPopulation(population).getGladiator()[0].getChromosome();
          crossoverPopulation.getGladiator()[x]=new Gladiador(crossoverChromosome(chromo1, chromo2));
      }      
      return population;
    }  
    private GladiatorPopulation mutateAndInvertPopulation(GladiatorPopulation population){
       
       GladiatorPopulation mutateAndInvertPopulation=new GladiatorPopulation(population.getGladiator().length);
      for (int x=0;x<Numb_Elite_Chromosomes;x++){
          mutateAndInvertPopulation.getGladiator()[x]=population.getGladiator()[x];
      }
      for(int x=Numb_Elite_Chromosomes;x<population.getGladiator().length;x++){
          if (Math.random()<Inversion_Rate){
          this.Inversiones+=1;
          mutateAndInvertPopulation.getGladiator()[x]=new Gladiador(
                  this.inversionChromosome(population.getGladiator()[x].getChromosome()));
          }
          
          mutateAndInvertPopulation.getGladiator()[x]=new Gladiador(
                  mutateChromosome(population.getGladiator()[x].getChromosome(),population.getGeneracion()));
          
      }
        
        return mutateAndInvertPopulation;
    }
    private GladiatorPopulation selectTournamentPopulation(GladiatorPopulation population){
        GladiatorPopulation tournamentPopulation=new GladiatorPopulation(Tournament_Selection_size);
        int num;
        for (int x=0;x<Tournament_Selection_size;x++){
            num=(int)Math.random()*population.getGladiator().length;
            tournamentPopulation.getGladiator()[x]=
                    population.getGladiator()[num];
        
        }
        tournamentPopulation.sortGladiatorByFitness();
        return tournamentPopulation;
    }
    
    private Chromosome crossoverChromosome(Chromosome chromo1,Chromosome chromo2){
        Chromosome crossoverChromosome=new Chromosome(TARGET_CHromosome.length);
        for (int x=0;x<chromo1.getGenes().length;x++){
            if(Math.random()<0.5)crossoverChromosome.
                    getGenes()[x]=chromo1.getGenes()[x];
            else{
                crossoverChromosome.getGenes()[x]=chromo2.getGenes()[x];
            }
        }
        return crossoverChromosome;
    }
    private Chromosome mutateChromosome(Chromosome chromosome,double MutationRateGeneracion){
    Chromosome mutateChromosome=new Chromosome(TARGET_CHromosome.length);
    for (int x=0;x<chromosome.getGenes().length;x++){
        System.out.println("Esto es el mutationRateGeneracion"+MutationRateGeneracion);
            this.SuperMutationRate=Mutation_Rate+(0.01*MutationRateGeneracion);
            if(Math.random()<this.SuperMutationRate){
                this.Mutaciones+=1;
                if(Math.random()<0.9){
                    mutateChromosome.getGenes()[x]=1;
                }
                else{
            //        mutateChromosome.getGenes()[x]=0;
                }
            }else{
             mutateChromosome.getGenes()[x]=chromosome.getGenes()[x];
               }
        }
    return mutateChromosome;
    }
    
    private Chromosome inversionChromosome(Chromosome chromosome){
        int CantidadDeMicroInversion=(int)Math.random()*Gladiador.totalgenes+1;
        
    for (int x=0;x<CantidadDeMicroInversion;x++){
        int inicio=(int)Math.random()*Gladiador.TotalLenghtArrayBytes;
        int end=(int)Math.random()*Gladiador.TotalLenghtArrayBytes;
        if (inicio>end){
            int aux=inicio;
            inicio=end;
            end=aux;
            
        }
        for (int i=inicio;i<end;i++){
            
            if(chromosome.getGenes()[x]==1){
                chromosome.getGenes()[x]=0;
            }else{
                chromosome.getGenes()[x]=1;
            
               }
        }
        }
    return chromosome;
    }
    
    public int getMutaciones() {
        return Mutaciones;
    }

    public void setMutaciones(int Mutaciones) {
        this.Mutaciones = Mutaciones;
    }

    public int getCrossOvers() {
        return CrossOvers;
    }

    public void setCrossOvers(int CrossOvers) {
        this.CrossOvers = CrossOvers;
    }

    public double getSuperMutationRate() {
        return SuperMutationRate;
    }

    public void setSuperMutationRate(double SuperMutationRate) {
        this.SuperMutationRate = SuperMutationRate;
    }
    
    public int getInversiones() {
        return Inversiones;
    }

    public void setInversiones(int Inversiones) {
        this.Inversiones = Inversiones;
    }
}
    

