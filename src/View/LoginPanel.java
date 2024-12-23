package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class LoginPanel extends JPanel {
    public static Color lightGray = new Color(43, 45, 48) ;
    public static Color darkGray = new Color(30, 31, 34) ;
    public static Color orange = new Color(206, 129, 76) ;
    public static Font fontBold = null ;
    public static Font fontRegular = null ;
    public static Font fieldsFont = null ;

   public JTextField userField;
   public JPasswordField passField;
   public JButton loginButton;



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
    static {
        try {
            fieldsFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/View/Fonts/ShadowsIntoLight-Regular.ttf")).deriveFont(20f);
        } catch (FontFormatException | IOException e) {
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

         userField = new JTextField(15);
        gbc.gridx = 1;
        userField.setFont(fieldsFont);
        userField.setBorder(new LineBorder(MainFrame.extraLightGray,1));

        centralPanel.add(userField, gbc);

        JLabel passLabel = createJLabel("Password:",gbc,0,1);
        centralPanel.add(passLabel, gbc);

        JPanel passwordEntryPanel = new JPanel(new GridBagLayout());

         passField = new JPasswordField(14);
        passField.setFont(fieldsFont);
        passField.setBorder(new LineBorder(new Color(70,73,75)));
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets( 0, 0, 0 ,0);
        gbc1.weightx = 8;
        gbc1.weighty= 1;
        gbc1.gridwidth = 10;
        gbc1.gridheight = 1;
        gbc1.fill=GridBagConstraints.BOTH;
        passwordEntryPanel.add(passField,gbc1);

//        centralPanel.add(passField, gbc);

        JCheckBox showPasswordCheckbox = new JCheckBox();
        showPasswordCheckbox.setForeground(orange);
        showPasswordCheckbox.setFont(fontRegular);
        showPasswordCheckbox.setOpaque(false);
//        gbc.gridx = 2;
        gbc1.gridx = 10;
        gbc1.weightx = 0.2;
        gbc1.gridwidth = 1;
        gbc1.anchor = GridBagConstraints.EAST;

        showPasswordCheckbox.setSize(8,8);
        showPasswordCheckbox.addActionListener(e -> {
            if (showPasswordCheckbox.isSelected()) {
                passField.setEchoChar((char) 0);
            } else {
                passField.setEchoChar('•');
            }
        });
        passwordEntryPanel.add(showPasswordCheckbox,gbc1);
        passwordEntryPanel.setBackground(new Color(70,73,75));
        passwordEntryPanel.setBorder(new LineBorder(MainFrame.extraLightGray,1));
//        gbc.insets= new Insets (0 , 0 , 0 , 1);
        gbc.gridx = 1;
        centralPanel.add(passwordEntryPanel, gbc);
//        gbc.insets = new Insets(20, 20, 20, 20);

        // Login Button
        loginButton = new JButton("Login");
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
    public String getUsername(){
        return userField.getText();
    }
    public String getPassword(){
        return passField.getText();
    }




}
