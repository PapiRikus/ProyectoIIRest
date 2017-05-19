/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rest.ProyectoServer.models;

/**
 *
 * @author cp
 */
import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

//import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Arrays;
import javax.xml.bind.annotation.XmlElement;
import jdk.nashorn.internal.ir.annotations.Ignore;
@XmlRootElement(name="Gladiador")
//@XStreamAlias("Gladiador")
public class Gladiador {
    private String nombre;
    private int golpeMano;
    private int golpePies;
    private int golpeCuerpo;
    private int ResistenciaGolpesMano;
    private int ResistenciaGolpesPies;
    private int ResistenciaGolpesCuerpo;
    private int ResistenciaTotal;
    private Chromosome chromosome;
    private String chromoString;
    public static int genSize=9;
    public static int totalgenes=6;
    public static int TotalLenghtArrayBytes=54;
    
    public int DamageTowers=7;

    public double MutatioRateMandar;
    public int NumeroMutaciones;
    public Gladiador(){
        
    }
    
    public Gladiador(String a,String b){
        
    }

    public Gladiador(String nombre, int golpeMano, int golpePies, int golpeCuerpo, int ResistenciaGolpesMano, int ResistencaGolpesPies, int ResistenciaGolpesCuerpo) {
        this.nombre = nombre;
        this.golpeMano = golpeMano;
        this.golpePies = golpePies;
        this.golpeCuerpo = golpeCuerpo;
        this.ResistenciaGolpesMano = ResistenciaGolpesMano;
        this.ResistenciaGolpesPies = ResistencaGolpesPies;
        this.ResistenciaGolpesCuerpo = ResistenciaGolpesCuerpo;
        ResistenciaTotal();
         generarNombre();
       
    }
    
    public Gladiador(Chromosome chomosome){
        this.chromosome=chomosome;
        convertirCrhomosomaaAtributos();
        ResistenciaTotal();
        this.chromoString=Arrays.toString(chomosome.getGenes())+"/"+chomosome.getFitness();
        generarNombre();
    }
    public Gladiador(Chromosome chomosome,int sizeGen){
        this.genSize=sizeGen;
        this.chromosome=chomosome;
        convertirCrhomosomaaAtributos();
        ResistenciaTotal();
        generarNombre();
   
    }
    
    private void generarNombre(){
    String[] vocales={"a","e","i","o","u"};
    String[] consonantes={"l","r","e","f","d","p"};
    int a,b,c;
    c=(int) ((Math.random() * 5) + 3);
   
    String nombre="";
    for (int i =0;i<c;i++){
        a=(int) (Math.random() * vocales.length);
        b=(int) (Math.random() * consonantes.length);
       
        nombre+=vocales[a];
        nombre+=consonantes[b];
       
        
    } 
    if (Math.random()>0.5)
       nombre+=(int) (Math.random() * 100 + 1);
      
    
    this.setNombre(nombre);
    }
    public void convertirStringChromoAChromo(){
       //Propiedad intelectual de http://stackoverflow.com/users/426379/saul
        String[] items = this.chromoString.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

        int[] results = new int[items.length];

        for (int i = 0; i < items.length; i++) {
        try {
            results[i] = Integer.parseInt(items[i]);
        } catch (NumberFormatException nfe) {
        };
        this.setChromosome(new Chromosome(results));
    }
        
    }
    
    public void convertirCrhomosomaaAtributos(){
        if (this.chromosome.getGenes().length==this.TotalLenghtArrayBytes){
            int n=0;
            int gene=0;
            for(int i =0;i<totalgenes;i++){
                for (int j=n;j<(genSize+n);j++){
                   // System.err.println("Esto se corrio de "+n);
                    if(chromosome.getGenes()[j]==1){
                        gene+=1;
                    
                    
                    }
                       
              }
              
              //gene=convertirBinarioADecimal(this.getChromosome().getGenes(), n,(genSize+n)-1);
              if(i>=3)  
              gene*=DamageTowers*gene;
                switch(i){
                        case 1:setGolpeMano(gene);
                        case 2:setGolpePies(gene);
                        case 3:setGolpeCuerpo(gene);
                        case 4:setResistenciaGolpesMano(gene);
                        case 5:setResistencaGolpesPies(gene);
                        case 6:setResistenciaGolpesCuerpo(gene);
                    }
                gene=0;
                n+=genSize;
            }
        }
    
    }
    
    public static int convertirBinarioADecimal(int []genes,int begin,int end){
    int j=0;
    for(int i=begin;i<end;i++){
        if(genes[i]== 1){
         j=(int) (j+ Math.pow(2,genes.length-1-i));
         
     }

    }
    return j;
    }
    
    
    public void ResistenciaTotal(){
        ResistenciaTotal=this.getResistencaGolpesPies()+
                this.getResistenciaGolpesCuerpo()+this.getResistenciaGolpesMano();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getGolpeMano() {
        return golpeMano;
    }

    public void setGolpeMano(int golpeMano) {
        this.golpeMano = golpeMano;
    }

    public int getGolpePies() {
        return golpePies;
    }

    public void setGolpePies(int golpePies) {
        this.golpePies = golpePies;
    }

    public int getGolpeCuerpo() {
        return golpeCuerpo;
    }

    public void setGolpeCuerpo(int golpeCuerpo) {
        this.golpeCuerpo = golpeCuerpo;
    }

    public int getResistenciaGolpesMano() {
        return ResistenciaGolpesMano;
    }

    public void setResistenciaGolpesMano(int ResistenciaGolpesMano) {
        this.ResistenciaGolpesMano = ResistenciaGolpesMano;
    }

    public int getResistencaGolpesPies() {
        return ResistenciaGolpesPies;
    }

    public void setResistencaGolpesPies(int ResistencaGolpesPies) {
        this.ResistenciaGolpesPies = ResistencaGolpesPies;
    }

    public int getResistenciaGolpesCuerpo() {
        return ResistenciaGolpesCuerpo;
    }

    public void setResistenciaGolpesCuerpo(int ResistenciaGolpesCuerpo) {
        this.ResistenciaGolpesCuerpo = ResistenciaGolpesCuerpo;
    }

    public int getResistenciaTotal() {
        return ResistenciaTotal;
    }

    public void setResistenciaTotal(int ResistenciaTotal) {
        this.ResistenciaTotal = ResistenciaTotal;
    }
    public Chromosome getChromosome() {
        return chromosome;
    }

    public void setChromosome(Chromosome chromosome) {
        this.chromosome = chromosome;
    }
     public int getGenSize() {
        return genSize;
    }

    public void setGenSize(int genSize) {
        this.genSize = genSize;
    }

    public int getTotalgenes() {
        return totalgenes;
    }

    public void setTotalgenes(int totalgenes) {
        this.totalgenes = totalgenes;
    }
    public String getChromoString() {
        return chromoString;
    }

    public void setChromoString(String chromoString) {
        this.chromoString = chromoString;
    }
    public int getResistenciaGolpesPies() {
        return ResistenciaGolpesPies;
    }

    public void setResistenciaGolpesPies(int ResistenciaGolpesPies) {
        this.ResistenciaGolpesPies = ResistenciaGolpesPies;
    }

    public static int getTotalLenghtArrayBytes() {
        return TotalLenghtArrayBytes;
    }

    public static void setTotalLenghtArrayBytes(int TotalLenghtArrayBytes) {
        Gladiador.TotalLenghtArrayBytes = TotalLenghtArrayBytes;
    }
    @Ignore
  //  @XmlElement(ignore=true)
    public int getDamageTowers() {
        return DamageTowers;
    }
    @Ignore
    public void setDamageTowers(int DamageTowers) {
        this.DamageTowers = DamageTowers;
    }

    public double getMutatioRateMandar() {
        return MutatioRateMandar;
    }

    public void setMutatioRateMandar(double MutatioRateMandar) {
        this.MutatioRateMandar = MutatioRateMandar;
    }

    public int getNumeroMutaciones() {
        return NumeroMutaciones;
    }

    public void setNumeroMutaciones(int NumeroMutaciones) {
        this.NumeroMutaciones = NumeroMutaciones;
    }
}
