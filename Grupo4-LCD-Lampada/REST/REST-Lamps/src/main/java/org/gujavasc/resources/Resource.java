package org.gujavasc.resources;


import com.mycompany.lampdriver.SimpleLampDriver;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.gujavasc.entity.Lamp;

@Path("lamp")
public class Resource {

    // Vejam este tutorial para entender os cabeçalhos adicionados para ativar requisições
    // Cross Domain
    // http://www.html5rocks.com/en/tutorials/cors/
    
    SimpleLampDriver driver = new SimpleLampDriver("10.3.200.210", 80);
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,"application/javascript"})
    public Response getLamp(@PathParam("id") Integer id) {
        Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "getLamp {0}", id);
        
        Lamp lamp = new Lamp(driver.statusLamp(id));
        lamp.setId(id);
        
        ResponseBuilder rb = Response.ok(lamp)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }

    @POST
    @Path("{id}")
    public Response setStatus(@FormParam("status") String status, @PathParam("id") Integer id) {
        ResponseBuilder rb = Response.accepted();
        
        Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "setStatus {0} {1}", new Object[]{id, status});
        
        if(null != status)switch (status) {
            case "0":
                driver.offLamp(id);
                break;
            case "1":
                driver.onLamp(id);
                break;
            default: rb = Response.status(Response.Status.PRECONDITION_FAILED);
        }
        
        rb.header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }

}
