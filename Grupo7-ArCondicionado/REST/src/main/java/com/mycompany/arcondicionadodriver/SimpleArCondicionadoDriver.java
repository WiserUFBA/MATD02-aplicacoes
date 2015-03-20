/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arcondicionadodriver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class SimpleArCondicionadoDriver implements InterfaceArCondicionado{

    private  String serverIP;
    private  int serverPort;
    private int temperaturaAtual; //isso  não vai existir depois do drive corretamente implementado
    private boolean ligado;
    
    public SimpleArCondicionadoDriver(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        temperaturaAtual = 10;
        ligado=true;
    }
    
    
    
    
    @Override
    public void on() {
        String[] strings = new String[2];
        strings[0] = "y1"; // ligar o ar condicionado
        //strings[1] = "18"; // temperatura para ligar
        
        try {
           System.out.println("teste22");
            String resp = sendMenssage(strings,0);
           //System.out.println("resposta "+resp);
            System.out.println("teste");
        } catch (IOException ex) {
            Logger.getLogger(SimpleArCondicionadoDriver.class.getName()).log(Level.SEVERE, null, ex);
        } 
        System.out.println("terminou");
    }

    @Override
    public void off() {
       String[] strings = new String[2];
        strings[0] = "y0"; // ligar o ar condicionado
        //strings[1] = "10"; // temperatura para ligar
        try {
           System.out.println("teste25");
            String resp = sendMenssage(strings,0);
           //System.out.println("resposta "+resp);
            System.out.println("teste");
        } catch (IOException ex) {
            Logger.getLogger(SimpleArCondicionadoDriver.class.getName()).log(Level.SEVERE, null, ex);
        } 
        System.out.println("terminou o off");
    
    }

    @Override
    public int consultarTemperatura() {
       return temperaturaAtual;
        //por enquanto
    
    }

    @Override
    public void mudarTemperatura(int mudarTemperaturaPara) {
        temperaturaAtual = mudarTemperaturaPara;
         String[] strings = new String[2];
        strings[0] = "b1"; // mudar temperatura do ar condicionado
        strings[1] = String.valueOf(mudarTemperaturaPara); // temperatura para ligar
        
        //strings[0] = strings[0]+strings[1];
        try {
           System.out.println("teste22");
            String resp = sendMenssage(strings,1);
           //System.out.println("resposta "+resp);
            System.out.println("teste");
        } catch (IOException ex) {
            Logger.getLogger(SimpleArCondicionadoDriver.class.getName()).log(Level.SEVERE, null, ex);
        } 
        System.out.println("terminou de mudar temperatura");
        this.temperaturaAtual = mudarTemperaturaPara;
    
    }
    

     private String sendMenssage(String[] msgToServer,int v) throws IOException{
        
        String msgFromServer;
 
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(serverIP, serverPort); //making the socket connection
        } catch (IOException ex) {
            Logger.getLogger(SimpleArCondicionadoDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
          Logger.getLogger(SimpleArCondicionadoDriver.class.getName())
                  .log(Level.INFO, "Connected to:{0} on port:{1}", new Object[]{serverIP, serverPort});
          DataOutputStream outToServer;
          //if(clientSocket!= null)  
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
          //else
              //System.out.println("eh nulo");
//BufferedReader para o Arduino-Server
          BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//

         if(v==0)
            outToServer.writeBytes(msgToServer[0]);//Enviando a mensagem
         else
            outToServer.writeBytes(msgToServer[0]+msgToServer[1]);//Enviando a mensagem
         //outToServer.writeBytes(msgToServer[1]);//Enviando a mensagem
          Logger.getLogger(SimpleArCondicionadoDriver.class.getName())
                  .log(Level.INFO, "Mensagem sendo enviada{0}", msgToServer);
          msgFromServer = inFromServer.readLine();//recebendo a resposta
          Logger.getLogger(SimpleArCondicionadoDriver.class.getName())
                  .log(Level.INFO, "Mensagem recebida{0}", msgFromServer);
 
          clientSocket.close();//Fechando o socket
          
          return msgFromServer;
    }


}
