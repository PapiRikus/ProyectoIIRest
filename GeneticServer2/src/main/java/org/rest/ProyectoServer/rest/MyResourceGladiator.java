/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rest.ProyectoServer.rest;



/**
 *
 * @author cp
 */
import java.util.List;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.rest.ProyectoServer.models.Gladiador;

import org.rest.ProyectoServer.models.Message;
import org.rest.ProyectoServer.service.GladiatorService;
import org.rest.ProyectoServer.service.MessageService;


@Path("Gladiator")

public class MyResourceGladiator {
        
    GladiatorService gladiatorS=new GladiatorService();
    /**
     *
     */
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
   
    public Gladiador[] getGladiators(){
        System.out.println("Pasa por get");
        return gladiatorS.getGladiators();
        
    }
    @Path("/popuA")
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Gladiador getGladiator(){
        System.out.println("PASA POR GET");
        
        return gladiatorS.getGladiatorPopuA();
        
    }
    
    @Path("/popuB")
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
   
    public Gladiador getGladiator2(){
        System.out.println("PASA POR GET2");
        return gladiatorS.getGladiatorPopuB();
        
    }
    
    @Path("/set")
    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Gladiador setGladiator(Gladiador glad){
        System.out.println("Entra en el Post"+glad.getNombre());
        Message mesage=new Message();
        mesage.setAuthor(glad.getNombre());
        gladiatorS.GuadarGanador1(mesage);
      //  System.out.println(mes.getMessage());
      //  System.out.println(mes.getAuthor());
        return glad;
        
    }
    
    @Path("/sett")
    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Gladiador setGladiator2(Gladiador glad){
        System.out.println("Entra en el Post"+glad.getNombre());
        Message mesage=new Message();
        mesage.setAuthor(glad.getNombre());
        gladiatorS.GuardarGanador2(mesage);

    //  System.out.println(mes.getMessage());
      //  System.out.println(mes.getAuthor());
        return glad;
        
    }
    
}
