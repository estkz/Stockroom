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

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime current = LocalDateTime.now();
    GridBagConstraints gbc = new GridBagConstraints();


    BekijkPakbonnen(JFrame frame, boolean m){
        super(frame, m);
        this.parentFrame = frame;

        setSize(new Dimension(600,600));
        setTitle("Bekijk pakbonnen");

        //setLayout(new FlowLayout(FlowLayout.LEFT));
        //setLayout(new GridLayout(0, 1, 0, 20));
        //setLayout(new GridLayout(20, 1));
        //setLayout(new GridBagLayout());
        setResizable(false);



        JLabel packingSlipNumber = new JLabel("420");
        JLabel orderID = new JLabel("1");
        JLabel klantNaam = new JLabel("Naam: " + clientName);
        JLabel date = new JLabel("Datum: " + dtf.format(current));
        JLabel titlePackingSlip = new JLabel(packingSlipTitle);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(titlePackingSlip, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(titlePackingSlip);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel rightPanel = new JPanel(new GridLayout(20, 1));
        rightPanel.add(klantNaam);
        rightPanel.add(date);

        mainPanel.add(rightPanel, BorderLayout.WEST);
        add(mainPanel);







        setLocationRelativeTo(null);
        setVisible(true);
    }
}
