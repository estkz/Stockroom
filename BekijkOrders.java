import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class BekijkOrders extends JDialog {

    // Vars
    int orderAmount = 10;

    // Components
    JButton newOrder = new JButton("Nieuwe order");


    JFrame parentFrame;

    JComboBox orderCombo = new JComboBox();


    JList orderList = new JList();

    // Functions

    /*
    void displayOrdersToCombo() {
        for(int i = 1; i < orderAmount; i++) {

        }
    }*/



    BekijkOrders(JFrame parentFrame, boolean m){

        // Create dialog window
        super(parentFrame,  m);
        this.parentFrame = parentFrame;

        setSize(new Dimension(600,600));
        setTitle("Order weergave");
        setLayout(new FlowLayout());
        setResizable(false);

        // Component styling
        orderCombo.setPreferredSize(new Dimension(250, 30));

        // Adding components
        add(orderCombo);
        add(orderList);
        add(newOrder);

        orderList.setPreferredSize(new Dimension(550,400));
        orderList.setLayout(new GridLayout(5,2));
        orderList.setBorder(BorderFactory.createLineBorder(Color.black));

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
