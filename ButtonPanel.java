import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements ActionListener {
    JButton voegVoorraadToe;
    JButton switchItems;
    JFrame parentFrame;

    ButtonPanel(JFrame parentFrame){
        this.parentFrame = parentFrame;

        setBounds(0,400,400,100);
        setLayout(new GridLayout(1,4));

        voegVoorraadToe = new JButton("Voeg voorraad toe");
        voegVoorraadToe.addActionListener(this);
        voegVoorraadToe.setHorizontalAlignment(JButton.CENTER);
        voegVoorraadToe.setBorder(BorderFactory.createLineBorder(Color.black));

        switchItems = new JButton("Switch items");
        switchItems.addActionListener(this);
        switchItems.setHorizontalAlignment(JButton.CENTER);
        switchItems.setBorder(BorderFactory.createLineBorder(Color.black));

        add(voegVoorraadToe);
        add(switchItems);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == voegVoorraadToe){
            new Dialog(parentFrame, true);
        }
    }
}
