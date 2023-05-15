import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Arm extends JPanel implements ActionListener {
    Database db = new Database();
    JFrame parentFrame;

    JPanel buttonPanel1 = new JPanel();
    JPanel buttonPanel2 = new JPanel();

    JButton bekijkOrders = new JButton("Bekijk orders");
    JButton bekijkPakbonnen = new JButton("Bekijk pakbonnen");

    JTextField item = new JTextField();
    JTextField plek = new JTextField();
    JButton voorraadAanpassen = new JButton("Voorraad toevoegen");
    JButton voorraadVerwijderen = new JButton("Voorraad verwijderen");


    Arm(JFrame parentFrame){
        this.parentFrame = parentFrame;

        setBounds(420,0,200,400);
        setBackground(Color.lightGray);
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));


        buttonPanel1.setLayout(new GridLayout(3,2));

        voorraadVerwijderen.addActionListener(this);
        voorraadAanpassen.addActionListener(this);

        buttonPanel1.setLayout(new GridLayout(7,1));
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
        buttonPanel1.add(voorraadVerwijderen);

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
            try {
                if (Integer.parseInt(plek.getText()) > 25 || Integer.parseInt(plek.getText()) < 1) {
                    JOptionPane.showMessageDialog(parentFrame, "GEEN GELDIGE PLEK (1-25)", "INANE ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    boolean res = db.setItems(Integer.parseInt(item.getText()), Integer.parseInt(plek.getText()));
                    VoorraadPanel.drawVoorraad();

                    if (res) {
                        JOptionPane.showMessageDialog(parentFrame, "Voorraad toegevoegd!");
                    } else {
                        JOptionPane.showMessageDialog(parentFrame, "ERROR, Item staat al in de kast en/of staat al een item op deze plek", "INANE ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    System.out.println(e);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(parentFrame, "ERROR, vul een int in s.v.p.", "INANE ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == voorraadVerwijderen) {
            try {
                if (db.getItems()[Integer.parseInt(plek.getText()) - 1] == Integer.parseInt(item.getText())) {

                    if (Integer.parseInt(plek.getText()) > 25 || Integer.parseInt(plek.getText()) < 1) {
                        JOptionPane.showMessageDialog(parentFrame, "GEEN GELDIGE PLEK (1-25)", "INANE ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        int input = JOptionPane.showConfirmDialog(parentFrame, "Weet u zeker dat u item "+item.getText()+" wilt verwijderen van plek "+plek.getText()+"?", "Confirm", JOptionPane.YES_NO_OPTION);
                        boolean res = false;


                        System.out.println(input);
                        if(input == 0){
                            res = db.removeItems(Integer.parseInt(item.getText()), Integer.parseInt(plek.getText()));
                            VoorraadPanel.drawVoorraad();

                            if (res) {
                                JOptionPane.showMessageDialog(parentFrame, "Voorraad verwijdert!");
                            } else {
                                JOptionPane.showMessageDialog(parentFrame, "ERROR, Item staat al in de kast en/of staat al een item op deze plek", "INANE ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }


                    } catch (Exception ex) {
                        System.out.println(e);
                    }
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "ERROR, op plek " + plek.getText() + " staat item " + item.getText() + " niet.", "INANE ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }catch(Exception exception){
                    JOptionPane.showMessageDialog(parentFrame, "ERROR, vul een int in s.v.p.", "INANE ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
