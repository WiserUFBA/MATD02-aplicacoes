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

public class FechaduraDriver implements Fechadura {

    private String serverIP;
    private int serverPort;

    public FechaduraDriver(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    @Override
    public void lock(int id) {
        if (id == 1) {
            try {
                sendMenssage("f1");
            } catch (IOException ex) {
                Logger.getLogger(FechaduraDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Logger.getLogger(FechaduraDriver.class.getName())
                    .log(Level.SEVERE, "Identificador desconhecido");
        }
    }

    @Override
    public void unlock(int id) {
        if (id == 1) {
            try {
                sendMenssage("f0");
            } catch (IOException ex) {
                Logger.getLogger(FechaduraDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Logger.getLogger(FechaduraDriver.class.getName())
                    .log(Level.SEVERE, "Identificador desconhecido");
        }
    }

    @Override
    public String statusLock(int id) {
        String status = null;
        if (id == 1) {
            try {               
                status = sendMenssage("fc");
            } catch (IOException ex) {
                Logger.getLogger(FechaduraDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Logger.getLogger(FechaduraDriver.class.getName())
                    .log(Level.SEVERE, "Identificador desconhecido");
        }
        return status;
    }

    private String sendMenssage(String msgToServer) throws IOException {

        String msgFromServer;

        Socket clientSocket = null;
        try {
            clientSocket = new Socket(serverIP, serverPort); //making the socket connection
        } catch (IOException ex) {
            Logger.getLogger(FechaduraDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        Logger.getLogger(FechaduraDriver.class.getName())
                .log(Level.INFO, "Connected to:{0} on port:{1}", new Object[]{serverIP, serverPort});
        //OutputStream do Arduino-Server
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        //BufferedReader para o Arduino-Server
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//

        outToServer.writeBytes(msgToServer);//Enviando a mensagem
        Logger.getLogger(FechaduraDriver.class.getName())
                .log(Level.INFO, "Mensagem sendo enviada {0}", msgToServer);
        msgFromServer = inFromServer.readLine();//recebendo a resposta
        Logger.getLogger(FechaduraDriver.class.getName())
                .log(Level.INFO, "Mensagem recebida {0}", msgFromServer);

        clientSocket.close();//Fechando o socket

        return msgFromServer;
    }
}
