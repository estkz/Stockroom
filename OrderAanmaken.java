import javax.swing.*;
import java.awt.*;

public class OrderAanmaken extends JDialog {
    //variables
    JPanel orderPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    JLabel order = new JLabel("Order");
    JTextField orderNr = new JTextField();

    JComboBox<Integer> item1 = new JComboBox<>();
    JComboBox<Integer> item2 = new JComboBox<>();
    JComboBox<Integer> item3 = new JComboBox<>();

    JButton cancel = new JButton("cancel");
    JButton toevoegen = new JButton("toevoegen");


    OrderAanmaken(JFrame parentFrame, boolean m){
        //make frame
        super(parentFrame, m);
        setSize(new Dimension(600,400));
        setTitle("Order aanmaken");
        setResizable(false);
        setLayout(null);


        //add elements to JComboBox
        for(int i=0; i<Voorraad.getVoorraad().length; i++){
            if(Voorraad.getVoorraad()[i] != 0) {
                item1.addItem(Voorraad.getVoorraad()[i]);
            }
        }
        for(int i=0; i<Voorraad.getVoorraad().length; i++){
            if(Voorraad.getVoorraad()[i] != 0) {
                item2.addItem(Voorraad.getVoorraad()[i]);
            }
        }
        for(int i=0; i<Voorraad.getVoorraad().length; i++){
            if(Voorraad.getVoorraad()[i] != 0) {
                item3.addItem(Voorraad.getVoorraad()[i]);
            }
        }

        //assign values to elements
        orderPanel.setBounds(0,0,600,300);
        orderPanel.setLayout(new FlowLayout());
        orderPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        buttonPanel.setBounds(0,300,600,100);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        order.setPreferredSize(new Dimension(50,20));
        orderNr.setPreferredSize(new Dimension(50,20));
        item1.setPreferredSize(new Dimension(150,20));
        item2.setPreferredSize(new Dimension(150,20));
        item3.setPreferredSize(new Dimension(150,20));

        cancel.setPreferredSize(new Dimension(100,50));
        toevoegen.setPreferredSize(new Dimension(100,50));

        cancel.addActionListener(e -> {
            dispose();
        });

        toevoegen.addActionListener(e -> {
            int nr = Integer.parseInt(orderNr.getText());
            int i1 = (int ) item1.getSelectedItem();
            int i2 = (int ) item2.getSelectedItem();
            int i3 = (int ) item3.getSelectedItem();
            boolean res = Orders.setOrderCheck(nr, i1, i2, i3);

            if(res){
                JOptionPane.showMessageDialog(parentFrame, "Order succesvol toegevoegd", "SUCCES", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("gelukt");
                repaint();
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Order nummer bestaat al", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.out.println("niet gelukt");
            }

        });

        //add elements to panels
        buttonPanel.add(cancel);
        buttonPanel.add(toevoegen);

        orderPanel.add(order);
        orderPanel.add(orderNr);
        orderPanel.add(item1);
        orderPanel.add(item2);
        orderPanel.add(item3);

        //add panels to frames
        add(orderPanel);
        add(buttonPanel);

        setVisible(true);
    }
}
