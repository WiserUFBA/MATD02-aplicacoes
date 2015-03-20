/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tempdriver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chronos
 */
public class SimpleTemperaturaUmidadeDriver implements TemperaturaUmidade{

    private  String serverIP;
    private  int serverPort;
    
    public SimpleTemperaturaUmidadeDriver(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    @Override
    public String statusTemperatura(int id) {
        String retur = null;
        if(id == 1){
            try {
                retur = sendMenssage("g1");
            } catch (IOException ex) {
                Logger.getLogger(SimpleTemperaturaUmidadeDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retur;
    }
    
       @Override
    public String statusUmidade(int id) {
        String retur = null;
        if(id == 2){
            try {
                retur = sendMenssage("g2");
            } catch (IOException ex) {
                Logger.getLogger(SimpleTemperaturaUmidadeDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retur;
    }

    private String sendMenssage(String msgToServer) throws IOException{
        
        String msgFromServer;
 
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(serverIP, serverPort); //making the socket connection
        } catch (IOException ex) {
            Logger.getLogger(SimpleTemperaturaUmidadeDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
          Logger.getLogger(SimpleTemperaturaUmidadeDriver.class.getName())
                  .log(Level.INFO, "Connected to: {0} on port: {1}", new Object[]{serverIP, serverPort});
          //OutputStream do Arduino-Server
          DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
          //BufferedReader para o Arduino-Server
          BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//

          outToServer.writeBytes(msgToServer);//Enviando a mensagem
          Logger.getLogger(SimpleTemperaturaUmidadeDriver.class.getName())
                  .log(Level.INFO, "Mensagem sendo enviada {0}", msgToServer);
          msgFromServer = inFromServer.readLine();//recebendo a resposta
          Logger.getLogger(SimpleTemperaturaUmidadeDriver.class.getName())
                  .log(Level.INFO, "Mensagem recebida {0}", msgFromServer);
 
          clientSocket.close();//Fechando o socket
          
          return msgFromServer;
    }


    
}
