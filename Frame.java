import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    VoorraadPanel voorraad = new VoorraadPanel();

    Frame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("HMI");
        setSize(new Dimension(750,750));
        setLayout(null);
        setResizable(false);


        add(voorraad);

        setVisible(true);
    }
}
