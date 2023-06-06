import javax.swing.*;
import java.awt.*;

public class BekijkWachtrij extends JDialog {

    JFrame parentFrame;
    BekijkWachtrij(JFrame frame, boolean m) {
        super(frame, m);
        this.parentFrame = frame;

        // Make the dialog screen
        setSize(new Dimension(600,600));
        setTitle("Bekijk Wachtrij");
        setLayout(new FlowLayout(FlowLayout.CENTER,125, 55));
        setResizable(false);

        // Components
        JPanel panelID = new JPanel();
        JButton viewItems = new JButton("Bekijk Items");
        JButton pageBack = new JButton("<--");
        JTable wachtrijTable;
        JTable itemsTable;

        // Table Rows/Columns
        String[][] data = {
                { "1" , "1", "3"},
                { "2" , "7", "8"},
                { "3" , "9", "5"},
                { "4" , "4", "2"},
                { "5" , "82", "1"},
                { "6" , "44", "6"},
                { "7" , "91", "1"},
                { "8" , "62", "1"},
                { "9" , "13", "3"},
                { "10" , "31", "2"}
        };

        String[] columnNames = { "Plek", "Order ID", "Aantal Items" };

        String[][] itemData = {
                { "1", "Furry Socks" },
                { "2", "Worstuhbroodje" }
        };

        String[] itemColumnNames = { "Item ID", "Item Beschrijving" };

        // Assign Rows/Columns To Table & Styling
        wachtrijTable = new JTable(data, columnNames);
        wachtrijTable.setBounds(30, 40, 200, 300);

        itemsTable = new JTable(itemData, itemColumnNames);
        itemsTable.setBounds(30,40,200,300);

        JScrollPane wachtrijScrollpane = new JScrollPane(wachtrijTable);
        JScrollPane itemsScrollpane = new JScrollPane(itemsTable);

        // Add Components
        add(wachtrijScrollpane);
        add(itemsScrollpane);
        itemsScrollpane.setVisible(false);
        add(viewItems);

        viewItems.addActionListener(e -> {
            wachtrijScrollpane.setVisible(false);
            itemsScrollpane.setVisible(true);
            add(pageBack);
            pageBack.setVisible(true);
            getContentPane().validate();
            getContentPane().repaint();
        });

        pageBack.addActionListener(e -> {
            itemsScrollpane.setVisible(false);
            wachtrijScrollpane.setVisible(true);
            getContentPane().validate();
            getContentPane().repaint();
            pageBack.setVisible(false);
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
