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
    Payments payments = new Payments() ;
    MealsController mealsController;
    Report report = new Report(0,0);

    public MainController(){
        users.loadFromFile();
        meals.loadFromFile();
        report.loadFromFile();
        payments.loadFromFile();
        loginAndRegistrationFrame = new LoginAndRegistrationFrame();

        loginAndRegisterManager = new LoginAndRegisterManager(users,loginAndRegistrationFrame,user);

        loginListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user =  loginAndRegisterManager.loginCheck();
                if(user!=null) {
                    loginAndRegistrationFrame.dispose();
                    profilePanel= new ProfilePanel(user);
                    profileController = new ProfileController(users,profilePanel);
                    reportPanel = new ReportPanel(report,users.getUsers().size(),meals.getMeals().size());
                    mealsPanel = new MealsPanel(user);
                    allOrdersPanel= new AllOrdersPanel(user,orders);
                    mainFrame = new MainFrame(user,profilePanel,reportPanel,allOrdersPanel);
                    mainFrame.mealsPanel.fillMainMenu(meals.getMeals());
                    profilePanel.logoutButton.addActionListener(logoutListener);

                    mealsController = new MealsController(meals, mainFrame.mealsPanel, user) ;

                    if( user.getUserType() == 0){

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

                    }
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
        MainFrame mainFrame=new MainFrame(user,profilePanel,reportPanel,allOrdersPanel);
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



