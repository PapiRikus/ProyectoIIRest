/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rest.ProyectoServer.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import manejoXml.ManagerXml;
import org.rest.ProyectoServer.GAlgorith3.DriverGAlgorith;
import org.rest.ProyectoServer.models.Gladiador;
import org.rest.ProyectoServer.models.Message;
import org.xml.sax.SAXException;

/**
 *
 * @author cp
 */
public class GladiatorService {
    DriverGAlgorith dirverGAlgorth;
    DriverGAlgorith dirverGAlgorth2;
    manejoXml.ManagerXml mag=new ManagerXml();
    String archivo="gladPopuA";
    String archivo2="gladPopuB";
    public GladiatorService() {
    
    dirverGAlgorth=new DriverGAlgorith("gladPopuA");
    dirverGAlgorth2=new DriverGAlgorith("gladPopuB");
   
    System.out.println("se crea nuevo driver");
    }
    
    public Gladiador getGladiatorPopuA(){
        Gladiador a=dirverGAlgorth.hacerAGenetic();
        System.out.println(a.getChromosome().getGenes().length);
        return a;
    }
    public Gladiador getGladiatorPopuB(){
        return dirverGAlgorth2.hacerAGenetic();
  
    }
    
    public Gladiador[] getGladiators(){
            
            
            Gladiador glad1=dirverGAlgorth.hacerAGenetic();
            Gladiador glad2=dirverGAlgorth.hacerAGenetic();
            Gladiador[]aux ={glad1,glad2};
            return aux;
        }
    
    public List<Gladiador> getAllGladiator(){
			Gladiador m=new Gladiador("esta carajada",  "esta otra");
			Gladiador m1=new Gladiador( "Cuando ya esto sirva", "yo lo hice");
			List<Gladiador> lis=new ArrayList<>();
			lis.add(m);
			lis.add(m1);
			return lis;
		
	}
    public void GuadarGanador1(Message msj){
        
        try {
            mag.agregarGanador(this.archivo,msj);
        } catch (IOException ex) {
            Logger.getLogger(GladiatorService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GladiatorService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(GladiatorService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(GladiatorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void GuardarGanador2(Message msj){
        try {
            mag.agregarGanador(archivo2, msj);
        } catch (SAXException ex) {
            Logger.getLogger(GladiatorService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GladiatorService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GladiatorService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(GladiatorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
