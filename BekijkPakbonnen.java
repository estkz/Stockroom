import javax.swing.*;
import java.awt.*;

public class BekijkPakbonnen extends JDialog {
    JFrame parentFrame;
    BekijkPakbonnen(JFrame frame, boolean m){
        super(frame, m);
        this.parentFrame = frame;

        setSize(new Dimension(600,600));
        setTitle("Bekijk pakbonnen");
        setLayout(new FlowLayout());
        setResizable(false);


        setLocationRelativeTo(null);
        setVisible(true);
    }
}
