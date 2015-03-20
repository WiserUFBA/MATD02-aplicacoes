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
#include <Servo.h>

// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:
byte mac[] = { 
  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xFE };
IPAddress ip(192,168,0,111);

// Initialize the Ethernet server library
// with the IP address and port you want to use 
// (port 80 is default for HTTP):
EthernetServer server(80);

int servo = 5;
int stateServo = 0;
Servo motor;

int buzzer = 3;
int stateBuzzer = 0;
int freq = 1500;

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
  motor.attach(servo);
  pinMode(buzzer,OUTPUT);
}

String valPag;

void loop() {
  // listen for incoming clients
  
 /* switch(stateBuzzer){
    case 2: int j;
            for(j=300; j<530; j+=j/20){
              freq=j;
              tone(buzzer, freq);
              delay(20);
            }
            delay(1000);
            for(j=530; j>300; j-=j/20){
              freq=j;
              tone(buzzer, freq);
              delay(20);
            }
            delay(1000);
            break;
    case 3: int i;
            for(i=400; i<500; i+=33){
              freq=i;
              tone(buzzer, freq);
              delay(20);
            }
            delay(2000);
            for(i=500; i>400; i-=33){
              freq=i;
              tone(buzzer, freq);
              delay(20);
            }
            delay(2000);
            break;
  }*/
  
  if(stateServo!=0){
    int i;
    for(i=20; i<160;i+=1*stateServo){
      motor.write(i);
      delay(10);
    }
    delay(1000);
    for(i=160; i>20; i-=1*stateServo){
      motor.write(i);
      delay(10);
    }
    delay(1000);
  }
  
  EthernetClient client = server.available();
  if (client) {
    Serial.println("Nova requisicao");
    while (client.connected()) {
      if (client.available()) {
        char c = client.read(); //Variável para armazenar os caracteres que forem recebidos
        valPag.concat(c); // Pega os valor após o IP do navegador ex: 192.168.1.2/0001
        Serial.write("Recebeu" + c);
        if(valPag.endsWith("b1")){
          freq=1500;
          tone(buzzer,freq);
          stateBuzzer = 1;
          client.println(stateBuzzer);
          
        }else if(valPag.endsWith("b0")){
          noTone(buzzer);
          stateBuzzer = 0;
          client.println(stateBuzzer);
            
        /*else if(valPag.startsWith("b2")){
          stateBuzzer = 2;
          client.println(stateBuzzer);
        }else if(valPag.startsWith("b3")){
          stateBuzzer = 3;
          client.println(stateBuzzer);*/
        }else if(valPag.endsWith("b4")){
          client.println(stateBuzzer); 
        }else if(valPag.endsWith("s0")){
          stateServo = 0;
          client.println(stateServo);
          
        }else if(valPag.endsWith("s1")){
          stateServo = 1;
          client.println(stateServo);
            
        /*}else if(valPag.startsWith("s2")){
          stateServo = 2;
          client.println(stateServo);
            
        }else if(valPag.startsWith("s3")){
          stateServo = 3;
          client.println(stateServo);
            
        }else if(valPag.startsWith("s4")){
          stateServo = 4;
          client.println(stateServo);*/
            
        }else if(valPag.endsWith("s5")){
          client.println(stateServo); 
        }
      }
    }
    // give the web browser time to receive the data
    delay(1);
    // close the connection:
    client.stop();
    Serial.println("client disconnected");
  }
}

