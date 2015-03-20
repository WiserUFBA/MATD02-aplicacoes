/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gujavasc.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chronos
 */
@XmlRootElement
public class Lamp {

    private String state;
    private Integer id;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Lamp(String state) {
        this.state = state;
    }
    
    public Lamp(){
        
    }
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
}
