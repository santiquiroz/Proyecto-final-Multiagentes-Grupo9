float analogPin = A2;
float lumin = 0.0;  
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(A2,INPUT);  

}

void loop() {
  // put your main code here, to run repeatedly:
  lumin = analogRead(A2);
  Serial.println(lumin);
  delay(50);
}
