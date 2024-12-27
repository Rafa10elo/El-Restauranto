package Control;

import Model.Order;
import Model.User;
import Model.*;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainController {
    User user;
    LoginAndRegistrationFrame loginAndRegistrationFrame;
    LoginAndRegisterManager loginAndRegisterManager;
    ActionListener logoutListener;
    ActionListener loginListener;
    MainFrame mainFrame ;
    ProfilePanel profilePanel;
    MealsPanel mealsPanel;
    ReportPanel reportPanel;
    AllOrdersPanel allOrdersPanel;
    ProfileController profileController;
    Users users= new Users();
    Orders orders = Orders.getOrdersSing();
    Meals meals = new Meals();
    Report report = new Report(0,0);

    public MainController(){
        users.loadFromFile();
        meals.loadFromFile();
        report.loadFromFile();
        loginAndRegistrationFrame = new LoginAndRegistrationFrame();
        loginAndRegisterManager=new LoginAndRegisterManager(users,loginAndRegistrationFrame,user);

        loginListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user =  loginAndRegisterManager.loginCheck();
                if(user!=null)
                {
                    loginAndRegistrationFrame.dispose();
                    profilePanel= new ProfilePanel(user);
                    profileController = new ProfileController(users,profilePanel);
                    reportPanel = new ReportPanel(report,users.getUsers().size(),meals.getMeals().size());
                    mealsPanel = new MealsPanel(user.getUserType());
                    allOrdersPanel= new AllOrdersPanel(user,orders);
                    mainFrame = createMainFrame(user.getUserType(),profilePanel,reportPanel,allOrdersPanel,report,users,meals);
                    profilePanel.logoutButton.addActionListener(logoutListener);
              }
            }
        };

        logoutListener= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                loginAndRegistrationFrame = new LoginAndRegistrationFrame();
                loginAndRegisterManager=new LoginAndRegisterManager(users,loginAndRegistrationFrame,user);
                loginAndRegistrationFrame.loginPanel.loginButton.addActionListener(loginListener);

            }

        };

        loginAndRegistrationFrame.loginPanel.loginButton.addActionListener(loginListener);

    }

    MainFrame createMainFrame(int userType, ProfilePanel profilePanel,ReportPanel reportPanel,AllOrdersPanel allOrdersPanel,Report report,Users users,Meals meals){
        MainFrame mainFrame=new MainFrame(userType,profilePanel,reportPanel,allOrdersPanel);
        mainFrame.profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.cardLayout.show(mainFrame.cardsPanel, "profilePanel");
            }
        });

        mainFrame.mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.cardLayout.show(mainFrame.cardsPanel, "mealsPanel");
            }
        });

        mainFrame.allOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllOrdersPanel updatedAllOrders = new AllOrdersPanel(user,orders);
                mainFrame.cardsPanel.add(updatedAllOrders,"allOrdersPanel") ;
                mainFrame.cardLayout.show(mainFrame.cardsPanel, "allOrdersPanel");
            }
        });


        mainFrame.reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportPanel updatedReport = new ReportPanel(report,users.getUsers().size(),meals.getMeals().size());
                mainFrame.cardsPanel.add(updatedReport,"reportPanel");
                mainFrame.cardLayout.show(mainFrame.cardsPanel, "reportPanel");
            }
        });

        mainFrame.revalidate();
        mainFrame.repaint();
        return mainFrame;
    }

}



