/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servodriver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chronos
 */
public class main {
    
    public static void main(String args[]){
        ServoDriver servo = new ServoDriver("192.168.0.111", 80);
        
        try {
            System.out.println(servo.sendMenssage("s0"));
            
            
//  SimpleLampDriver driver = new SimpleLampDriver("192.168.25.100", 80);
            //  driver.offLamp(1);
            //  System.out.println(driver.statusLamp(2));
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
