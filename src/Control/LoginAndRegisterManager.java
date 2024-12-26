package Control;

import Model.*;
import View.LoginAndRegistrationFrame;
import View.LoginPanel;
import View.RegisterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAndRegisterManager {
    private User user;
    private Users users;
    private LoginAndRegistrationFrame loginAndRegistrationFrame;

   public LoginAndRegisterManager (Users users,LoginAndRegistrationFrame loginAndRegistrationFrame,User user){
       this.user=user;
       this.users = users;
       this.loginAndRegistrationFrame =loginAndRegistrationFrame;


       loginAndRegistrationFrame.registerPanel.getRegisterButton().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
        registrationCheck();

           }
       });


   }
   public void registrationCheck(){
       String username = loginAndRegistrationFrame.registerPanel.getUsername();
       String email = loginAndRegistrationFrame.registerPanel.getEmail();
       String password =loginAndRegistrationFrame.registerPanel.getPassword();
       String checkPass = loginAndRegistrationFrame.registerPanel.getCheckPass();
       String code = loginAndRegistrationFrame.registerPanel.getOptionalText();
       int test;
       if(loginAndRegistrationFrame.registerPanel.getChooseCustomer())
           test = 0;
       else if (loginAndRegistrationFrame.registerPanel.getChooseEmployee())
           test = 1 ;
       else
           test = 2;
    User user=   users.findUser(username);
    if(user!=null){
        JOptionPane.showMessageDialog(loginAndRegistrationFrame, "already existed username ,try another one", "error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if(!RegisterPanel.isValidEmail(email))
    {
        JOptionPane.showMessageDialog(loginAndRegistrationFrame, "incorrect email form try again", "error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if(RegisterPanel.passwordCheck(password)== 0)
    {
        JOptionPane.showMessageDialog(loginAndRegistrationFrame, "enter a password that contains 8 characters at least", "error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (!RegisterPanel.checkingThePass(password,checkPass)){
        JOptionPane.showMessageDialog(loginAndRegistrationFrame, "the password confirmation is not correct try again", "error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (loginAndRegistrationFrame.registerPanel.getChooseEmployee()&&!code.equals("11109"))
    {
        JOptionPane.showMessageDialog(loginAndRegistrationFrame, "incorrect employee code please try again.", "error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (loginAndRegistrationFrame.registerPanel.getChooseManager()&&!code.equals("91011"))
    {
        JOptionPane.showMessageDialog(loginAndRegistrationFrame, "incorrect manager code please try again.", "error", JOptionPane.ERROR_MESSAGE);
        return;
    }

       JOptionPane.showMessageDialog(loginAndRegistrationFrame, "welcome to our system sir now you can login", "error", JOptionPane.ERROR_MESSAGE);


       User newUser = new User(username,email,password,test);
        users.addUser(newUser);

       users.writerThread();
       loginAndRegistrationFrame.cardLayout.show(loginAndRegistrationFrame.mainPanel,"Login");




   }
   public User loginCheck(){
       String username=   loginAndRegistrationFrame.loginPanel.getUsername();
       String password=  loginAndRegistrationFrame.loginPanel.getPassword();
       user = users.findUser(username);
       if(user!=null)
       {
           if(users.isCorrectPassword(user,password))
           {
               JOptionPane.showMessageDialog(loginAndRegistrationFrame, "successfully logged in", "success", JOptionPane.INFORMATION_MESSAGE);
              return user;
           }
           else
           {
           JOptionPane.showMessageDialog(loginAndRegistrationFrame, "incorrect password please try again.", "error", JOptionPane.ERROR_MESSAGE);
           return null;
           }
       }
       else
       {
           JOptionPane.showMessageDialog(loginAndRegistrationFrame, "there is no user with this username", "error", JOptionPane.ERROR_MESSAGE);
       return null;
       }

   }






}
