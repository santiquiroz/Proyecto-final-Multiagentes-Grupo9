float analogPin = A2;
float lumin = 0.0;  
void setup() {
  // activando la conexion por serial al agente
  Serial.begin(9600);
  while(!Serial){
    ;
  }
  //configurando el puerto A2 para entrada de datos para luminisencia
  pinMode(A2,INPUT);  

}

void loop() {
  // 
  if (Serial.available() > 0){
    //leyendo entradas desde algun agente
    byte incomingByte = Serial.read();
    if(incomingByte != -1){
      Serial.println("Incoming: "+incomingByte);
    }
  }
  lumin = analogRead(A2);
  //Serial.println(lumin);
  delay(50);
}
