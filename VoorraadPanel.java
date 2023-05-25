import javax.swing.*;
import java.awt.*;

public class VoorraadPanel extends JPanel{
    JFrame parentFrame;
    static Database db = new Database();

    static JPanel[] panels = new JPanel[25];
    static JLabel[] nr = new JLabel[25];
    static JLabel[] labels = new JLabel[25];

    VoorraadPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setBounds(0, 0, 400, 400);
        setBackground(Color.lightGray);
        setLayout(new GridLayout(5, 5));

        for (int i = 0; i < 25; i++) {
            panels[i] = new JPanel();
            panels[i].setLayout(new GridLayout(2,1));
            panels[i].setBackground(Color.lightGray);
            panels[i].setBorder(BorderFactory.createLineBorder(Color.black));
            add(panels[i]);

            nr[i] = new JLabel("<html><h5 style="+(char)34+"color:gray;"+(char)34+">"+(i+1)+"</h5></html>");
            nr[i].setVerticalAlignment(SwingConstants.TOP);
            nr[i].setHorizontalAlignment(SwingConstants.LEFT);

            labels[i] = new JLabel();
            labels[i].setHorizontalAlignment(SwingConstants.CENTER);
            labels[i].setVerticalAlignment(SwingConstants.TOP);

            panels[i].add(nr[i]);
            panels[i].add(labels[i]);
        }
        drawVoorraad();
    }


    public static void drawVoorraad() {
        int[] voorraadArray = Voorraad.getVoorraad();

        for(int i=0; i<25; i++) {
            int voorraadItem = voorraadArray[i];

            if (voorraadItem != 0) {
                labels[i].setText("<html><h5>"+db.getItems()[voorraadItem-1]+"</h5></html>");
            } else {
                labels[i].setText("");
            }
        }
    }
}




