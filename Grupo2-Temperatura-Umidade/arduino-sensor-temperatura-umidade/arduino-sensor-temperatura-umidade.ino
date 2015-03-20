#include <SPI.h>
#include <Ethernet.h>
#include "DHT.h"

#define DHTPIN 2
#define DHTTYPE DHT11

DHT dht(DHTPIN, DHTTYPE);

// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:
byte mac[] = { 
  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xFA };
IPAddress ip(192,168,0,182);

// Initialize the Ethernet server library
// with the IP address and port you want to use 
// (port 80 is default for HTTP):
EthernetServer server(80);

void setup() {
 // Open serial communications and wait for port to open:
  Serial.begin(9600);

  Serial.println("DHTxx test!");
  dht.begin();


  // start the Ethernet connection and the server:
  Ethernet.begin(mac, ip);
  server.begin();
  Serial.print("server is at ");
  Serial.println(Ethernet.localIP());
}

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
        Serial.write("Recebeu " + c);          
        if(valPag.endsWith("g1")){
          //delay(2000);
          float h = dht.readHumidity();
          float t = dht.readTemperature();
          float f = dht.readTemperature(true);

          if (isnan(h) || isnan(t) || isnan(f)) {
            Serial.println("Failed to read from DHT sensor!");
            return;
          }

          float hi = dht.computeHeatIndex(f, h);

          Serial.print(t);

          client.println(t);
        }
        else if(valPag.endsWith("g2")){
          //delay(2000);
          float h = dht.readHumidity();
          float t = dht.readTemperature();
          float f = dht.readTemperature(true);

          if (isnan(h) || isnan(t) || isnan(f)) {
            Serial.println("Failed to read from DHT sensor!");
            return;
          }

          float hi = dht.computeHeatIndex(f, h);

          Serial.print(h);

          client.println(h);
        }
      }
    }
    // give the web browser time to receive the data
    delay(10);
    // close the connection:
    client.stop();
    Serial.println("client disconnected");
  }
}

