/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sensordriver;

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
public class SimpleSensorDriver implements Sensor{

    private  String serverIP;
    private  int serverPort;
    
    public SimpleSensorDriver(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }
/*
    @Override
    public void onLamp(int id) {
        if(id == 1){
            try {
                sendMenssage("y1");
            } catch (IOException ex) {
                Logger.getLogger(SimpleSensorDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(id == 2){
            try {
                sendMenssage("b1");
            } catch (IOException ex) {
                Logger.getLogger(SimpleSensorDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Logger.getLogger(SimpleSensorDriver.class.getName())
                    .log(Level.SEVERE, "Identificador desconhecido");
        }
    }

    @Override
    public void offLamp(int id) {
        if(id == 1){
            try {
                sendMenssage("y0");
            } catch (IOException ex) {
                Logger.getLogger(SimpleSensorDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(id == 2){
            try {
                sendMenssage("b0");
            } catch (IOException ex) {
                Logger.getLogger(SimpleSensorDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Logger.getLogger(SimpleSensorDriver.class.getName())
                    .log(Level.SEVERE, "Identificador desconhecido");
        }
    }*/

    @Override
    public String statusSensor(int id) {
        String retur = null;
        Logger.getLogger(SimpleSensorDriver.class.getName()).log(Level.SEVERE, "id: "+id);
        if(id == 0){
            try {
                retur = sendMenssage("y");
            } catch (IOException ex) {
                Logger.getLogger(SimpleSensorDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(id ==5){
            try {
                retur = sendMenssage("b");
            } catch (IOException ex) {
                Logger.getLogger(SimpleSensorDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Logger.getLogger(SimpleSensorDriver.class.getName())
                    .log(Level.SEVERE,  "Identificador desconhecido");
        }
        return retur;
    }

    private String sendMenssage(String msgToServer) throws IOException{
        
        String msgFromServer;
 
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(serverIP, serverPort); //making the socket connection
        } catch (IOException ex) {
            Logger.getLogger(SimpleSensorDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
          Logger.getLogger(SimpleSensorDriver.class.getName())
                  .log(Level.INFO, "Connected to: {0} on port: {1}", new Object[]{serverIP, serverPort});
          //OutputStream do Arduino-Server
          DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
          //BufferedReader para o Arduino-Server
          BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//

          outToServer.writeBytes(msgToServer);//Enviando a mensagem
          Logger.getLogger(SimpleSensorDriver.class.getName())
                  .log(Level.INFO, "Mensagem sendo enviada {0}", msgToServer);
          msgFromServer = inFromServer.readLine();//recebendo a resposta
          Logger.getLogger(SimpleSensorDriver.class.getName())
                  .log(Level.INFO, "Mensagem recebida {0}", msgFromServer);
 
          clientSocket.close();//Fechando o socket
          
          return msgFromServer;
    }
    
}
