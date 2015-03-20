/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buzzerdriver;

public interface Buzzer {
    
    public void onBuzzer(String status);
    
    public void offBuzzer();
    
    public String statusBuzzer();
    
}
