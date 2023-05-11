import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class OrderAanmaken extends JDialog{
    Database db = new Database();
    JFrame parentFrame;
    JButton addItem = new JButton("Item toevoegen");
    JButton removeItem = new JButton("Item verwijderen");


    OrderAanmaken(JFrame f, boolean m){
        super(f, m);
        this.parentFrame = f;

        setSize(new Dimension(600,600));
        setLayout(new FlowLayout());
        setResizable(false);

        DefaultListModel<String> demoList = new DefaultListModel<>();
        JList<String> list = new JList<>(demoList);
        list.setPreferredSize(new Dimension(500,400));


        JComboBox<Integer> comboBox = new JComboBox<>();
        for(int i = 0; i< db.getItems().length; i++){
            if(db.getItems()[i] != 0) {
                comboBox.addItem(db.getItems()[i]);
            }
        }
        comboBox.setPreferredSize(new Dimension(150,21));


        addItem.addActionListener(e -> {
            demoList.addElement(Objects.requireNonNull(comboBox.getSelectedItem()).toString());
            comboBox.removeItem(comboBox.getSelectedItem());
        });
        addItem.setPreferredSize(new Dimension(150,20));


        removeItem.addActionListener(e -> {
            comboBox.addItem(Integer.parseInt(list.getSelectedValue()));
            demoList.remove(list.getSelectedIndex());
        });
        addItem.setPreferredSize(new Dimension(150, 20));


        add(comboBox);
        add(addItem);
        add(list);
        add(removeItem);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}