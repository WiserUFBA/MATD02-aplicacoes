/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tempdriver;

/**
 *
 * @author chronos
 */
public class main {
    
    public static void main(String args[]){
        
      //  SimpleLampDriver driver = new SimpleLampDriver("192.168.25.100", 80);
        SimpleTemperaturaUmidadeDriver driver = new SimpleTemperaturaUmidadeDriver("192.168.1.56", 80);
        
      System.out.println(driver.statusTemperatura(1));
      System.out.println(driver.statusUmidade(1));
    }
    
}
