import javax.swing.*;
import java.awt.*;

public class VoorraadPanel extends JPanel{
    JFrame parentFrame;

    static JLabel[] labels = new JLabel[25];

    VoorraadPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setBounds(0, 0, 400, 400);
        setBackground(Color.lightGray);
        setLayout(new GridLayout(5, 5));

        for (int i = 0; i < 25; i++) {
            labels[i] = new JLabel();
            labels[i].setBorder(BorderFactory.createLineBorder(Color.black));
            labels[i].setHorizontalAlignment(SwingConstants.CENTER);
            add(labels[i]);
        }

        drawVoorraad();



    }


    public static void drawVoorraad() {
        int[] voorraadArray = Voorraad.getVoorraad();

        for(int i=0; i<25; i++) {
            int voorraadItem = voorraadArray[i];

            if (voorraadItem != 0) {
                labels[i].setText("Item: "+voorraadItem);
            } else {
                labels[i].setText("");
            }
        }
    }
}




