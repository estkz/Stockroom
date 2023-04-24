import com.sun.jdi.VMOutOfMemoryException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dialog extends JDialog implements ActionListener {
    Voorraad voorraad = new Voorraad();

    JTextField index;
    JTextField item;
    JLabel indexLabel;
    JLabel itemLabel;
    JButton cancel;
    JButton ok;

    Dialog(JFrame frame, boolean modal){
        super(frame, modal);
        setSize(new Dimension(300,200));
        setLayout(new GridLayout(3,2));

        index = new JTextField();
        index.setPreferredSize(new Dimension(50,20));
        index.setHorizontalAlignment(JTextField.CENTER);

        item = new JTextField();
        item.setPreferredSize(new Dimension(50,20));
        item.setHorizontalAlignment(JTextField.CENTER);

        indexLabel = new JLabel("index");
        indexLabel.setHorizontalAlignment(JLabel.CENTER);

        itemLabel = new JLabel("item");
        itemLabel.setHorizontalAlignment(JLabel.CENTER);

        cancel = new JButton("cancel");
        cancel.setHorizontalAlignment(JButton.CENTER);
        cancel.addActionListener(this);

        ok = new JButton("Ok");
        ok.setHorizontalAlignment(JButton.CENTER);
        ok.addActionListener(this);

        add(indexLabel);
        add(index);
        add(itemLabel);
        add(item);
        add(cancel);
        add(ok);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancel){
            dispose();
        }

        if(e.getSource() == ok){
            boolean res = voorraad.setVoorraad(Integer.parseInt(index.getText()), Integer.parseInt(item.getText()));

            if(res){
                System.out.println("update");
            }
        }
    }
}
