package Control;

import Model.*;
import View.LoginAndRegistrationFrame;
import View.LoginPanel;
import View.RegisterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAndRegisterManager {
    private Users users;
    private LoginAndRegistrationFrame loginAndRegistrationFrame;

   public LoginAndRegisterManager (Users users,LoginAndRegistrationFrame loginAndRegistrationFrame){
       this.users = users;
       this.loginAndRegistrationFrame =loginAndRegistrationFrame;

       loginAndRegistrationFrame.loginPanel.loginButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.out.println("button pressed");
               loginCheck();
           }
       });


   }

   public void loginCheck(){
       String username=   loginAndRegistrationFrame.loginPanel.getUsername();
       String password=  loginAndRegistrationFrame.loginPanel.getPassword();
       User user = users.findUser(username);
       if(user!=null)
       {
           if(users.isCorrectPassword(user,password))
           {
               JOptionPane.showMessageDialog(loginAndRegistrationFrame, "successfully logged in", "success", JOptionPane.INFORMATION_MESSAGE);
               loginAndRegistrationFrame.dispose();
               new JFrame().setVisible(true);// هون بدنا نعمل الفريم الكبيرة التانية تبعكم

           }
           else
           {
           JOptionPane.showMessageDialog(loginAndRegistrationFrame, "incorrect password please try again.", "error", JOptionPane.ERROR_MESSAGE);
           }
       }
       else
       {
           JOptionPane.showMessageDialog(loginAndRegistrationFrame, "there is no user with this username", "error", JOptionPane.ERROR_MESSAGE);
       }

   }






}
