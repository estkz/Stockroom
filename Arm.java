import javax.swing.*;
import java.awt.*;

public class Arm extends JPanel {
    JButton bekijkOrders = new JButton("Bekijk orders");
    JFrame parentFrame;


    Arm(JFrame parentFrame){
        this.parentFrame = parentFrame;

        setBounds(500,0,200,400);
        setBackground(Color.lightGray);
        setLayout(new FlowLayout());

        add(bekijkOrders);

        bekijkOrders.addActionListener(e -> {
            new BekijkOrders(parentFrame, true);
        });

    }
}
