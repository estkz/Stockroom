import Serial.SimpleSerial;

public class Main {
    public static void main(String[] args) {
        Voorraad.getDatabasePlekken();

        SimpleSerial Serial = new SimpleSerial();
        Serial.setup();

        new Frame(Serial);
    }
}