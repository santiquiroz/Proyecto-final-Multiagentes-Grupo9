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

String input ;
int inputUtil;
int hola = 1;


void setup() {
  // activando la conexion por serial al agente
  Serial.begin(9600);

  //configurando el puerto A2 para entrada de datos para luminisencia
  pinMode(A2, INPUT);
  pinMode(2, INPUT);
  pinMode(A4, INPUT);

  pinMode(7, OUTPUT);
  
  borrarSerial();
  //hola.trim();
}

void loop() {
  /* 1 significa entregar datos
     0 significa encender o apagar la lampara
  */
  if (Serial.available() > 0) {
    input = Serial.readString();
    inputUtil = input.toInt();


    if (inputUtil == 1) {
      borrarSerial();
      entregarDatos();
      borrarSerial();
    }
    if (inputUtil == 0) {
        switchLampara();
    }
    else {
      Serial.println("error");
    }

    borrarSerial();
  }
}

void borrarSerial() {
  Serial.flush();
  while (Serial.available()) {
    Serial.read();
  }
}

void entregarDatos() {

  borrarSerial();
  //leyendo e imprimiendo los datos por medio del puerto serial.
  lumin = analogRead(A2);
  gente = digitalRead(2);
  correo = analogRead(A4);
  //Serial.print("luminosidad: ");
  //V = analogRead(A2);

  //ilum = ((long)(1024-V)*A*10)/((long)B*Rc*V);  //usar si LDR entre GND y A0
  //ilum = ((long)V*A*10)/((long)B*Rc*(1024-V));    //usar si LDR entre A0 y Vcc (como en el esquema anterior)

  //Serial.println(ilum);
  //Serial.println(ilum*(100.0/1023.0));
  ////Serial.println(lumin);
  //Serial.print("gente: ");
  //Serial.println(gente);
  //Serial.print("correo: ");

  String datosImpresion = "";
  datosImpresion = datosImpresion + "luminosidad=" + lumin + "," + "gente:" + gente + ",";

  if (correo > 1021) {
    //Serial.println(1);
    datosImpresion = datosImpresion + "correo:" + 1;
  }
  else {
    //Serial.println(0);
    datosImpresion = datosImpresion + "correo:" + 0;
  }
  Serial.println(datosImpresion);
}

void switchLampara() {
  
  if(encendido){
    Serial.println("apagando");
    digitalWrite(7,HIGH);
    encendido = false;
  }
  else{
    Serial.println("encendiendo");
    digitalWrite(7,LOW);
    encendido = true;
    
  }
  
}
