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
    JButton editAndSaveButton;
    ProfileController(Users users ,ProfilePanel profilePanel)
    {
        this.users = users;
        this.profilePanel =profilePanel;
        wantedUser= profilePanel.getUser();
        editAndSaveButton= profilePanel.editProfileButton;
        editAndSaveButton.addActionListener(new ActionListener() {
            private boolean inEditMode = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (inEditMode) {
                    // Switch back to main panel
                    profilePanel.getCardLayout().show(profilePanel.getCardPanel(), "main");
                    updateUser();
                    editAndSaveButton.setText("Edit");
                    inEditMode = false;
                } else {
                    // Switch to edit panel
                    String passwordDialog = JOptionPane.showInputDialog(profilePanel.mainPanel, "Enter your password:");
                    if(Objects.equals(passwordDialog, wantedUser.getPassword())){
                        profilePanel.getCardLayout().show(profilePanel.getCardPanel(), "edit");
                        inEditMode = true;
                        editAndSaveButton.setText("Save");
                    }
                    else
                        JOptionPane.showMessageDialog(profilePanel.mainPanel, "Access Denied", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


    }
     void updateUser(){
        if(users.findUser(profilePanel.getName())!=null){
            JOptionPane.showMessageDialog(profilePanel.getMainPanel(), "Username already taken!", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            wantedUser.setUserName(profilePanel.getEditedUsername());
            wantedUser.setEmail(profilePanel.getEditedEmail());
            wantedUser.setPassword(profilePanel.getEditedPassword());
        }
    }



}
