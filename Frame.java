import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    VoorraadPanel voorraad = new VoorraadPanel(this);
    HomeUI arm = new HomeUI(this);


    Frame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("HMI");
        setSize(new Dimension(634,428));
        setLayout(null);
        setResizable(false);


        add(voorraad);
        add(arm);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
