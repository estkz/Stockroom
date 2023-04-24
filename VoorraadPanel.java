import javax.swing.*;
import java.awt.*;


public class VoorraadPanel extends JPanel {
    Voorraad voorraad = new Voorraad();

    VoorraadPanel() {
        setBounds(0, 0, 400, 400);
        setBackground(Color.lightGray);
        setLayout(new GridLayout(5, 5));

        voorraad.setVoorraad(20, 10);
        voorraad.setVoorraad(20, 14);
        voorraad.setVoorraad(1, 4);
        voorraad.setVoorraad(19, 3);
        voorraad.setVoorraad(3, 5);

        drawVoorraad();
    }

    void drawVoorraad(){
        for(int i=0; i<25; i++) {
            if (voorraad.getVoorraad()[i] != 0) {
                JLabel label = new JLabel("Item " + voorraad.getVoorraad()[i]);
                label.setBorder(BorderFactory.createLineBorder(Color.black));
                label.setHorizontalAlignment(JLabel.CENTER);
                add(label);
            } else {
                JLabel label = new JLabel("");
                label.setBorder(BorderFactory.createLineBorder(Color.black));
                add(label);
            }
        }
    }
}




