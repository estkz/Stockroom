import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class BekijkPakbonnen extends JDialog {
    Database db = new Database();
    JFrame parentFrame;
    JLabel lblSpacing = new JLabel(" ");

    // Functions
    void GetOrderContent(int orderID) {
        // Get all the information for the selected order (Order ID, ClientName, Amount of products, Products in order, Date)
        System.out.println("Order ID: " + orderID);
        Order order = new Order(orderID);

        // Update the corresponding labels with the retrieved order information
        lOrderID.setText("Order ID: " + orderID);
        lKlantNaam.setText("Naam: " + order.getCustomerName());
        lDate.setText("Datum: " + dtf.format(current));
    }

    // Vars
    String packingSlipTitle = "Pakbon ";
    int orderAmount = db.getAantalOrders();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime current = LocalDateTime.now();
    GridBagConstraints gbc = new GridBagConstraints();

    // Components
    JComboBox<Integer> selectOrder;

    // Labels
    JLabel company = new JLabel("Nerdygadgets");
    JLabel companyStreet = new JLabel("Campus");
    JLabel zipCode = new JLabel("8017 CA Zwolle");
    JLabel companyCountry = new JLabel("Nederland");

    JLabel lOrderID;
    JLabel lKlantNaam;
    JLabel lDate;
    JLabel titlePackingSlip;

    // Paint Bekijk Pakbonnen Dialog
    BekijkPakbonnen(JFrame frame, boolean m){
        super(frame, m);
        this.parentFrame = frame;

        // Dialog Properties
        setSize(new Dimension(600,600));
        setTitle("Bekijk pakbonnen");
        setResizable(false);

        /* Components */
        selectOrder = new JComboBox<>();

        lOrderID = new JLabel("Order ID: ");
        lKlantNaam = new JLabel("Naam: ");
        lDate = new JLabel("Datum: ");
        titlePackingSlip = new JLabel(packingSlipTitle);

        // Top Panel | Pakbon Title Alignment
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(titlePackingSlip, BorderLayout.CENTER);
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(titlePackingSlip);
        topPanel.add(selectOrder);

        for(int i = 1; i <= orderAmount; i++){
            selectOrder.addItem(i); // Add order IDs to the combobox
        }

        // Add action listener to the combobox
        selectOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedOrderID = (int) selectOrder.getSelectedItem();
                GetOrderContent(selectedOrderID); // Update the order information based on the selected order ID
            }
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
        leftPanel.add(lDate);
        mainPanel.add(leftPanel, BorderLayout.WEST);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
