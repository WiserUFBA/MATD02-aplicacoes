package org.gujavasc.resources;



import com.mycompany.lampdriver.SimpleLCDDriver;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


@Path("lcd")
public class Resource {

    // Vejam este tutorial para entender os cabeçalhos adicionados para ativar requisições
    // Cross Domain
    // http://www.html5rocks.com/en/tutorials/cors/
    
    SimpleLCDDriver driver = new SimpleLCDDriver("10.3.200.210", 80);
    


    @POST
    public Response setMensagem(@FormParam("mensagem") String mensagem) {
        ResponseBuilder rb = Response.accepted();
        
        driver.setMensagem(mensagem);
        rb = Response.status(Response.Status.OK);
        
        
        rb.header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
        
        
        
    }

}
