#include <SPI.h>
#include <Ethernet.h>

// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:
byte mac[] = { 
  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress ip(192,168,0,150);

// Initialize the Ethernet server library
// with the IP address and port you want to use 
// (port 80 is default for HTTP):
EthernetServer server(80);

int fechadura = 7;

void setup() {
 // Open serial communications and wait for port to open:
  Serial.begin(9600);

  // start the Ethernet connection and the server:
  Ethernet.begin(mac, ip);
  server.begin();
  Serial.print("server is at ");
  Serial.println(Ethernet.localIP());
  pinMode(fechadura,OUTPUT);
}

int stateFechadura = 0;
int stateHumidade;
String valPag;
        
void loop() {
  // listen for incoming clients
  EthernetClient client = server.available();
  if (client) {
    Serial.println("Nova requisicao");
    
	while (client.connected()) {
      if (client.available()) {
       char c = client.read(); //Variável para armazenar os caracteres que forem recebidos
        valPag.concat(c); // Pega os valor após o IP do navegador ex: 192.168.1.2/0001
        Serial.print(c);
        
		if(valPag.endsWith("f1")){
          digitalWrite(fechadura,HIGH);
          stateFechadura = 1;
          client.println(stateFechadura);

        }else if(valPag.endsWith("f0")){
          digitalWrite(fechadura,LOW);
          stateFechadura = 0;
          client.println(stateFechadura);

        }else if(valPag.endsWith("fc")){
          client.println(stateFechadura);
        }else if(valPag.endsWith("u")){
            stateHumidade = analogRead(A0);
            Serial.print(stateHumidade);
	    if(stateHumidade <= 300){
		client.println('a');
	    }
	    else if(stateHumidade > 300 && stateHumidade <= 700){
		client.println('u');
	    }
	    else{
		client.println('s');
	  }
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