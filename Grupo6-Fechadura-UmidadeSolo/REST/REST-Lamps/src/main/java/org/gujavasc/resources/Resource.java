package org.gujavasc.resources;


import com.mycompany.lampdriver.FechaduraDriver;
import com.mycompany.lampdriver.SensorUmidadeDriver;
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
import org.gujavasc.entity.Fechadura;
import org.gujavasc.entity.Lamp;
import org.gujavasc.entity.SensorUmidade;

@Path("")
public class Resource {

    // Vejam este tutorial para entender os cabeçalhos adicionados para ativar requisições
    // Cross Domain
    // http://www.html5rocks.com/en/tutorials/cors/
    private final String serverIP = "192.168.0.150";
    
    SimpleLampDriver lampDriver = new SimpleLampDriver(serverIP, 80);
    FechaduraDriver fechaduraDriver = new FechaduraDriver(serverIP, 80);
    SensorUmidadeDriver sensorUmidadeDriver = new SensorUmidadeDriver(serverIP, 80);
    
    @GET
    @Path("lamp/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,"application/javascript"})
    public Response getLamp(@PathParam("id") Integer id) {
        Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "getLamp {0}", id);        
        
        Lamp lamp = new Lamp(lampDriver.statusLamp(id));
        lamp.setId(id);
        
        ResponseBuilder rb = Response.ok(lamp)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
    
    @POST
    @Path("lamp/{id}")
    public Response setLampStatus(@FormParam("status") String status, @PathParam("id") Integer id) {
        ResponseBuilder rb = Response.accepted();
        
        Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "setLampStatus {0} {1}", new Object[]{id, status});

        if(null != status)switch (status) {
            case "0":
                lampDriver.offLamp(id);
                break;
            case "1":
                lampDriver.onLamp(id);
                break;
            default: rb = Response.status(Response.Status.PRECONDITION_FAILED);
        }
        
        rb.header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
    
    @GET
    @Path("fechadura/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,"application/javascript"})
    public Response getFechadura(@PathParam("id") Integer id) {
        Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "getFechadura {0}", id);
        
        Fechadura fechadura = new Fechadura(fechaduraDriver.statusLock(id));
        fechadura.setId(id);
        
        ResponseBuilder rb = Response.ok(fechadura)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }

    @POST
    @Path("fechadura/{id}")
    public Response setFechaduraStatus(@FormParam("status") String status, @PathParam("id") Integer id) {
        ResponseBuilder rb = Response.accepted();
        
        Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "setFechaduraStatus {0} {1}", new Object[]{id, status});
        
        if(null != status)switch (status) {
            case "0":
                fechaduraDriver.unlock(id);
                break;
            case "1":
                fechaduraDriver.lock(id);
                break;
            default: rb = Response.status(Response.Status.PRECONDITION_FAILED);
        }
        
        rb.header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }

    @GET
    @Path("sensor-umidade/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,"application/javascript"})
    public Response getSensorUmidade(@PathParam("id") Integer id) {
        Logger.getLogger(Resource.class.getName())
                    .log(Level.SEVERE, "getSensorUmidade {0}", id);
        
        SensorUmidade sensorUmidade = new SensorUmidade(sensorUmidadeDriver.readHumidity(id));
        sensorUmidade.setId(id);
        
        ResponseBuilder rb = Response.ok(sensorUmidade)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
}
