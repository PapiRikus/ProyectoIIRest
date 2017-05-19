/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rest.ProyectoServer.GAlgorith3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import manejoXml.ManagerXml;
import org.rest.ProyectoServer.models.Gladiador;
import org.xml.sax.SAXException;

/**
 *
 * @author cp
 */
public class DriverGAlgorith {
    GladiatorPopulation population;
    
    GAlgorithm geneticAlgorith;
    int generacion;
    String nombreArchivo;
    ManagerXml mag=new ManagerXml();
    public DriverGAlgorith(String nombreArchivo){
        
        try {
            generacion=mag.generacionesenGladiadoresxml(nombreArchivo);
            System.out.println("uia:"+this.generacion);   
            ArrayList<Gladiador>listGlads=mag.listaasobtenerGladiatorPopulation(nombreArchivo);
            if (listGlads.size()==0){
                System.out.println("Priemra generacion de gladiadores");
                population =new GladiatorPopulation(12);
            }
            else {
                System.out.println("Ya existe una poblacion de gladiadores");
                ArrayList<Gladiador>a=mag.listaasobtenerGladiatorPopulation(nombreArchivo);
                System.out.println(a.size());
                for (int i=0;i<a.size();i++){
                System.out.println(a.get(i).getNombre()+"  "+i);
                }
                population=this.deListaaPoblacionGladiador(a);
                population.setGeneracion(generacion+1);
            }
                 System.out.println("uia:"+this.generacion);   
       
            
           // System.out.println(population.getGeneracion()+"   sdfsd Generacionn:  "+generacion);
           
            geneticAlgorith=new GAlgorithm();
            this.nombreArchivo=nombreArchivo;
            System.out.println("Archivo:"+this.nombreArchivo);
       //     System.out.println("NUEVAA POBLACION");
            System.out.println("uia2:"+this.generacion);   
         
       this.innit();
     
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DriverGAlgorith.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DriverGAlgorith.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DriverGAlgorith.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    public DriverGAlgorith(){
    
    }
    
    
    public void GuardarPoblacion(GladiatorPopulation population){
      //  mag.agregarPoblacion(population, "popu");
        
    }
    public GladiatorPopulation deListaaPoblacionGladiador(ArrayList<Gladiador> ListagladiatorPoblacion){
        GladiatorPopulation gladiatorPopulacion=new GladiatorPopulation(ListagladiatorPoblacion.size()/2);
   
        //Por que se volvia muy lento el servidor
        for (int i=0;i<ListagladiatorPoblacion.size()/2;i++){
                ListagladiatorPoblacion.get(i).getChromoString();
                ListagladiatorPoblacion.get(i).convertirStringChromoAChromo();
            System.err.println("Esdfsdfsdfsdf:"+ListagladiatorPoblacion.get(i).getChromosome());
        gladiatorPopulacion.getGladiator()[i]=ListagladiatorPoblacion.get(i);
            System.out.println(gladiatorPopulacion.getGladiator()[i].getNombre()+
                    "Esto son los gneres"+gladiatorPopulacion.getGladiator()[i].getChromoString());    
        }
        return gladiatorPopulacion;
    }
    public GladiatorPopulation leerPoblaciones(){
        
        
        return null;
    }
    
    public void innit(){
        population.sortGladiatorByFitness();
        printPopulation(population,"TargetChromosome:"+
                Arrays.toString(GAlgorithm.TARGET_CHromosome));
    
    }
    
    public void printPopulation(GladiatorPopulation population,String heading){
        System.out.println(heading);
        System.out.println("--------------------------");
        for (int i=0;i<population.getGladiator().length;i++){
            System.out.println("Nombre:"+population.getGladiator()[i].getNombre()+"Chromosome#"+i+":"+
                    Arrays.toString(population.getGladiator()[i].getChromosome().getGenes())+
                    " Fitness:"+
                    population.getGladiator()[i].getChromosome().getFitness());
        }
        
        System.out.println("--------------------------");
        
    }
   public Gladiador hacerAGenetic(){
        
        System.out.println("-----------------");   
        System.out.println("Generation#0"+"1 Fitness:"+GAlgorithm.TARGET_CHromosome.length);    
        System.out.println("uia3:"+this.generacion);   
       
       // printPopulation(population,"TargetChromosome:"+Arrays.toString(GAlgorithm.TARGET_CHromosome));
        int generationN=0;
    //    while (population.getGladiator()[0].getChromosome().getFitness()<GeneticAlgorith.TARGET_CHromosome.length-6){
     
       // while(generationN<3){
            
        generationN++;
            population.setGeneracion(this.generacion);
            population= geneticAlgorith.evolve(population);
            population.sortGladiatorByFitness();
          //  System.out.println(population.getGladiator()[0].getChromosome().getFitness()+"    "+(GeneticAlgorith.TARGET_CHromosome.length-3));
            
            System.out.println("Generation#"+generationN+"| Fittes chromosome fit"+
                    population.getGladiator()[0].getChromosome().getFitness());
            //printPopulation(population,"TargetChromosome:"+Arrays.toString(GeneticAlgorith.TARGET_CHromosome));
          
            try {
                 population.setGeneracion(this.generacion);
                 population.setMutaciones(this.geneticAlgorith.getMutaciones());
                 population.setMutationRate(this.geneticAlgorith.getSuperMutationRate());
                 population.setIversiones(this.geneticAlgorith.getInversiones());
                 
                 mag.agregarGladiatorPoblacion(population, this.nombreArchivo);
            } catch (SAXException ex) {
                Logger.getLogger(DriverGAlgorith.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(DriverGAlgorith.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DriverGAlgorith.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(DriverGAlgorith.class.getName()).log(Level.SEVERE, null, ex);
            }
        //}
        //}
        
        Gladiador glad=population.getGladiator()[0];
        glad.convertirCrhomosomaaAtributos();
        glad.setMutatioRateMandar(this.geneticAlgorith.SuperMutationRate);
        glad.setNumeroMutaciones(this.geneticAlgorith.getMutaciones());
        System.out.println(glad.getGolpeMano());
        System.out.println(glad.getGolpePies());
        System.out.println(glad.getGolpeCuerpo());
        
        System.out.println(glad.getResistenciaGolpesMano());
        System.out.println(glad.getResistencaGolpesPies());
        System.out.println(glad.getResistenciaGolpesCuerpo());
        
        System.out.println("Resistencia Total"+glad.getResistenciaTotal());
        return glad;
   
   }
}
