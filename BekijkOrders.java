import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class BekijkOrders extends JDialog {
    final int totaalAantalOrders = 10;

    JButton newOrder = new JButton("Nieuwe order");
    JButton updateOrders = new JButton("Update order weergave");
    JPanel orderList = new JPanel();
//    JScrollPane scroll = new JScrollPane(orderList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JFrame parentFrame;

    BekijkOrders(JFrame parentFrame, boolean m){
        super(parentFrame,  m);
        this.parentFrame = parentFrame;

        setSize(new Dimension(600,600));
        setTitle("Order weergave");
        setLayout(new FlowLayout());
        setResizable(false);

        orderList.setPreferredSize(new Dimension(550,400));
        orderList.setLayout(new GridLayout(totaalAantalOrders+1,2));
        orderList.setBorder(BorderFactory.createLineBorder(Color.black));


        orderList.add(new JLabel("Order nr"));
        orderList.add(new JLabel("items"));

        //display orders
        displayOrders();

        newOrder.addActionListener(e -> {
            new OrderAanmaken(parentFrame, true);
        });

        updateOrders.addActionListener(e -> {
            displayOrders();
        });


//        add(scroll);
        add(orderList);
        add(newOrder);
        add(updateOrders);

        setVisible(true);
    }

    void displayOrders(){
        StringBuilder orderInfo;
        JLabel order;
        for(int i=1; i<=totaalAantalOrders; i++){
            orderList.add(new JCheckBox(String.valueOf(i)));

            orderInfo = new StringBuilder();

            if(Orders.getOrders()[i] != null) {
                orderInfo.append(Arrays.toString(Orders.getOrders()[i]));

            } else{
                orderInfo.append("lege order");

            }

            order = new JLabel(String.valueOf(orderInfo));

            if(Orders.getOrders()[i] != null){
                order.setBackground(Color.green);
            } else {
                order.setBackground(Color.red);
                order.setForeground(Color.white);
            }

            orderList.add(order);
            order.setOpaque(true);
        }
    }
}
