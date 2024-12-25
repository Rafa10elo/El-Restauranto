package View;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoginAndRegistrationFrame extends JFrame {
    public static Color lightGray = new Color(43, 45, 48) ;
    public static Color darkGray = new Color(30, 31, 34) ;
    public static Color orange = new Color(206, 129, 76) ;
    public static Font fontBold = null ;
    public static Font fontRegular = null ;

    static {
        try {
            fontBold = Font.createFont( Font.TRUETYPE_FONT, new File("src/View/Fonts/AmaticSC-Bold.ttf")).deriveFont(35f) ;
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LoginAndRegistrationFrame(){

        try {

            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.err.println("Failed to initialize FlatLaf");
        }

        setTitle("El Restauranto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,700);
        setLocationRelativeTo(null);
        setVisible(true);

        // Create CardLayout to switch between panels
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        LoginPanel login = new LoginPanel(mainPanel, cardLayout);
        RegisterPanel register = new RegisterPanel(mainPanel, cardLayout);

        mainPanel.add(login, "Login");
        mainPanel.add(register, "Register");

        cardLayout.show(mainPanel, "Login");

        add(mainPanel);

        revalidate();
        repaint();
    }
}
