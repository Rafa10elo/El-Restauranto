import javax.swing.*;

public class MainFrame extends JFrame {
    MainFrame(){

        JLabel label = new JLabel("hello");
        this.add(label);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
