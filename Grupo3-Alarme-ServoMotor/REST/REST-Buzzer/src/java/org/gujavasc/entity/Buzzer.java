/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gujavasc.entity;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Buzzer {

    private String state;   //Estado do buzzer 
    private Integer freq;   //Frequencia
    
    public Buzzer(String state) {
        this.state = state;
    }
    
    public Buzzer(){
        
    }
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
    }
    
}
