package org.gujavasc.resources;


import com.mycompany.tempdriver.SimpleTemperaturaUmidadeDriver;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.gujavasc.entity.Temp;

@Path("temp")
public class Resource {

    // Vejam este tutorial para entender os cabeçalhos adicionados para ativar requisições
    // Cross Domain
    // http://www.html5rocks.com/en/tutorials/cors/
    
    SimpleTemperaturaUmidadeDriver driver = new SimpleTemperaturaUmidadeDriver("192.168.0.182", 80);
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getStatus(@PathParam("id") Integer id) {
        //ResponseBuilder rb = Response.accepted();
        
        Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "getStatus {0}", new Object[]{id});
        
        if(null != id)switch (id) {
            case 1:
                driver.statusTemperatura(1);
                break;
            case 2:
                driver.statusUmidade(1);
                break;
            //default: rb = Response.status(Response.Status.PRECONDITION_FAILED);
        }
        
        Temp temp = new Temp(driver.statusTemperatura(id));
        
        if(id == 2) temp = new Temp(driver.statusUmidade(id));
        
        temp.setId(id);
        
        
        ResponseBuilder rb = Response.ok(temp)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }

}
