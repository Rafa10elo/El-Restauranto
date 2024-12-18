package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class RegisterPanel extends JPanel {
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

    public RegisterPanel(JPanel mainPanel, CardLayout cardLayout){
        setLayout(new GridBagLayout());
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new GridBagLayout());
        centralPanel.setBorder(BorderFactory.createLineBorder(orange, 3));
        centralPanel.setBackground(darkGray);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel userLabel =  createJLabel("Username:",gbc,0,0);
        gbc.gridwidth = 1;
        centralPanel.add(userLabel, gbc);

        JTextField userField = new JTextField(15);
        gbc.gridx = 1;
        centralPanel.add(userField, gbc);

        JLabel emailLabel = createJLabel("Email:",gbc,0,1);
        centralPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(15);
        gbc.gridx = 1;
        centralPanel.add(emailField, gbc);

        JLabel passLabel = createJLabel("Password:",gbc,0,2);
        centralPanel.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(15);
        gbc.gridx = 1;
        centralPanel.add(passField, gbc);

        JLabel confirmPassLabel = createJLabel("Confirm Password:",gbc,0,3);
        centralPanel.add(confirmPassLabel, gbc);

        JPasswordField confirmPassField = new JPasswordField(15);
        gbc.gridx = 1;
        centralPanel.add(confirmPassField, gbc);

        JLabel chooseUserLabel = createJLabel("User:",gbc,0,4);
        centralPanel.add(chooseUserLabel, gbc);

        JRadioButton chooseCustomerButton = new JRadioButton("Customer");
        chooseCustomerButton.setFont(fontBold);
        JRadioButton chooseEmployeeButton = new JRadioButton("Employee");
        chooseEmployeeButton.setFont(fontBold);
        JRadioButton chooseManagerButton = new JRadioButton("Manager");
        chooseManagerButton.setFont(fontBold);

        ButtonGroup userChoice = new ButtonGroup();
        userChoice.add(chooseCustomerButton);
        userChoice.add(chooseEmployeeButton);
        userChoice.add(chooseManagerButton);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        radioPanel.setBackground(new Color(43,43,43));
        radioPanel.add(chooseCustomerButton);
        radioPanel.add(chooseEmployeeButton);
        radioPanel.add(chooseManagerButton);
        radioPanel.setBackground(darkGray);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        centralPanel.add(radioPanel, gbc);

        JLabel optionalFieldLabel =createJLabel("Employee/Manager Code:",gbc,0,6);
        centralPanel.add(optionalFieldLabel, gbc);

        JTextField optionalField = new JTextField(5);
        optionalField.setEnabled(false);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 30, 5, 10);
        centralPanel.add(optionalField, gbc);

        gbc.insets = new Insets(20, 20, 20, 20);

        JButton registerButton = new JButton("Register");
        registerButton.setFont(fontBold);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        centralPanel.add(registerButton, gbc);

        JButton backButton = new JButton("Back to Login");
        backButton.setFont(fontBold);
        gbc.gridy = 8;
        centralPanel.add(backButton, gbc);

        gbc.gridx=0;
        gbc.gridy=0;
        add(centralPanel,gbc);

        // Enable optional text field when "Employee" or "Manager" is selected
        ActionListener radioActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chooseEmployeeButton.isSelected() || chooseManagerButton.isSelected()) {
                    optionalField.setEnabled(true);
                } else {
                    optionalField.setEnabled(false);
                }
            }
        };

        chooseCustomerButton.addActionListener(radioActionListener);
        chooseEmployeeButton.addActionListener(radioActionListener);
        chooseManagerButton.addActionListener(radioActionListener);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Login");
            }
        });
    }

    JLabel createJLabel(String message,GridBagConstraints gbc,int gridx,int gridy){
        JLabel label = new JLabel(message);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        label.setFont(fontRegular);
        return label;
    }


}
