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

// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:
byte mac[] = { 
  0xDE, 0xAD, 0xBE, 0xDE, 0xAA, 0xE0 };
IPAddress ip(192,168,0,123);

// Initialize the Ethernet server library
// with the IP address and port you want to use 
// (port 80 is default for HTTP):
EthernetServer server(80);

//VARS
//the time we give the sensor to calibrate (10-60 secs according to the datasheet)
int calibrationTime = 30;        

//the time when the sensor outputs a low impulse
long unsigned int lowIn;         

//the amount of milliseconds the sensor has to be low 
//before we assume all motion has stopped
long unsigned int pause = 5000;  

boolean lockLow = true;
boolean takeLowTime;  

int pirPin = 7;    //the digital pin connected to the PIR sensor's output
int ledPin = 13;
int pino_d = 6; //Pino ligado ao D0 do sensor
int pino_a = A5; //Pino ligado ao A0 do sensor

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
   
  pinMode(pino_d, INPUT);
  pinMode(pino_a, INPUT);

  pinMode(pirPin, INPUT);
  pinMode(ledPin, OUTPUT);
  digitalWrite(pirPin, LOW);

  //give the sensor some time to calibrate
  Serial.print("calibrating sensor ");
    for(int i = 0; i < calibrationTime; i++){
      Serial.print(".");
      delay(1000);
      }
    Serial.println(" done");
    Serial.println("SENSOR ACTIVE");
    //delay(50);
}

String valPag;
int estadoPresenca = 0;
int estadoChuva = 0;

void loop() {
    // listen for incoming clients
    EthernetClient client = server.available();
    if (client) {
      Serial.println("Nova requisicao");
      while (client.connected()) {
      
        if (client.available()) {
          char c = client.read(); //Variável para armazenar os caracteres que forem recebidos
          valPag.concat(c); // Pega os valor após o IP do navegador ex: 192.168.1.2/0001
          Serial.write("Recebeu" + c);
          
          if(valPag.endsWith("p")){ //se for sensor de presença
            estadoPresenca = digitalRead(pirPin);
          
            if(digitalRead(pirPin) == HIGH){
              digitalWrite(ledPin, HIGH);   //the led visualizes the sensors output pin state
              if(lockLow){  
                //makes sure we wait for a transition to LOW before any further output is made:
                lockLow = false;            
                Serial.println("---");
                Serial.print("motion detected at ");
                Serial.print(millis()/1000);
                Serial.println(" sec"); 
                delay(50);
              }         
              takeLowTime = true;
            }

            if(digitalRead(pirPin) == LOW){       
              digitalWrite(ledPin, LOW);  //the led visualizes the sensors output pin state

              if(takeLowTime){
                lowIn = millis();          //save the time of the transition from high to LOW
                takeLowTime = false;       //make sure this is only done at the start of a LOW phase
              }

              //if the sensor is low for more than the given pause, 
              //we assume that no more motion is going to happen
              if(!lockLow && millis() - lowIn > pause){  
                //makes sure this block of code is only executed again after 
                //a new motion sequence has been detected
                lockLow = true;                        
                Serial.print("motion ended at ");      //output
                Serial.print((millis() - pause)/1000);
                Serial.println(" sec");
                delay(50);
              }
            }
            client.println(estadoPresenca);
          }
          //se for sensor de chuva
          if(valPag.endsWith("c")){
            // read the input on analog pin 0:
            //int sensorValue = analogRead(A0); 
	    
	    estadoChuva = digitalRead(pino_d);		
	
            if(digitalRead(pino_d) == LOW){
	      Serial.println(estadoChuva);
              Serial.println("Chuva");
            }

            if(digitalRead(pino_d) == HIGH){
	      Serial.println(estadoChuva);
              Serial.println("Sol");
            }

            Serial.println(estadoChuva);
            delay(100);

	    client.println(estadoChuva);
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

