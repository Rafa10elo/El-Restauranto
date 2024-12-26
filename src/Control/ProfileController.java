package Control;

import Model.User;
import Model.*;
import View.ProfilePanel;
import View.RegisterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class ProfileController {
    Users users;
    ProfilePanel profilePanel;
    User wantedUser;
    public boolean inEditMode;

    ProfileController(Users users ,ProfilePanel profilePanel)
    {
        this.users = users;
        this.profilePanel =profilePanel;
        wantedUser= profilePanel.getUser();
        inEditMode =false;
        profilePanel.editProfileButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (inEditMode) {

                    updateUser();

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
        return;
        }
         if (RegisterPanel.passwordCheck(profilePanel.getEditedPassword())==0)
         {
             JOptionPane.showMessageDialog(profilePanel.getMainPanel(), "not correct password formula!", "Error", JOptionPane.INFORMATION_MESSAGE);
             return;
         }



            wantedUser.setUserName(profilePanel.getEditedUsername());
            wantedUser.setEmail(profilePanel.getEditedEmail());
            wantedUser.setPassword(profilePanel.getEditedPassword());
            users.writerThread();

         profilePanel.mainPanel = profilePanel.fillProfile(wantedUser);
         profilePanel.getCardPanel().add(profilePanel.mainPanel, "main");
         profilePanel.getCardLayout().show(profilePanel.getCardPanel(), "main");
         profilePanel.editProfileButton.setText("Edit");
         inEditMode = false;


     }



}
