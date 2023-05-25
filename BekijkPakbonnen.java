import javax.swing.*;
import java.awt.*;
import java.time.Clock;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class BekijkPakbonnen extends JDialog {
    JFrame parentFrame;

    // Vars
    String packingSlipTitle = "Pakbon";
    String clientName = "Tom Prachtig";

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime current = LocalDateTime.now();


    BekijkPakbonnen(JFrame frame, boolean m){
        super(frame, m);
        this.parentFrame = frame;

        setSize(new Dimension(600,600));
        setTitle("Bekijk pakbonnen");
        setLayout(new FlowLayout());
        setResizable(false);

        JLabel packingSlipNumber = new JLabel("420");
        JLabel orderID = new JLabel("1");
        JLabel klantNaam = new JLabel("Naam: " + clientName);
        JLabel date = new JLabel("Datum: " + dtf.format(current));
        JLabel titlePackingSlip = new JLabel(packingSlipTitle);


        // Add Components
        add(titlePackingSlip);
        add(klantNaam);
        add(date);





        setLocationRelativeTo(null);
        setVisible(true);
    }
}
