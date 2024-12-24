package View;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoginAndRegistrationFrame extends JFrame {
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

        // Create CardLayout to switch between panels
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        LoginPanel login = new LoginPanel(mainPanel, cardLayout);
        RegisterPanel register = new RegisterPanel(mainPanel, cardLayout);

        mainPanel.add(login, "Login");
        mainPanel.add(register, "Register");

        cardLayout.show(mainPanel, "Login");

        add(mainPanel);
    }
}
