/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lampdriver;

/**
 *
 * @author chronos
 */
public interface Lamp {
    
    public void onLamp(int id);
    
    public void offLamp(int id);
    
    public String statusLamp(int id);
    
}
