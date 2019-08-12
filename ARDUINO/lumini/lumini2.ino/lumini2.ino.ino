int gente = -1;
float lumin = 0.0;
float correo = 0.0;
bool encendido = false;

const long A = 1000;     //Resistencia en oscuridad en KΩ
const int B = 15;        //Resistencia a la luz (10 Lux) en KΩ
const int Rc = 10;       //Resistencia calibracion en KΩ
const int LDRPin = A0;   //Pin del LDR

int V;
int ilum;

void setup() {
  // activando la conexion por serial al agente
  Serial.begin(9600);

  //configurando el puerto A2 para entrada de datos para luminisencia
  pinMode(A2, INPUT);
  pinMode(2, INPUT);
  pinMode(A4, INPUT);

  borrarSerial();
}

void loop() {
    borrarSerial();
    //leyendo e imprimiendo los datos por medio del puerto serial.
    lumin = analogRead(A2);
    gente = digitalRead(2);
    correo = analogRead(A4);
    String datosImpresion = "";
    datosImpresion = datosImpresion + "luminosidad=" + lumin + "," + "gente:" + gente + ",";
    if (correo > 1021) {
      datosImpresion = datosImpresion + "correo:" + 1;
    }
    else {
      datosImpresion = datosImpresion + "correo:" + 0;
    }
    Serial.println(datosImpresion);
    delay(2000);
    borrarSerial();
}

void borrarSerial() {
    Serial.flush();
    while (Serial.available()) {
      Serial.read();
    }
  }
