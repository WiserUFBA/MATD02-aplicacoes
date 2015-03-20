/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gujavasc.entity;

public class SensorUmidade {

    private String umidade;
    private Integer id;

    public SensorUmidade(String umidade) {
        this.umidade = umidade;
    }

    public SensorUmidade() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUmidade() {
        return umidade;
    }

    public void setUmidade(String umidade) {
        this.umidade = umidade;
    }
}
