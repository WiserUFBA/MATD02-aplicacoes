/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucasbulcao
 */
@XmlRootElement(name="lcd")
public class LCD {
    private String mensagem;
    private Integer id;
    
    @XmlElement
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public LCD(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public LCD(){
        
    }

    @XmlElement
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
