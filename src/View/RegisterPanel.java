package View;

import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class RegisterPanel extends JPanel {
    public static Color lightGray = new Color(43, 45, 48);
    public static Color darkGray = new Color(30, 31, 34);
    public static Color orange = new Color(206, 129, 76);
    public static Font fontBold = null;
    public static Font fontRegular = null;
    public static Font fieldsFont = null;

    static {
        try {
            fontBold = Font.createFont(Font.TRUETYPE_FONT, new File("src/View/Fonts/AmaticSC-Bold.ttf")).deriveFont(20f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            fontRegular = Font.createFont(Font.TRUETYPE_FONT, new File("src/View/Fonts/AmaticSC-Regular.ttf")).deriveFont(20f);
        } catch (FontFormatException | IOException e) {
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

    public RegisterPanel(JPanel mainPanel, CardLayout cardLayout) {
        setLayout(new GridBagLayout());
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new GridBagLayout());
        centralPanel.setBorder(BorderFactory.createLineBorder(orange, 3));
        centralPanel.setBackground(darkGray);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel userLabel = createJLabel("Username:", gbc, 0, 0);
        gbc.gridwidth = 1;
        centralPanel.add(userLabel, gbc);

        JTextField userField = new JTextField(15);
        gbc.gridx = 1;
        userField.setFont(fieldsFont);
        userField.setBorder(new LineBorder(MainFrame.extraLightGray,1));
        centralPanel.add(userField, gbc);

        JLabel emailLabel = createJLabel("Email:", gbc, 0, 1);

        centralPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(15);
        emailField.setFont(fieldsFont);

        gbc.gridx = 1;
        emailField.setBorder(new LineBorder(MainFrame.extraLightGray,1));

        emailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                boolean test = isValidEmail(emailField.getText());
                if(!test)
                    emailField.setBorder(new MatteBorder(0,0,2,0,Color.red));
                else
                    emailField.setBorder(new MatteBorder(0,0,2,0,new Color(70,73,75)));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                boolean test = isValidEmail(emailField.getText());
                if(!test)
                    emailField.setBorder(new MatteBorder(0,0,2,0,Color.red));
                else
                    emailField.setBorder(new MatteBorder(0,0,2,0,new Color(70,73,75)));
            }
        });
        centralPanel.add(emailField, gbc);

        JLabel passLabel = createJLabel("Password:", gbc, 0, 2);
        centralPanel.add(passLabel, gbc);


        JPanel passwordEntryPanel = new JPanel(new GridBagLayout());

        JPasswordField passField = new JPasswordField(14);
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
        passField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                int test = passwordCheck(passField.getText());
                if(passField.getText().equals(""))
                    passwordEntryPanel.setBorder(new MatteBorder(0,0,2,0,new Color(70,73,75)));
                else if(test==0)
                    passwordEntryPanel.setBorder(new MatteBorder(0,0,2,0,Color.red));
                else if (test == 1)
                    passwordEntryPanel.setBorder(new MatteBorder(0,0,2,0,Color.ORANGE));
                else if (test == 2)
                    passwordEntryPanel.setBorder(new MatteBorder(0,0,2,0,Color.YELLOW));
                else if (test == 3)
                    passwordEntryPanel.setBorder(new MatteBorder(0,0,2,0,Color.GREEN));
                else if (test == 4)
                    passwordEntryPanel.setBorder(new MatteBorder(0,0,2,0,Color.cyan));


            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(passField.getText().equals(""))
                    passField.setBorder(new MatteBorder(0,0,2,0,new Color(70,73,75)));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int test = passwordCheck(passField.getText());
                if(passField.getText().equals(""))
                    passwordEntryPanel.setBorder(new MatteBorder(0,0,2,0,new Color(70,73,75)));
                else if(test==0)
                    passwordEntryPanel.setBorder(new MatteBorder(0,0,2,0,Color.red));
                else if (test == 1)
                    passwordEntryPanel.setBorder(new MatteBorder(0,0,2,0,Color.ORANGE));
                else if (test == 2)
                    passwordEntryPanel.setBorder(new MatteBorder(0,0,2,0,Color.YELLOW));
                else if (test == 3)
                    passwordEntryPanel.setBorder(new MatteBorder(0,0,2,0,Color.GREEN));
                else if (test == 4)
                    passwordEntryPanel.setBorder(new MatteBorder(0,0,2,0,Color.cyan));

            }
        });
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

        passwordEntryPanel.add(showPasswordCheckbox,gbc1);
        passwordEntryPanel.setBackground(new Color(70,73,75));
        passwordEntryPanel.setBorder(new LineBorder(MainFrame.extraLightGray,1));
//        gbc.insets= new Insets (0 , 0 , 0 , 1);
        gbc.gridx = 1;
       centralPanel.add(passwordEntryPanel, gbc);
//        gbc.insets = new Insets(20, 20, 20, 20);

        showPasswordCheckbox.addActionListener(e -> {
            if (showPasswordCheckbox.isSelected()) {
                passField.setEchoChar((char) 0);
            } else {
                passField.setEchoChar('•');
            }
        });
        JLabel confirmPassLabel = createJLabel("Confirm Password:", gbc, 0, 3);
        centralPanel.add(confirmPassLabel, gbc);

        JPanel passwordEntryPanel1 = new JPanel(new GridBagLayout());
        JPasswordField passCheckField = new JPasswordField(14);
        passCheckField.setFont(fieldsFont);

        passCheckField.setBorder(new LineBorder(new Color(70,73,75)));
        passCheckField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                boolean test = checkingThePass(passField.getText(),passCheckField.getText());
                if(!test)
                    passwordEntryPanel1.setBorder(new MatteBorder(0,0,2,0,Color.red));
                else
                    passwordEntryPanel1.setBorder(new MatteBorder(0,0,2,0,new Color(70,73,75)));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                boolean test = checkingThePass(passField.getText(),passCheckField.getText());
                if(!test)
                    passwordEntryPanel1.setBorder(new MatteBorder(0,0,2,0,Color.red));
                else
                    passwordEntryPanel1.setBorder(new MatteBorder(0,0,2,0,new Color(70,73,75)));
            }
        });
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets( 0, 0, 0 ,0);
        gbc1.weightx = 8;
        gbc1.weighty= 1;
        gbc1.gridwidth = 10;
        gbc1.gridheight = 1;
        gbc1.fill=GridBagConstraints.BOTH;
        passwordEntryPanel1.add(passCheckField,gbc1);

//        centralPanel.add(passField, gbc);
        JCheckBox showPasswordCheckCheckbox = new JCheckBox();
        showPasswordCheckCheckbox.setForeground(orange);
        showPasswordCheckCheckbox.setFont(fontRegular);
        showPasswordCheckCheckbox.setOpaque(false);
//        gbc.gridx = 2;
        gbc1.gridx = 10;
        gbc1.weightx = 0.2;
        gbc1.gridwidth = 1;
        gbc1.anchor = GridBagConstraints.EAST;

        showPasswordCheckCheckbox.setSize(8,8);

        passwordEntryPanel1.add(showPasswordCheckCheckbox,gbc1);
        passwordEntryPanel1.setBackground(new Color(70,73,75));
        passwordEntryPanel1.setBorder(new LineBorder(MainFrame.extraLightGray,1));
//        gbc.insets= new Insets (0 , 0 , 0 , 1);
        gbc.gridx = 1;
        centralPanel.add(passwordEntryPanel1, gbc);
//        gbc.insets = new Insets(20, 20, 20, 20);
//
        showPasswordCheckCheckbox.addActionListener(e -> {
            if (showPasswordCheckCheckbox.isSelected()) {
                passCheckField.setEchoChar((char) 0);
            } else {
                passCheckField.setEchoChar('•');
            }
        });

        JLabel chooseUserLabel = createJLabel("User:", gbc, 0, 4);
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
        radioPanel.setBackground(darkGray);
        radioPanel.add(chooseCustomerButton);
        radioPanel.add(chooseEmployeeButton);
        radioPanel.add(chooseManagerButton);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        centralPanel.add(radioPanel, gbc);

        JLabel optionalFieldLabel = createJLabel("Employee/Manager Code:", gbc, 0, 6);
        centralPanel.add(optionalFieldLabel, gbc);

        JTextField optionalField = new JTextField(5);
        optionalField.setFont(fieldsFont);

        optionalField.setEnabled(false);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 30, 5, 10);
        optionalField.setBorder(new LineBorder(MainFrame.extraLightGray,1));
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

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(centralPanel, gbc);

        ActionListener radioActionListener = e -> {
            if (chooseEmployeeButton.isSelected() || chooseManagerButton.isSelected()) {
                optionalField.setEnabled(true);
            } else {
                optionalField.setEnabled(false);
            }
        };

        chooseCustomerButton.addActionListener(radioActionListener);
        chooseEmployeeButton.addActionListener(radioActionListener);
        chooseManagerButton.addActionListener(radioActionListener);

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
    }

    JLabel createJLabel(String message, GridBagConstraints gbc, int gridx, int gridy) {
        JLabel label = new JLabel(message);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        label.setFont(fontRegular);
        return label;
    }
    public static int passwordCheck(String password) {
        if (password.length() < 8) {
            return 0;
        }
        int score = 1;

        String upperCaseCheck = ".*[A-Z].*";
        String numberCheck = ".*\\d.*";
        String symbolCheck = ".*[!@#$%^&*()\\-_=+{}\\[\\]:;\"'<>,.?/`~|\\\\].*";

        if (password.matches(upperCaseCheck))
            score++;

        if (password.matches(numberCheck))
            score++;

        if (password.matches(symbolCheck))
            score++;


        return score;
    }
    public static boolean isValidEmail(String email) {
        String emailCheck = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailCheck);
    }
    public static boolean checkingThePass(String pass1,String pass2) {

        return pass1.equals(pass2);
    }



}
