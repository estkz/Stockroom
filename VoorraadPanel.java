import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VoorraadPanel extends JPanel implements ActionListener {
    JFrame parentFrame;

    VoorraadPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setBounds(0, 0, 400, 400);
        setBackground(Color.lightGray);
        setLayout(new GridLayout(5, 5));
        drawVoorraad();
    }


    public void drawVoorraad() {
        for(int i=0; i<25; i++) {
            if (Voorraad.getVoorraad()[i] != 0) {
                JLabel label = new JLabel("Item " + Voorraad.getVoorraad()[i]);
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}




