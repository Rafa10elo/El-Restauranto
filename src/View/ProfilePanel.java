package View;

import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ProfilePanel extends JPanel {


    private CardLayout cardLayout;
    private JPanel cardPanel;

    public ProfilePanel(User user) {
        setLayout(new BorderLayout());

        //sidebar
        JPanel profileSidebarPanel = new JPanel();
        profileSidebarPanel.setLayout(new BorderLayout());
        profileSidebarPanel.setBackground(MainFrame.lightGray);
        profileSidebarPanel.setPreferredSize(new Dimension(200, 400));
        profileSidebarPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, MainFrame.orange));
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
        buttonPanel.setBackground(MainFrame.lightGray);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton editProfileButton = new JButton("Edit");
        editProfileButton.setFont(MainFrame.fontBold.deriveFont(40F));
        editProfileButton.setFocusPainted(false);
        editProfileButton.setBackground(MainFrame.orange);
        editProfileButton.setForeground(MainFrame.darkGray);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(MainFrame.fontBold.deriveFont(40F));
        logoutButton.setFocusPainted(false);
        logoutButton.setBackground(MainFrame.orange);
        logoutButton.setForeground(MainFrame.darkGray);

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
        mainPanel.setBackground(MainFrame.darkGray);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel welcomeLabel = new JLabel("Welcome, "+user.getUserName()+" !");
        welcomeLabel.setFont(MainFrame.fontBold.deriveFont(60F));
        welcomeLabel.setForeground(MainFrame.orange);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(welcomeLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(MainFrame.fontBold.deriveFont(40F));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(usernameLabel, gbc);

        JLabel usernameValue = new JLabel(user.getUserName());
        usernameValue.setFont(MainFrame.fontRegular.deriveFont(40F));
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(usernameValue, gbc);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(MainFrame.fontBold.deriveFont(40F));
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(emailLabel, gbc);

        JLabel emailValue = new JLabel(user.getEmail());
        emailValue.setFont(MainFrame.fontRegular.deriveFont(40F));
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(emailValue, gbc);

        JLabel userTypeLabel = new JLabel("User:");
        userTypeLabel.setFont(MainFrame.fontBold.deriveFont(40F));
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
        userTypeValue.setFont(MainFrame.fontRegular.deriveFont(40F));
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(userTypeValue, gbc);

        //edit panel
        JPanel editPanel = new JPanel();
        editPanel.setLayout(new GridBagLayout());
        editPanel.setBackground(MainFrame.darkGray);

        JLabel editUsernameLabel = new JLabel("Username:");
        editUsernameLabel.setFont(MainFrame.fontBold.deriveFont(40F));
        editUsernameLabel.setForeground(MainFrame.orange);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        editPanel.add(editUsernameLabel, gbc);

        JTextField usernameTextField = new JTextField(user.getUserName(), 15);
        usernameTextField.setFont(MainFrame.fontRegular.deriveFont(40F));
        gbc.gridx = 1;
        gbc.gridy = 0;
        editPanel.add(usernameTextField, gbc);


        JLabel editEmailLabel = new JLabel("Email:");
        editEmailLabel.setFont(MainFrame.fontBold.deriveFont(40F));
        editEmailLabel.setForeground(MainFrame.orange);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        editPanel.add(editEmailLabel, gbc);

        JTextField editEmailField = new JTextField(user.getEmail(), 15);
        editEmailField.setFont(MainFrame.fontRegular.deriveFont(40F));
        gbc.gridx = 1;
        gbc.gridy = 1;
        editPanel.add(editEmailField, gbc);


        JLabel editPasswordLabel = new JLabel("Password:");
        editPasswordLabel.setFont(MainFrame.fontBold.deriveFont(40F));
        editPasswordLabel.setForeground(MainFrame.orange);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        editPanel.add(editPasswordLabel, gbc);

        JTextField editPasswordField = new JTextField(user.getPassword(), 15);
        editPasswordField.setFont(MainFrame.fontRegular.deriveFont(40F));
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
