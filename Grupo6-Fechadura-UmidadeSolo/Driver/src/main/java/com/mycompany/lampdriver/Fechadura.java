/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lampdriver;

public interface Fechadura {
        
    public void lock(int id);
    
    public void unlock(int id);
    
    public String statusLock(int id);
}
