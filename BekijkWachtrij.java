import javax.swing.*;
import java.awt.*;

public class BekijkWachtrij extends JDialog {
    JFrame parentFrame;
    BekijkWachtrij(JFrame frame, boolean m) {
        super(frame, m);
        this.parentFrame = frame;

        setSize(new Dimension(600,600));
        setTitle("Bekijk Wachtrij");
        setLayout(new FlowLayout());
        setResizable(false);

        JFrame bekijkWachtrij = new JFrame("Bekijk Wachtrij");
        JPanel panelID = new JPanel();
        panelID.setBounds(75,80,450,35);
        panelID.setBackground(Color.lightGray);
        panelID.setVisible(true);
        bekijkWachtrij.add(panelID);
        bekijkWachtrij.setSize(600,600);
        bekijkWachtrij.setLayout(null);
        bekijkWachtrij.setVisible(true);

        setLocationRelativeTo(null);
        setVisible(true);

        // [+] Plek   -   [+] orderID   -   [+] aantalItemsInOrder
        //                                              |
        //                                               -----> [+] itemID   -   [+] itemType
    }
}
