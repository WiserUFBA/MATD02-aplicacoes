

#include <SPI.h>
#include <Ethernet.h>
#include <LiquidCrystal.h> //Inclui a biblioteca do LCD
 
LiquidCrystal lcd(9, 8, 5, 4, 3, 2); //Configura os pinos do Arduino para se comunicar com o LCD

// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:
byte mac[] = { 
  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress ip(10,3,200,210);

// Initialize the Ethernet server library
// with the IP address and port you want to use 
// (port 80 is default for HTTP):
EthernetServer server(80);

int ledAmarelo = 7;
int lebAzul = 6;

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
  lcd.begin(16, 2); //Inicia o LCD com dimensões 16x2(Colunas x Linhas)
  lcd.setCursor(0, 0); //Posiciona o cursor na primeira coluna(0) e na primeira linha(0) do LCD
}

  String valPag="";

int stateAzul = 0;
int stateAmarelo = 0;

void loop() {

  // listen for incoming clients
  EthernetClient client = server.available();
  if (client) {
    Serial.println("Nova requisicao");
    while (client.connected()) {
      
      if (client.available()) {
        char c = (char)client.read(); //Variável para armazenar os caracteres que forem recebidos
        valPag.concat(c); // Pega os valor após o IP do navegador ex: 192.168.1.2/0001
        Serial.print("Recebeu" + c);
        if(valPag.endsWith("y1")){
          digitalWrite(ledAmarelo,HIGH);
          stateAmarelo = 1;
          client.println(stateAmarelo);
          
        }else if(valPag.endsWith("y0")){
          digitalWrite(ledAmarelo,LOW);
          stateAmarelo = 0;
          client.println(stateAmarelo);
          
        }else if(valPag.endsWith("b1")){
          digitalWrite(lebAzul,HIGH);
          stateAzul = 1;
          client.println(stateAzul);
          
        }else if(valPag.endsWith("b0")){
          digitalWrite(lebAzul,LOW);
          stateAzul = 0;
          client.println(stateAzul);
          
        }else if(valPag.endsWith("b2")){
          client.println(stateAzul);
          
        }else if(valPag.endsWith("y2")){
          client.println(stateAmarelo);
        }else{
          lcd.setCursor(0, 1); //Posiciona o cursor na primeira coluna(0) e na segunda linha(1) do LCD
          String lcdStr = "";
          lcdStr.concat(valPag);
          while(client.available())
            lcdStr.concat((char)client.read()); //Escreve no LCD "LabdeGaragem"
          
          lcd.print(lcdStr);  
          client.println("OK");
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

