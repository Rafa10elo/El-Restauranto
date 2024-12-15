import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainFrame extends JFrame {
    MainFrame(){

//        JLabel label = new JLabel("hello");
        setSize(new Dimension(500,500));
        this.setLayout(new BorderLayout());
        JButton button = new RoundedButton("hamoudeh");
        JSpinner spinner = new RoundedSpinner(new SpinnerListModel());
        this.add(button,BorderLayout.SOUTH);
        this.add(spinner, BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
