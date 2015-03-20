/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lampdriver;

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
public class SimplePresencaDriver implements Presenca{

    private  String serverIP;
    private  int serverPort;
    
    public SimplePresencaDriver(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    
    //p - Presença e c- Chuva
    @Override
    public String statusPresenca(int id) {
        String retur = null;
        if(id == 1){ //presença
            try {
                retur = sendMenssage("p");
            } catch (IOException ex) {
                Logger.getLogger(SimplePresencaDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(id == 2){ //chuva
            try {
                retur = sendMenssage("c");
            } catch (IOException ex) {
                Logger.getLogger(SimplePresencaDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Logger.getLogger(SimplePresencaDriver.class.getName())
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
            Logger.getLogger(SimplePresencaDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
          Logger.getLogger(SimplePresencaDriver.class.getName())
                  .log(Level.INFO, "Connected to:{0} on port:{1}", new Object[]{serverIP, serverPort});
          //OutputStream do Arduino-Server
          DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
          //BufferedReader para o Arduino-Server
          BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//

          outToServer.writeBytes(msgToServer);//Enviando a mensagem
          Logger.getLogger(SimplePresencaDriver.class.getName())
                  .log(Level.INFO, "Mensagem sendo enviada{0}", msgToServer);
          msgFromServer = inFromServer.readLine();//recebendo a resposta
          Logger.getLogger(SimplePresencaDriver.class.getName())
                  .log(Level.INFO, "Mensagem recebida{0}", msgFromServer);
 
          clientSocket.close();//Fechando o socket
          
          return msgFromServer;
    }
    
}
