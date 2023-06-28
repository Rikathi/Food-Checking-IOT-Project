#define MQ3pin1 A0
#define MQ3pin2 A2

float sensorValue;  //variable to store sensor value
#include<SoftwareSerial.h>

SoftwareSerial mySerial(10, 11);
void setup() {
	Serial.begin(9600); // sets the serial port to 9600
	Serial.println("MQ3 warming up!");
	 // allow the MQ3 to warm up
}

void loop() {
	float sensorValue1 = analogRead(MQ3pin1); // read analog input pin 0
  float sensorValue2 = analogRead(MQ3pin2);
//  Serial.print("Sensor Value1: ");
//  Serial.println(sensorValue1);
//  Serial.print("Sensor Value2: ");
//  Serial.println(sensorValue2);

//  Serial.print("Average:");
  float Average = (sensorValue1+sensorValue2)/2.5;
  Serial.print(Average);
  Serial.println();
	delay(1000); // wait 2s for next reading
}