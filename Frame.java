import Serial.SimpleSerial;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    SimpleSerial Serial;


    Frame(SimpleSerial Serial){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("HMI");
        setSize(new Dimension(634,428));
        setLayout(null);
        setResizable(false);

        this.Serial = Serial;

        VoorraadPanel voorraad = new VoorraadPanel(this);
        HomeUI arm = new HomeUI(this, Serial);

        add(voorraad);
        add(arm);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
