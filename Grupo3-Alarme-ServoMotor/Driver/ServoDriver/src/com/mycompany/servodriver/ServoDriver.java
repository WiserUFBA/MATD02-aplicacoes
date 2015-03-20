package com.mycompany.servodriver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServoDriver implements Servo{

    private  String serverIP;
    private  int serverPort;
    
    public ServoDriver(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    @Override
    public void rotateServo(String rot) {
        try {
            sendMenssage("s"+rot);
        } catch (IOException ex) {
            Logger.getLogger(ServoDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String statusServo() {
        String retur = null;
        try {
            retur = sendMenssage("s5");
        } catch (IOException ex) {
            Logger.getLogger(ServoDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retur;
    }

    String sendMenssage(String msgToServer) throws IOException{
        
        String msgFromServer;
 
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(serverIP, serverPort); //making the socket connection
        } catch (IOException ex) {
            Logger.getLogger(ServoDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
          Logger.getLogger(ServoDriver.class.getName())
                  .log(Level.INFO, "Connected to:{0} on port:{1}", new Object[]{serverIP, serverPort});
          //OutputStream do Arduino-Server
          DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
          //BufferedReader para o Arduino-Server
          BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//

          outToServer.writeBytes(msgToServer);//Enviando a mensagem
          Logger.getLogger(ServoDriver.class.getName())
                  .log(Level.INFO, "Mensagem sendo enviada{0}", msgToServer);
          msgFromServer = inFromServer.readLine();//recebendo a resposta
          Logger.getLogger(ServoDriver.class.getName())
                  .log(Level.INFO, "Mensagem recebida{0}", msgFromServer);
 
          clientSocket.close();//Fechando o socket
          
          return msgFromServer;
    }
}
