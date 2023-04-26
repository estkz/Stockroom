import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    VoorraadPanel voorraad = new VoorraadPanel(this);
    Arm arm = new Arm(this);


    Frame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("HMI");
        setSize(new Dimension(750,750));
        setLayout(null);
        setResizable(false);


        add(voorraad);
        add(arm);

        setVisible(true);
    }
}
