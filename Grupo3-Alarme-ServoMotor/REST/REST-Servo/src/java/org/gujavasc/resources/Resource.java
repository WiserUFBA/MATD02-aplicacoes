package org.gujavasc.resources;


import com.mycompany.servodriver.ServoDriver;
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
import org.gujavasc.entity.Servo;

@Path("servo")
public class Resource {

    // http://www.html5rocks.com/en/tutorials/cors/
    
    ServoDriver driver = new ServoDriver("192.168.0.111", 80);
    
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,"application/javascript"})
    public Response getServo() {
        Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "getServo {0}");
        
        Servo servo = new Servo(driver.statusServo());
        
        ResponseBuilder rb = Response.ok(servo)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }

    @POST
    public Response setStatus(@FormParam("status") String status) {
        
        ResponseBuilder rb = Response.ok();
        
        Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "setStatus {0} {1} {2} {3} {4}", new Object[]{status});
        
        if(null != status){
                driver.rotateServo(status);
        }else
                rb = Response.status(Response.Status.PRECONDITION_FAILED);
        
        rb.header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
}
