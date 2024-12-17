package View;
import com.formdev.flatlaf.FlatDarkLaf;


import BHswing.RoundedSpinner;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
  public   MainFrame(){
try{
UIManager.setLookAndFeel(new FlatDarkLaf());


} catch (Exception e) {
    throw new RuntimeException(e);
}
      JLabel label = new JLabel("hello");
JPanel panel = new JPanel();
panel.add(label);
        setSize(new Dimension(500,500));
        this.setLayout(new BorderLayout());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);

        this.setVisible(true);



    }
}
