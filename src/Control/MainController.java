package Control;

import Model.User;
import Model.*;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    Payments payments = new Payments() ;

    Report report = new Report(0,0);

    public MainController(){
        users.loadFromFile();
        meals.loadFromFile();
        report.loadFromFile();
        payments.loadFromFile();
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

                    if( user.getUserType() == 0){
                        // add click action listener to meal panels in main menu : click = add to order
                        for (Map.Entry<Meal, MealPanel> entry : mainFrame.mealsPanel.getAllMeals().entrySet()) {
                            entry.getValue().addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    mainFrame.mealsPanel.addMealPanelToOrder(entry.getKey());
                                }
                            });
                        }
                        // ADD ORDER BUTTON (PAY + CANCEL)
                        ActionListener addOrderActionListener = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (mainFrame.mealsPanel.getSidePanel().paymentInfoValid()){
                                    // payment
                                    Payment payment ;
                                    if (mainFrame.mealsPanel.getSidePanel().getMethod().equals("cash")){
                                        payment = new Payment(mainFrame.mealsPanel.getSidePanel().getPaymentAmount(), "cash") ;
                                    }else{
                                        if( !payments.checkTheCreditCard(mainFrame.mealsPanel.getSidePanel().getCreditCardId().getText()) ){
                                            payment = null ;
                                        }else{
                                            payment = new Payment(mainFrame.mealsPanel.getSidePanel().getPaymentAmount(), "credit card") ;
                                        }
                                    }

                                    // order and add payment to payments
                                    if ( payment != null ){
//                                            payments.writerThread();

//                                            Order order = new Order(mainFrame.mealsPanel.getSidePanel().getOrderMeals(), mainFrame.mealsPanel.getSidePanel().getTotalPrice(), mainFrame.mealsPanel.getSidePanel().getTipsCombo(),
//                                                    /* time and status ?*/);
//                                            no orders writer thread
//                                            add order to orders
                                        mainFrame.mealsPanel.getSidePanel().orderReset();
                                        JOptionPane.showMessageDialog(mainFrame, "Your order is successfully added! :) ", "order added", JOptionPane.INFORMATION_MESSAGE);
                                    }else{
//                                            Order order = new CANCELED
//                                            writer thread
//                                            add to orders
                                        mainFrame.mealsPanel.getSidePanel().orderReset();
                                        JOptionPane.showMessageDialog(mainFrame, "the credit card ID is not valid, your order will be canceled", "incorrect ID", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        };
                        mainFrame.mealsPanel.getSidePanel().getPayButton().addActionListener(addOrderActionListener);

                        ActionListener cancelPaymentAndOrder = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
//                                Order order = new CANCELED
//                                            writer thread
//                                            add to orders
                                JOptionPane.showMessageDialog(mainFrame, "Your order is canceled", "", JOptionPane.INFORMATION_MESSAGE);
                                mainFrame.mealsPanel.getSidePanel().orderReset();
                            }
                        };
                        mainFrame.mealsPanel.getSidePanel().getCancelPayButton().addActionListener(cancelPaymentAndOrder);

                    }else{
                        // add click action listener to meal panels in main menu : click = EDIT
                        for (Map.Entry<Meal, MealPanel> entry : mainFrame.mealsPanel.getAllMeals().entrySet()) {entry.getValue().addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    mainFrame.mealsPanel.createEditMealDialog(entry.getKey());
                                }
                            });
                        }
                        // ADD MEAL BUTTON
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
                                    JOptionPane.showMessageDialog(mainFrame, "Meal added successfully! :)", "", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }};
                        mainFrame.mealsPanel.getSidePanel().getAddMealButton().addActionListener(addMealActionListener);

                        // EDIT MEAL
                        ActionListener editMealActionListener = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (mainFrame.mealsPanel.editMealInfoValid()){
                                    Meal editedMeal = mainFrame.mealsPanel.getEditedMealInfo() ;
                                    // edit view
                                    mainFrame.mealsPanel.getMealPanel(mainFrame.mealsPanel.getCurrentMeal()).setMealInfo(editedMeal);
                                    mainFrame.mealsPanel.editMeals(mainFrame.mealsPanel.getCurrentMeal(), editedMeal) ;
                                    // edit model
                                    meals.modifyMeal(mainFrame.mealsPanel.getCurrentMeal(), editedMeal);
                                    meals.writerThread();

                                    mainFrame.mealsPanel.setCurrentMeal(null);

                                    mainFrame.mealsPanel.getEditMealDialog().removeAll();
                                    mainFrame.mealsPanel.getEditMealDialog().dispose();

                                    JOptionPane.showMessageDialog(mainFrame, "Meal edited successfully!", "", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        };
                        mainFrame.mealsPanel.getEditMealButton().addActionListener(editMealActionListener);

                        // DELETE MEAL
                        ActionListener deleteMeal = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // edit model
                                meals.deleteMeal(mainFrame.mealsPanel.getCurrentMeal()) ;
                                meals.writerThread();
                                // edit view
                                mainFrame.mealsPanel.getAllMeals().remove(mainFrame.mealsPanel.getCurrentMeal()) ;
                                mainFrame.mealsPanel.fillMainMenu(meals.getMeals());

                                mainFrame.mealsPanel.setCurrentMeal(null);

                                mainFrame.mealsPanel.getEditMealDialog().removeAll();
                                mainFrame.mealsPanel.getEditMealDialog().dispose();

                                JOptionPane.showMessageDialog(mainFrame, "Meal deleted successfully!", "", JOptionPane.INFORMATION_MESSAGE);
                            }
                        };
                        mainFrame.mealsPanel.getDeleteMeal().addActionListener(deleteMeal);
                    }
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
