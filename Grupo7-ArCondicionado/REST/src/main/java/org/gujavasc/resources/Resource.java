/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.mycompany.resources;
package org.gujavasc.resources;
//import com.mycompany.arcondicionadodriver.InterfaceArCondicionado;
//import com.mycompany.arcondicionadodriver.SimpleArCondicionadoDriver;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import com.mycompany.arcondicionadodriver.InterfaceArCondicionado;
import com.mycompany.arcondicionadodriver.SimpleArCondicionadoDriver;
import javax.ws.rs.GET;
//import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author lucas
 */
@Path("laboratorio-wiser")
public class Resource {
    
    
   InterfaceArCondicionado sad = new SimpleArCondicionadoDriver("192.168.0.102",80); // numero ip e porta  
    
    
    @GET
    @Path("/gettemperatura/arcondicionado/{id}")
    //@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,"application/javascript"})
    @Produces({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON})
    public Response getTemperatura(@PathParam("id") Integer id) {
           System.out.println("teste24");   
       
            int temperaturaAtual = sad.consultarTemperatura();
        
            
         Response.ResponseBuilder rb = Response.ok(temperaturaAtual);
         /*
                 .header("Access-Control-Allow-Origin", "*")
         .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
         .header("Access-Control-Allow-Headers", "Content-Type")
         .allow("OPTIONS"); */
         return rb.build();
        
        /* Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "getLamp {0}", id);
        
        Lamp lamp = new Lamp(driver.statusLamp(id));
        lamp.setId(id);
        
        Response.ResponseBuilder rb = Response.ok(lamp)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        
        return rb.build();
                */
      }

    @GET
    @Path("/on/arcondicionado/{id}")
    //@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,"application/javascript"})
    //@Produces(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON,"application/javascript"})
     public Response onArCondicionado(@PathParam("id") Integer id) {
         //InterfaceArCondicionado sad = new SimpleArCondicionadoDriver("ip",80); // numero ip e porta
         System.out.println("ligou o ar pelo servidor");   
         //System.out.println("sad"+sad);
         sad.on();
         
            Response.ResponseBuilder rb = Response.ok("Ar condicionado ligado")
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        
        return rb.build();
        
        /* Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "getLamp {0}", id);
        
        Lamp lamp = new Lamp(driver.statusLamp(id));
        lamp.setId(id);
        
        Response.ResponseBuilder rb = Response.ok()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        
        return rb.build();
                */
      }
    
     
     
     @GET
    @Path("/off/arcondicionado/{id}")
    //@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,"application/javascript"})
    @Produces(MediaType.APPLICATION_JSON)
    
     public Response offArCondicionado(@PathParam("id") Integer id) {
         //InterfaceArCondicionado sad = new SimpleArCondicionadoDriver("ip",80); // numero ip e porta
         System.out.println("desligou o ar pelo servidor");  
         sad.off();
            
            Response.ResponseBuilder rb = Response.ok("Ar condicionado desligado")
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        
            
        return rb.build();
        
        /* Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "getLamp {0}", id);
        
        Lamp lamp = new Lamp(driver.statusLamp(id));
        lamp.setId(id);
        
        Response.ResponseBuilder rb = Response.ok(lamp)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        
        return rb.build();
                */
      }


     

    @GET
    @Path("/mudartemperatura/arcondicionado/{id}/{tempnova}")
    //@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,"application/javascript"})
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    
     public Response mudarTemperaturaArCondicionado(@PathParam("id") Integer id,@PathParam("tempnova") Integer tempnova) {
         //InterfaceArCondicionado sad = new SimpleArCondicionadoDriver("ip",80); // numero ip e porta
            sad.mudarTemperatura(tempnova);
            
            Response.ResponseBuilder rb = Response.ok("Ar condicionado com nova temperatura: "+sad.consultarTemperatura())
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        
        return rb.build();
        
        /* Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "getLamp {0}", id);
        
        Lamp lamp = new Lamp(driver.statusLamp(id));
        lamp.setId(id);
        
        Response.ResponseBuilder rb = Response.ok(lamp)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        
        return rb.build();
                */
      }


}
