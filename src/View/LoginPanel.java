package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoginPanel extends JPanel {

    public LoginPanel(JPanel mainPanel, CardLayout cardLayout){

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel centralPanel = new JPanel();
        centralPanel.setFont(MainFrame.fontRegular);
        centralPanel.setLayout(new GridBagLayout());
        centralPanel.setBorder(BorderFactory.createLineBorder(MainFrame.orange));
        centralPanel.setBackground(MainFrame.darkGray);

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
        loginButton.setFont(MainFrame.fontBold.deriveFont(20F));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        centralPanel.add(loginButton, gbc);

        JLabel registerLabel = createJLabel("Don't have an account?",gbc,0,3);
        centralPanel.add(registerLabel, gbc);

        JButton cmdRegister = new JButton("Register Here!");
        cmdRegister.setForeground(MainFrame.orange); // Set text color
        cmdRegister.setFont(MainFrame.fontRegular.deriveFont(20F)); // Set the custom font
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
        label.setFont(MainFrame.fontRegular.deriveFont(20F));
        return label;
    }




}
