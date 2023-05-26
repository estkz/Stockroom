import javax.swing.*;
import java.awt.*;
import java.time.Clock;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class BekijkPakbonnen extends JDialog {
    JFrame parentFrame;

    // Vars
    String packingSlipTitle = "Pakbon ";
    String clientName = "Tom Prachtig ";

    int orderID = 1;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime current = LocalDateTime.now();
    GridBagConstraints gbc = new GridBagConstraints();


    // Paint Bekijk Pakbonnen Dialog
    BekijkPakbonnen(JFrame frame, boolean m){
        super(frame, m);
        this.parentFrame = frame;

        // Dialog Properties
        setSize(new Dimension(600,600));
        setTitle("Bekijk pakbonnen");
        setResizable(false);

        // Components
        JLabel lPpackingSlipNumber = new JLabel("420");
        JLabel lOrderID = new JLabel("Order ID: " + orderID);
        JLabel lKlantNaam = new JLabel("Naam: " + clientName);
        JLabel lDate = new JLabel("Datum: " + dtf.format(current));
        JLabel titlePackingSlip = new JLabel(packingSlipTitle);

        // Top Panel | Pakbon Title Alignment
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(titlePackingSlip, BorderLayout.CENTER);
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(titlePackingSlip);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        add(mainPanel);

        // Left Panel | Client/Order Information
        JPanel leftPanel = new JPanel(new GridLayout(20, 1));
        leftPanel.add(lOrderID);
        leftPanel.add(lKlantNaam);
        leftPanel.add(lDate);
        mainPanel.add(leftPanel, BorderLayout.WEST);








        setLocationRelativeTo(null);
        setVisible(true);
    }
}
