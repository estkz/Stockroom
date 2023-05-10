import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VoorraadPanel extends JPanel implements ActionListener {
    Database db = new Database();

    JFrame parentFrame;
    JTextField item = new JTextField();
    JTextField plek = new JTextField();

    JButton voorraadAanpassen = new JButton("Voorraad aanpassen");

    JLabel[] labels = new JLabel[25];

    VoorraadPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setBounds(0, 0, 400, 400);
        setBackground(Color.lightGray);
        setLayout(new GridLayout(6, 5));

        for (int i = 0; i < 25; i++) {
            labels[i] = new JLabel();
            labels[i].setBorder(BorderFactory.createLineBorder(Color.black));
            labels[i].setHorizontalAlignment(SwingConstants.CENTER);
            add(labels[i]);
        }


        voorraadAanpassen.addActionListener(this);

        drawVoorraad();
        add(new JLabel("Item nr."));
        add(item);
        add(new JLabel("Plek nr."));
        add(plek);
        add(voorraadAanpassen);
    }


    public void drawVoorraad() {
        int[] voorraadArray = Voorraad.getVoorraad();

        for(int i=0; i<25; i++) {
            int voorraadItem = voorraadArray[i];

            if (voorraadItem != 0) {
                labels[i].setText("Item: "+voorraadItem);
            } else {
                labels[i].setText("");
            }
        }
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
                drawVoorraad();

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




