package Control;

import Model.Order;
import Model.User;
import Model.*;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


public class MainController {
    User user;
    LoginAndRegistrationFrame loginAndRegistrationFrame;
    LoginAndRegisterManager loginAndRegisterManager;
    ActionListener actionListener1;
    ActionListener actionListener;
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

        loginAndRegisterManager = new LoginAndRegisterManager(users,loginAndRegistrationFrame,user);

        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user =  loginAndRegisterManager.loginCheck();
                if(user!=null) {
                    loginAndRegistrationFrame.dispose();
                    profilePanel= new ProfilePanel(user);
                    profileController = new ProfileController(users,profilePanel);
                    reportPanel = new ReportPanel(report,users.getUsers().size(),meals.getMeals().size());
                    mealsPanel = new MealsPanel(user.getUserType());
                    allOrdersPanel= new AllOrdersPanel(user,orders);
                    mainFrame = new MainFrame(user.getUserType(),profilePanel,reportPanel,allOrdersPanel);
                    mainFrame.mealsPanel.fillMainMenu(meals.getMeals());
                    profilePanel.logoutButton.addActionListener(actionListener1);

                    ActionListener addMealActionListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (mainFrame.mealsPanel.getSidePanel().mealInfoValid()){
                                Meal newMeal = mainFrame.mealsPanel.getNewMealInfo() ;
                                // add to model + write
                                meals.addMeal(newMeal);
                                meals.writerThread();
                                // edit view + add it to hashmap
                                mainFrame.mealsPanel.getSidePanel().newMealReset();
                                mainFrame.mealsPanel.fillMainMenu(meals.getMeals());
                            }
                        }};
                    mainFrame.mealsPanel.getSidePanel().getAddMealButton().addActionListener(addMealActionListener);

                }

            }
        };

        actionListener1= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                loginAndRegistrationFrame = new LoginAndRegistrationFrame();
                loginAndRegisterManager=new LoginAndRegisterManager(users,loginAndRegistrationFrame,user);
                loginAndRegistrationFrame.loginPanel.loginButton.addActionListener(actionListener);
            }
        };

        loginAndRegistrationFrame.loginPanel.loginButton.addActionListener(actionListener);







    }



}
