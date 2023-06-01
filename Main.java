import Serial.SerialComm;
import Serial.SimpleSerial;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Order order = new Order(1);


        Voorraad.getDatabasePlekken();
        SimpleSerial Serial = new SimpleSerial();
        // System.out.println(Arrays.toString(SerialComm.listPorts()));
//        Serial.setup();
        new Frame(Serial);
    }
}