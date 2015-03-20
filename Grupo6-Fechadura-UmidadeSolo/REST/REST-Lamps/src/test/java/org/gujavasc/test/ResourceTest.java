package org.gujavasc.test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;


public class ResourceTest {

    @Test
    public void deletarOLivro_javaee7() {
        Client client = ClientBuilder.newClient();
        
        Response response = client.target("http://localhost:8080/rest-example/resources")
                                  .path("books")
                                  .path("{id}")
                                  .resolveTemplate("id", "10")
                                  .request()
                                  .delete();
        
        response.close();
        
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }

}
