import Serial.SimpleSerial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class HomeUI extends JPanel implements ActionListener {
    Database db = new Database();
    JFrame parentFrame;

    JPanel buttonPanel1 = new JPanel();
    JPanel buttonPanel2 = new JPanel();

    JButton bekijkOrders = new JButton("Bekijk orders");
    JButton bekijkPakbonnen = new JButton("Bekijk pakbonnen");
    JButton veranderPlek = new JButton("Verander plek");

    JComboBox<String> item = new JComboBox<>();
    JComboBox<Integer> plek = new JComboBox<>();

     // Java Stukje Nick
    JButton bekijkWachtrij = new JButton("Bekijk wachtrij");
    ImageIcon hmiLogo = new ImageIcon("nerdie.png");

    JButton voorraadAanpassen = new JButton("Voorraad toevoegen");
    JButton voorraadVerwijderen = new JButton("Voorraad verwijderen");

    SimpleSerial Serial;


    HomeUI(JFrame parentFrame, SimpleSerial Serial){
        this.parentFrame = parentFrame;
        this.Serial = Serial;

        setBounds(420,0,200,400);
        setBackground(Color.lightGray);
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));
        parentFrame.setIconImage(hmiLogo.getImage());

        for(int i=0; i<db.getAantalItems(); i++){
            item.addItem((i+1) + " " + db.getItems()[i]);
        }
        for(int i=1; i<=25; i++){
            plek.addItem(i);
        }

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
        buttonPanel2.add(bekijkWachtrij);
        buttonPanel2.add(veranderPlek);

        add(buttonPanel1);
        add(buttonPanel2);

        bekijkOrders.addActionListener(e -> {
            new BekijkOrders(parentFrame, true, Serial);
        });

        bekijkPakbonnen.addActionListener(e -> {
            new BekijkPakbonnen(parentFrame, true);
        });

        bekijkWachtrij.addActionListener(e -> {
            new BekijkWachtrij(parentFrame, true);
        });


        veranderPlek.addActionListener(e -> {
            new VeranderPlek(parentFrame, true, Serial);
        });


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == voorraadAanpassen) {
            int plekVar = 0;
            int itemVar = 0;
            boolean res = false;

            try {
                plekVar = Integer.parseInt(Objects.requireNonNull(plek.getSelectedItem()).toString());
                itemVar = Character.getNumericValue(Objects.requireNonNull(item.getSelectedItem()).toString().charAt(0));
            } catch (NumberFormatException numE){
                System.out.println(numE);
            }

            if (plekVar != 0 && itemVar != 0) {
                res = db.setItems(plekVar, itemVar);
            }

            boolean placed = false;
            if(res){
                Serial.grid(26);
                int options = JOptionPane.showConfirmDialog(this,"is packet placed?");
                if(options == 0){

                    if(!Serial.grid(26)){return;}
                    Serial.delay(100);
                    if(!Serial.grid(plekVar)){return;}
                    Serial.delay(300);
                    if(!Serial.packet("place",3)){return;}
                    Serial.delay(250);
                    if(!Serial.packet("dump")){return;}
                    Serial.delay(100);
                    if(!Serial.grid(26)){return;}
                    Serial.delay(100);
                    placed = true;
                }
            }



            VoorraadPanel.drawVoorraad();

            if (res && placed) {
                JOptionPane.showMessageDialog(parentFrame, "Voorraad toegevoegd!");
            } else {
                JOptionPane.showMessageDialog(parentFrame, "ERROR", "INANE ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }




        if (e.getSource() == voorraadVerwijderen) {
            int plekVar = 0;
            int itemVar = 0;
            boolean res = false;

            try {
                plekVar = Integer.parseInt(Objects.requireNonNull(plek.getSelectedItem()).toString());
                itemVar = Character.getNumericValue(Objects.requireNonNull(item.getSelectedItem()).toString().charAt(0));
            } catch (NumberFormatException numE){
                System.out.println(numE);
            }

            try {
                int input = JOptionPane.showConfirmDialog(parentFrame, "Weet u zeker dat u het item op plek " + plek.getSelectedItem() + " wilt verwijderen ?", "Confirm", JOptionPane.YES_NO_OPTION);
                System.out.println(input);

                if (input == 0) {
                    res = db.removeItems(itemVar, plekVar);
                    VoorraadPanel.drawVoorraad();


                    boolean picked = false;
                    if(res){
                        if(!Serial.packet("dump")){return;}
                        Serial.delay(100);
                        if(!Serial.grid(plekVar)){return;}
                        Serial.delay(100);
                        if(!Serial.packet("pick",3)){return;}
                        Serial.delay(100);
                        if(!Serial.packet("dump")){return;}
                        Serial.delay(100);
                        if(!Serial.grid(26)){return;}
                        Serial.delay(100);
                        picked = true;
                    }


                    if (res && picked) {
                        JOptionPane.showMessageDialog(parentFrame, "Voorraad verwijderd!");
                    } else {
                        JOptionPane.showMessageDialog(parentFrame, "ERROR, voorraad niet verwijderd", "INANE ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                System.out.println(e);
            }
        }
    }
}
