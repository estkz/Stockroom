import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    VoorraadPanel voorraad = new VoorraadPanel(this);
    Arm arm = new Arm(this);


    Frame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("HMI");
        setSize(new Dimension(620,428));
        setLayout(null);
        setResizable(false);


        add(voorraad);
        add(arm);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
