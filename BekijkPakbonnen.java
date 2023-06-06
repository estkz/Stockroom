import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BekijkPakbonnen extends JDialog {
    private Database db = new Database();
    private JFrame parentFrame;

    // Components
    private JList<String> productList = new JList<>();
    private DefaultListModel<String> productListModel = new DefaultListModel<>();
    private JComboBox<Integer> selectOrder = new JComboBox<>();

    // Company Information
    private JLabel company = new JLabel("Nerdygadgets");
    private JLabel companyStreet = new JLabel("Campus");
    private JLabel zipCode = new JLabel("8017 CA Zwolle");
    private JLabel companyCountry = new JLabel("Nederland");

    // Customer Information
    private JLabel lOrderID = new JLabel("Order ID:");
    private JLabel lKlantNaam = new JLabel("Naam:");
    private JLabel lDeliveryAddress = new JLabel(" ");
    private JLabel lDate = new JLabel("Datum:");
    private JLabel titlePackingSlip;

    // Binpacking Information
    private JLabel lAmountOfItems = new JLabel("Aantal bins nodig:");
    private JLabel lblSpacing = new JLabel(" ");

    // Date and time
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private LocalDateTime current = LocalDateTime.now();

    // Paint Bekijk Pakbonnen Dialog
    public BekijkPakbonnen(JFrame frame, boolean m) {
        super(frame, m);
        parentFrame = frame;

        // Dialog Properties
        setSize(600, 600);
        setTitle("Bekijk pakbonnen");
        setResizable(false);

        titlePackingSlip = new JLabel("Pakbon (orderID):");

        // Top Panel | Pakbon Title Alignment
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(titlePackingSlip);
        topPanel.add(selectOrder);

        ArrayList<Integer> comboOrderID = db.getAllOrderIDs();
        for (int orderID : comboOrderID) {
            selectOrder.addItem(orderID); // Add order ID to the combobox
        }

        // Add action listener to the combobox
        selectOrder.addActionListener(e -> {
            int selectedOrderID = (int) selectOrder.getSelectedItem();
            GetOrderContent(selectedOrderID); // Update the order information based on the selected order ID
        });

        selectOrder.setSelectedIndex(0);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        add(mainPanel);

        // Left Panel | Client/Order Information
        JPanel leftPanel = new JPanel(new GridLayout(25, 1));

        // Left Panel | Company Information
        leftPanel.add(company);
        leftPanel.add(companyStreet);
        leftPanel.add(zipCode);
        leftPanel.add(companyCountry);
        leftPanel.add(lblSpacing);

        // Left Panel | Client Information
        leftPanel.add(lOrderID);
        leftPanel.add(lKlantNaam);
        leftPanel.add(lDeliveryAddress);
        leftPanel.add(lDate);
        leftPanel.add(lAmountOfItems);

        // Right Panel
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(200, getHeight()));

        // Right Panel | Add Items in bin
        JLabel binPackingInfo = new JLabel("Items in bin:");
        rightPanel.add(binPackingInfo, BorderLayout.NORTH);

        // Add more components to the right panel as needed
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton printPakbon = new JButton("Print");
        bottomPanel.add(printPakbon);

        // Print pakbon alert
        printPakbon.addActionListener(e -> {
            JOptionPane.showMessageDialog(BekijkPakbonnen.this, "Pakbon wordt geprint!");
        });

        mainPanel.add(productList, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Functions
    private void GetOrderContent(int orderID) {
        System.out.println("Order ID: " + orderID);
        Order order = new Order(orderID);

        // Clear the list when picking new order from combobox
        productListModel.clear();

        ArrayList<ArrayList<Integer>> orderLines = db.getOrderLines(orderID);

        for (ArrayList<Integer> orderLine : orderLines) {
            for (int orderLineID : orderLine) {
                int itemID = db.getItemIDFromOrderline(orderLineID);
                if (itemID != -1) {
                    String itemsInOrder = db.getItems()[itemID];
                    System.out.println("id: " + itemID);
                    productListModel.addElement(itemsInOrder);
                }
            }
        }

        // Set the DefaultListModel as the model for the JList
        productList.setModel(productListModel);

        // Update the corresponding labels with the retrieved order information
        lOrderID.setText("Order ID: " + orderID);
        lKlantNaam.setText("Naam: " + order.getCustomerName());
        lDeliveryAddress.setText("Adres: " + order.getDeliveryAddress());
        lDate.setText("Datum: " + dtf.format(current));
        lAmountOfItems.setText("Aantal producten: " + db.getAantalItemsFromOrderID(orderID));
    }
}
