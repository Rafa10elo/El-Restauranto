package View;

import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ProfilePanel extends JPanel {
    public static Color lightGray = new Color(43, 45, 48) ;
    public static Color darkGray = new Color(30, 31, 34) ;
    public static Color orange = new Color(206, 129, 76) ;
    public static Font fontBold = null ;
    public static Font fontRegular = null ;
    public static Font fontBigBold = null ;

    static {
        try {
            fontBold = Font.createFont( Font.TRUETYPE_FONT, new File("src/View/Fonts/AmaticSC-Bold.ttf")).deriveFont(40f) ;
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            fontBigBold = Font.createFont( Font.TRUETYPE_FONT, new File("src/View/Fonts/AmaticSC-Bold.ttf")).deriveFont(60f) ;
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            fontRegular = Font.createFont( Font.TRUETYPE_FONT, new File("src/View/Fonts/AmaticSC-Regular.ttf")).deriveFont(40f) ;
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private CardLayout cardLayout;
    private JPanel cardPanel;

    public ProfilePanel(User user) {
        setLayout(new BorderLayout());

        //sidebar
        JPanel profileSidebarPanel = new JPanel();
        profileSidebarPanel.setLayout(new BorderLayout());
        profileSidebarPanel.setBackground(lightGray);
        profileSidebarPanel.setPreferredSize(new Dimension(200, 400));
        profileSidebarPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, orange));
        add(profileSidebarPanel, BorderLayout.WEST);

        //pfp
        ImageIcon profileIcon = new ImageIcon("src/View/Images/profilePicture.png");
        Image scaledImage = profileIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel profilePicLabel = new JLabel(new ImageIcon(scaledImage));
        profilePicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profileSidebarPanel.add(profilePicLabel, BorderLayout.NORTH);

        //sidebar buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(lightGray);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton editProfileButton = new JButton("Edit");
        editProfileButton.setFont(fontBold);
        editProfileButton.setFocusPainted(false);
        editProfileButton.setBackground(orange);
        editProfileButton.setForeground(darkGray);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(fontBold);
        logoutButton.setFocusPainted(false);
        logoutButton.setBackground(orange);
        logoutButton.setForeground(darkGray);

        buttonPanel.add(editProfileButton);
        buttonPanel.add(logoutButton);
        profileSidebarPanel.add(buttonPanel, BorderLayout.SOUTH);

        //switching card layout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        add(cardPanel, BorderLayout.CENTER);

        //main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(darkGray);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel welcomeLabel = new JLabel("Welcome, "+user.getUserName()+" !");
        welcomeLabel.setFont(fontBigBold);
        welcomeLabel.setForeground(orange);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(welcomeLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(fontBold);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(usernameLabel, gbc);

        JLabel usernameValue = new JLabel(user.getUserName());
        usernameValue.setFont(fontRegular);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(usernameValue, gbc);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(fontBold);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(emailLabel, gbc);

        JLabel emailValue = new JLabel(user.getEmail());
        emailValue.setFont(fontRegular);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(emailValue, gbc);

        JLabel userTypeLabel = new JLabel("User:");
        userTypeLabel.setFont(fontBold);
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(userTypeLabel, gbc);

        String userType= "";
        switch (user.getUserType()){
            case 0:
                userType="Customer";
                break;
            case 1:
                userType="Employee";
                break;
            case 2:
                userType="Manager";
                break;
        }

        JLabel userTypeValue = new JLabel(userType);
        userTypeValue.setFont(fontRegular);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(userTypeValue, gbc);

        //edit panel
        JPanel editPanel = new JPanel();
        editPanel.setLayout(new GridBagLayout());
        editPanel.setBackground(darkGray);


        JLabel editUsernameLabel = new JLabel("Username:");
        editUsernameLabel.setFont(fontBold);
        editUsernameLabel.setForeground(orange);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        editPanel.add(editUsernameLabel, gbc);

        JTextField usernameTextField = new JTextField(user.getUserName(), 15);
        usernameTextField.setFont(fontRegular);
        gbc.gridx = 1;
        gbc.gridy = 0;
        editPanel.add(usernameTextField, gbc);


        JLabel editEmailLabel = new JLabel("Email:");
        editEmailLabel.setFont(fontBold);
        editEmailLabel.setForeground(orange);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        editPanel.add(editEmailLabel, gbc);

        JTextField editEmailField = new JTextField(user.getEmail(), 15);
        editEmailField.setFont(fontRegular);
        gbc.gridx = 1;
        gbc.gridy = 1;
        editPanel.add(editEmailField, gbc);


        JLabel editPasswordLabel = new JLabel("Password:");
        editPasswordLabel.setFont(fontBold);
        editPasswordLabel.setForeground(orange);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        editPanel.add(editPasswordLabel, gbc);

        JTextField editPasswordField = new JTextField(user.getPassword(), 15);
        editPasswordField.setFont(fontRegular);
        gbc.gridx = 1;
        gbc.gridy = 2;
        editPanel.add(editPasswordField, gbc);

        cardPanel.add(mainPanel, "main");
        cardPanel.add(editPanel, "edit");

        //to switch between edit and main panel
        editProfileButton.addActionListener(new ActionListener() {
            private boolean inEditMode = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (inEditMode) {
                    // Switch back to main panel
                    cardLayout.show(cardPanel, "main");
                    editProfileButton.setText("Edit");
                    inEditMode = false;
                } else {
                    // Switch to edit panel
                    cardLayout.show(cardPanel, "edit");
                    editProfileButton.setText("Save");
                    inEditMode = true;
                }
            }
        });
    }
}
