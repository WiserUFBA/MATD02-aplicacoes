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
        
        //SimpleLampDriver driver = new SimpleLampDriver("192.168.25.100", 80);
        SimplePresencaDriver driver = new SimplePresencaDriver("192.168.0.123", 80);
        //driver.statusPresenca(1);
        System.out.println("Status: " + driver.statusPresenca(2));
    }
    
}
