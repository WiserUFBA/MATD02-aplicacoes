package org.gujavasc.resources;


import com.mycompany.buzzerdriver.BuzzerDriver;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.gujavasc.entity.Buzzer;

@Path("buzzer")
public class Resource {

    // http://www.html5rocks.com/en/tutorials/cors/
    
    BuzzerDriver driver = new BuzzerDriver("192.168.0.111", 80);
    
    @GET
    //@Path("{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,"application/javascript"})
    public Response getBuzzer() {
        Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "getBuzzer {0}");
        
        Buzzer buzzer = new Buzzer(driver.statusBuzzer());
        
        ResponseBuilder rb = Response.ok(buzzer)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }

    @POST
    //@Path("{id}")
    public Response setStatus(@FormParam("status") String status) {
        
        ResponseBuilder rb = Response.ok();
        
        Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "setStatus {0} {1}", new Object[]{status});
        
        if(null != status){
            if(status.equals("0"))
                driver.offBuzzer();
            else if(status.equals("1") || status.equals("2") || status.equals("3"))
                driver.onBuzzer(status);
            else
                rb = Response.status(Response.Status.PRECONDITION_FAILED);
        }
        
        rb.header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }

}
