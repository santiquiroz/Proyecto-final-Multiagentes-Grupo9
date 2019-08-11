bool encendido = false;
int input;
void setup() {
  // Declaramos que utilizaremos el pin como salida
  pinMode(2, OUTPUT);
  //Iniciamos la comunicación con el puerto serie
  Serial.begin(9600);
}

void loop() {
  delay(5000);
  
  digitalWrite(2, HIGH);
  delay(5000);
  //En caso que haya información en el Serial Port, se entra en esta estructura
  if (Serial.available() > 0) {
    //Se lee la información entrante en el Serial Port
    input = Serial.read();
    //while(Serial.available()) {Serial.read();} 
    if ((input == 1)&&(!encendido)) {
      //Si el valor de input es 1, se enciende el led
      digitalWrite(2, HIGH);
      encendido = true;
      
        // Se envía el mensaje "Encendido", seguido de un símbolo de @
        // y el tiempo de ejecución en milisegundos, obtenido a través de millis()
      
      String output = "Encendido @";
      output += millis();
      output += "ms";
      //Se imprime el mensaje en el puerto serie
      Serial.println(output);
    }
    if((input == '0')&&(encendido))
    {
      //Si el valor de input es diferente de 1, se apaga el LED
      digitalWrite(2, LOW);
      
         //Se envía el mensaje "Apagado", seguido de un símbolo de @
         //y el tiempo de ejecución en milisegundos, obtenido a través de millis()
      
      String output = "Apagado @";
      output += millis();
      output += "ms";
      //Se imprime el mensaje en el puerto serie
      Serial.println(output);
    }
    
  }
}
