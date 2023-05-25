import javax.swing.*;
import java.awt.*;

public class BekijkPakbonnen extends JDialog {
    Database db = new Database();
    JButton haalPakbon = new JButton("Ophalen pakbon van order");
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> pakbonList = new JList<>(listModel);
    JFrame parentFrame;

    BekijkPakbonnen(JFrame frame, boolean m){
        super(frame, m);
        this.parentFrame = frame;

        setSize(new Dimension(600,600));
        setTitle("Bekijk pakbonnen");
        setLayout(new FlowLayout());
        setResizable(false);



        add(new JLabel("Pakbon halen: "));
        add(pakbonList);
        add(haalPakbon);

        pakbonList.setPreferredSize(new Dimension(550,400));
        pakbonList.setLayout(new GridLayout(5,2));
        pakbonList.setBorder(BorderFactory.createLineBorder(Color.black));

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
