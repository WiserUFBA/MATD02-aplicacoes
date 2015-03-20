/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Lampada;
import cliente.LampadaCliente;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.bind.JAXBException;

/**
 *
 * @author lucasbulcao
 */
@ManagedBean
@ViewScoped
public class LampadaBean {
    Lampada lampada = new Lampada();

    public Lampada getLampada() {
        return lampada;
    }

    public void setLampada(Lampada lampada) {
        this.lampada = lampada;
    }
    
    public void acenderLampada(int id) throws JAXBException, MalformedURLException, IOException{
        LampadaCliente lampCliente = new LampadaCliente();
        lampada.setId(id);
        lampada.setState(1);
        lampCliente.setLampada(lampada);
    }
    
    public void apagarLampada(int id) throws JAXBException, MalformedURLException, IOException{
        LampadaCliente lampCliente = new LampadaCliente();
        lampada.setId(id);
        lampada.setState(0);
        lampCliente.setLampada(lampada);
    }
    
    public void statusLampada(int id) throws JAXBException, MalformedURLException, IOException{
        LampadaCliente lampCliente = new LampadaCliente();
        lampada = lampCliente.getLampada(id);

    }
    

   
    
    
    
}
