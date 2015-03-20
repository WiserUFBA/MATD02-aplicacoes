/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arcondicionadodriver;

/**
 *
 * @author lucas
 */
public interface InterfaceArCondicionado {
    
    public void on();
    public void off();
    public int consultarTemperatura();
    public void mudarTemperatura(int mudarTemperaturaPara);
    


}
