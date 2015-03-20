/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gujavasc.entity;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Servo {

    private String state;   //Estado do servo
    
    public Servo(String state) {
        this.state = state;
    }
    
    public Servo(){
        
    }
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
