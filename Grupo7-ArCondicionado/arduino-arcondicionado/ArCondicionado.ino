//#include <IRremoteInt.h>
#include <IRremote.h>

//#include <IRremote.h>




/*
  Web Server
 
 A simple web server that shows the value of the analog input pins.
 using an Arduino Wiznet Ethernet shield. 
 
 Circuit:
 * Ethernet shield attached to pins 10, 11, 12, 13
 * Analog inputs attached to pins A0 through A5 (optional)
 
 created 18 Dec 2009
 by David A. Mellis
 modified 9 Apr 2012
 by Tom Igoe
 
 */

#include <SPI.h>
#include <Ethernet.h>
#include <IRremote.h>



// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:
byte mac[] = { 
  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xFD };
IPAddress ip(192,168,0,102);

// Initialize the Ethernet server library
// with the IP address and port you want to use 
// (port 80 is default for HTTP):
EthernetServer server(80);
IRsend irsend;
IRsend ir;
int ledAmarelo = 7;
int lebAzul = 6;
//char c='0';

void setup() {
 // Open serial communications and wait for port to open:
  Serial.begin(9600);
   while (!Serial) {
    ; // wait for serial port to connect. Needed for Leonardo only
  }


  // start the Ethernet connection and the server:
  Ethernet.begin(mac, ip);
  server.begin();
  Serial.print("server is at ");
  Serial.println(Ethernet.localIP());
  pinMode(ledAmarelo,OUTPUT);
  pinMode(lebAzul,OUTPUT);
}


int stateAzul = 0;
int stateAmarelo = 0;
int temp = 0;
void loop() {
  // listen for incoming clients
String valPag;
String valPag2;
String valPag3;
char c;
int u1;
  EthernetClient client = server.available();
  if (client) {
    Serial.println("Nova requisicao");
    
    
    while (client.connected()) {
      
      if (client.available()) {
        Serial.println("tem dados");
        //while(c!='-1'){
        c = client.read();
         //Variável para armazenar os caracteres que forem recebidos
        valPag.concat(c); // Pega os valor após o IP do navegador ex: 192.168.1.2/0001  
        //Serial.println('ta aqui',);  
      //}
        Serial.println(valPag);  
    
        //Serial.write("Recebeu"+ c);
        //Serial.println("Recebeu"+ c);
        Serial.println("chegou  valPag "+ valPag);
        if(valPag.endsWith("y1")){
          //while(client.available()){
            //c=client.read();
            //valPag2.concat(c);
            
          //}
          //temp = valPag2;
          Serial.println("valPag2u"+valPag2);
          Serial.println("tempu"+temp);
          unsigned char t=20;        
          irsend.sendTemp(18);
//          irsend.desligar();
          //digitalWrite(ledAmarelo,HIGH);
          //stateAmarelo = 1;
          String r="10";
          int u = r.toInt();
          Serial.print("valor de u=");
          Serial.println(u);
          Serial.println("valPag2u"+valPag2);
          //Serial.println("tempu"+temp);
          Serial.println("ligou o ar condicionado"); 
          client.println("ok");
          
        }else if(valPag.endsWith("y0")){
          //while(client.available()){
            //c=client.read();
            //valPag3.concat(c);
            
          //}
          //temp = valPag2;
          Serial.println("valPag2u"+valPag3);
          Serial.println("tempu"+temp);
          unsigned char t=10;        
          irsend.desligar();
//          irsend.sendTemp(t);
          //digitalWrite(ledAmarelo,HIGH);
          //stateAmarelo = 1;
          Serial.println("valPag2u"+valPag2);
          //Serial.println("tempu"+temp);
          Serial.println("desligou o ar condicionado"); 
            client.println("ok1");
        
      
        }else if(valPag.endsWith("b1")){
          
          //String valPag2;
          //while (client.available()) {
        //Serial.println("tem dados do valor da temperatura");
        //c = client.read();
         //Variável para armazenar os caracteres que forem recebidos
        //valPag2.concat(c); // Pega os valor após o IP do navegador ex: 192.168.1.2/0001  
        //}
           int c1=((int)client.read()-48)*10; 
           int c2= (int)client.read()-48;
           
          //u1=valPag2.toInt();
          Serial.println("agora vai mudar de temperatura");
          String r="10";
          int u = r.toInt();
          //int u1 = valPag2.toInt();
          Serial.print("valor de u=");
          Serial.println(c1+c2);
          
          unsigned char t=22;
          irsend.sendTemp(c1+c2);
          client.println("mudou temp");
          
        }else if(valPag.endsWith("b0")){
          digitalWrite(lebAzul,LOW);
          stateAzul = 0;
          //client.println(stateAzul);
          
        }else if(valPag.endsWith("b2")){
          client.println(stateAzul);
          
        }else if(valPag.endsWith("y2")){
          client.println(stateAmarelo);
        }
      }
        //Serial.println("fim dos dados");
       //Serial.print("var: ");
        //Serial.println(valPag);
    }
    // give the web browser time to receive the data
    delay(1);
    // close the connection:
    client.stop();
    Serial.println("client disconnected");
  }
}

