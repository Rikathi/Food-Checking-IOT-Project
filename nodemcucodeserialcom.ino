#include<SoftwareSerial.h>
#include <ESP8266WiFi.h>
#include "FirebaseESP8266.h"
//#include <FirebaseESP8266HTTPClient.h>
#include <Wire.h>

#define FIREBASE_HOST "mq3sensingiot-default-rtdb.firebaseio.com"
#define FIREBASE_AUTH "L2182rbR39BFNsWcKPG2q4R0DVoBZ10UHL8lgnnc"

#define WIFI_SSID "Rikathi"
#define WIFI_PASSWORD "12345678"

#define SENSOR_PIN A0
WiFiClient wifiClient;
FirebaseData firebaseData;
const char* sensorPath = "/sensorData/value";
void setup()
{
  Serial.begin(9600);
   WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
    pinMode(D0,OUTPUT);
  }

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Firebase.reconnectWiFi(true);

}

void loop()
{
  if (Serial.available() > 0)
  {
    //char data = Serial.read();
    //char data;
    String buffer = Serial.readStringUntil('\n');
    Serial.println(buffer);
    float value = buffer.toFloat();
    buffer.trim();
    if (value > 115)
    {
      digitalWrite(D0,HIGH);
    }
    else
    {
      digitalWrite(D0,LOW);
    }
    if (Firebase.setString(firebaseData, sensorPath,buffer) ){
    Serial.println("Data sent to Firebase successfully!");
  } else {
    Serial.println("Failed to send data to Firebase.");
    Serial.println("Reason: " + firebaseData.errorReason());
  }
    
  }
}