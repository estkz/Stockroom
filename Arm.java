import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Arm extends JPanel implements ActionListener {
    Database db = new Database();

    JButton bekijkOrders = new JButton("Bekijk orders");
    JButton bekijkPakbonnen = new JButton("Bekijk pakbonnen");
    JFrame parentFrame;

    JPanel buttonPanel1 = new JPanel();
    JPanel buttonPanel2 = new JPanel();

    JTextField item = new JTextField();
    JTextField plek = new JTextField();
    JButton voorraadAanpassen = new JButton("Voorraad aanpassen");


    Arm(JFrame parentFrame){
        this.parentFrame = parentFrame;

        setBounds(420,0,200,400);
        setBackground(Color.lightGray);
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));

        voorraadAanpassen.addActionListener(this);

        buttonPanel1.setLayout(new GridLayout(6,1));
        buttonPanel2.setLayout(new GridLayout(6,1));
        buttonPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
        buttonPanel2.setBorder(BorderFactory.createLineBorder(Color.black));

        buttonPanel1.setPreferredSize(new Dimension(180, 180));
        buttonPanel2.setPreferredSize(new Dimension(180, 180));

        buttonPanel1.add(new JLabel("<html><B>"+"VOORRAAD TOEVOEGEN"+"</B></html>"));
        buttonPanel1.add(new JLabel("Item nr."));
        buttonPanel1.add(item);
        buttonPanel1.add(new JLabel("Plek nr."));
        buttonPanel1.add(plek);
        buttonPanel1.add(voorraadAanpassen);

        buttonPanel2.add(bekijkOrders);
        buttonPanel2.add(bekijkPakbonnen);


        add(buttonPanel1);
        add(buttonPanel2);

        bekijkOrders.addActionListener(e -> {
            new BekijkOrders(parentFrame, true);
        });

        bekijkPakbonnen.addActionListener(e -> {
            new BekijkPakbonnen(parentFrame, true);
        });



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == voorraadAanpassen) {
            if(Integer.parseInt(plek.getText()) > 25 || Integer.parseInt(plek.getText()) < 1){
                JOptionPane.showMessageDialog(parentFrame, "GEEN GELDIGE PLEK (1-25)", "INANE ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                boolean res = db.setItems(Integer.parseInt(item.getText()), Integer.parseInt(plek.getText()));
                VoorraadPanel.drawVoorraad();

                if (res) {
                    JOptionPane.showMessageDialog(parentFrame, "Voorraad toegevoegd!");
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "ERROR, Item staat al in de kast", "INANE ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println(e);
            }
        }
    }
}
