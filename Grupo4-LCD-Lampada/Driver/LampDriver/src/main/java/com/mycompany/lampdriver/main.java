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
public class main {
    
    public static void main(String args[]){
        
       SimpleLampDriver driver = new SimpleLampDriver("10.3.200.210", 80);
       driver.onLamp(1);
       System.out.println(driver.statusLamp(1));
    }
    
}
