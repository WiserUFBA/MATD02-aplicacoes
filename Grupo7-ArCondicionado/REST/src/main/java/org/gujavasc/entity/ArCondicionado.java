/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gujavasc.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas
 */


/**
 *
 * @author chronos
 */
@XmlRootElement
public class ArCondicionado {

    private String state;
    private Integer id;
    private Double temperatura;

    public ArCondicionado(String state) {
    this.state = state;
    }
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    

    

}
    