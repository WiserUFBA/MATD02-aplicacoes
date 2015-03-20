/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.LCD;
import cliente.LCDCliente;
import java.io.IOException;

/**
 *
 * @author lucasbulcao
 */
@ManagedBean
@ViewScoped
public class LCDBean {
    LCD lcd = new LCD();
    
    public void enviarTexto() throws IOException{
        System.out.print(lcd.getMensagem());
        LCDCliente lcdCliente = new LCDCliente();
        lcdCliente.setLCD(lcd);
    }

    public LCD getLcd() {
        return lcd;
    }

    public void setLcd(LCD lcd) {
        this.lcd = lcd;
    }
    
    
    
}
