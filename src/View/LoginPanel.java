package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoginPanel extends JPanel {
    public static Color lightGray = new Color(43, 45, 48) ;
    public static Color darkGray = new Color(30, 31, 34) ;
    public static Color orange = new Color(206, 129, 76) ;
    public static Font fontBold = null ;
    public static Font fontRegular = null ;

    static {
        try {
            fontBold = Font.createFont( Font.TRUETYPE_FONT, new File("src/View/Fonts/AmaticSC-Bold.ttf")).deriveFont(20f) ;
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            fontRegular = Font.createFont( Font.TRUETYPE_FONT, new File("src/View/Fonts/AmaticSC-Regular.ttf")).deriveFont(20f) ;
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LoginPanel(JPanel mainPanel, CardLayout cardLayout){

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel centralPanel = new JPanel();
        centralPanel.setFont(fontRegular);
        centralPanel.setLayout(new GridBagLayout());
        centralPanel.setBorder(BorderFactory.createLineBorder(orange));
        centralPanel.setBackground(darkGray);

        JLabel userLabel = createJLabel("Username:",gbc,0,0);
        centralPanel.add(userLabel, gbc);

        JTextField userField = new JTextField(15);
        gbc.gridx = 1;
        centralPanel.add(userField, gbc);

        JLabel passLabel = createJLabel("Password:",gbc,0,1);
        centralPanel.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(15);
        gbc.gridx = 1;
        centralPanel.add(passField, gbc);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(fontBold);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        centralPanel.add(loginButton, gbc);

        JLabel registerLabel = createJLabel("Don't have an account?",gbc,0,3);
        centralPanel.add(registerLabel, gbc);

        JButton cmdRegister = new JButton("Register Here!");
        cmdRegister.setForeground(orange); // Set text color
        cmdRegister.setFont(fontRegular); // Set the custom font
        cmdRegister.setContentAreaFilled(false);
        cmdRegister.setBorderPainted(false);
        cmdRegister.setFocusPainted(false);
        cmdRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cmdRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Register");
            }
        });

        gbc.gridx = 1;
        gbc.insets=new Insets(10,60,10,5);
        centralPanel.add(cmdRegister, gbc);
        gbc.insets= new Insets(20,20,20,20);
        gbc.gridx=0;
        gbc.gridy=0;
        add(centralPanel,gbc);

    }


    JLabel createJLabel(String message,GridBagConstraints gbc,int gridx,int gridy){
        JLabel label = new JLabel(message);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        label.setFont(fontRegular);
        return label;
    }




}
