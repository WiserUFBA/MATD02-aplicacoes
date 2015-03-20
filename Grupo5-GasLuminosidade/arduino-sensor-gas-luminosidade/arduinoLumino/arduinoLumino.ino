/*
 * Entrada analógica
 * le o valor do sensor de luminosidade e envia valor para o computador
 */
 
int sensorPin = 0;    // selecione o pino de entrada ao potenciômetro
int val;
 
void setup() {
 Serial.begin(9600);
}
 
void loop() {
 val = analogRead(sensorPin);    // ler o valor do potenciômetro
 Serial.println(val);            //emvia valor para o pc
 delay(1000);                    //aguarda 1 segundo
}
