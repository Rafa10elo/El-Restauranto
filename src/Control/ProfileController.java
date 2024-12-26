package Control;

import Model.User;
import Model.*;
import View.ProfilePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ProfileController {
    Users users;
    ProfilePanel profilePanel;
    User wantedUser;

    ProfileController(Users users ,ProfilePanel profilePanel)
    {
        this.users = users;
        this.profilePanel =profilePanel;
        wantedUser= profilePanel.getUser();
        profilePanel.editProfileButton.addActionListener(new ActionListener() {
            private boolean inEditMode = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (inEditMode) {
                    // Switch back to main panel

                    updateUser();
                    profilePanel.mainPanel = profilePanel.fillProfile(wantedUser);
                    profilePanel.getCardPanel().add(profilePanel.mainPanel, "main");
                    profilePanel.getCardLayout().show(profilePanel.getCardPanel(), "main");
                    profilePanel.editProfileButton.setText("Edit");
                    inEditMode = false;
                } else {
                    // Switch to edit panel
                    String passwordDialog = JOptionPane.showInputDialog(profilePanel.fillProfile(wantedUser), "Enter your password:");
                    if(passwordDialog.equals(wantedUser.getPassword())){
                        profilePanel.getCardLayout().show(profilePanel.getCardPanel(), "edit");
                        inEditMode = true;
                        profilePanel.editProfileButton.setText("Save");
                    }
                    else
                        JOptionPane.showMessageDialog(profilePanel.mainPanel, "Access Denied", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


    }
     void updateUser(){
        User user = users.findUser(profilePanel.getEditedUsername());
        if(users.findUser(profilePanel.getEditedUsername())!=null){
            JOptionPane.showMessageDialog(profilePanel.getMainPanel(), "Username already taken!", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            wantedUser.setUserName(profilePanel.getEditedUsername());
            wantedUser.setEmail(profilePanel.getEditedEmail());
            wantedUser.setPassword(profilePanel.getEditedPassword());
            users.writerThread();
        }
    }



}
