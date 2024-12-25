import Control.OrderTimerManager;
import Model.Meal;
import Model.Report;
import Model.Order;
import View.LoginAndRegistrationFrame;
import View.MainFrame;
import View.ReportPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
//import java.util.Timer;


public class Main {


    public static void main(String[] args) {

        // sarah test
//        MainFrame mainFrame = new MainFrame(0);
//        ReportPanel reportPanel = new ReportPanel(new Report(54,5562),50,19);
//        mainFrame.add(reportPanel, BorderLayout.CENTER);
//        mainFrame.setVisible(true);
        //sorrryy bhhh

//        ArrayList<Meal> meals = new ArrayList<>();
//        meals.add(new Meal("Burger", "213",321,"3"));
//        meals.add(new Meal("Fries", "31",231,"213"));
//
//        ArrayList<Meal> meals1 = new ArrayList<>();
//        meals.add(new Meal("Burger", "213",321,"3"));
//        meals.add(new Meal("Fries", "31",231,"213"));
//
//        LocalDateTime deliveryTime = LocalDateTime.now().plusSeconds(5);
//        LocalDateTime deliveryTime1 = LocalDateTime.now().plusSeconds(10);
//
//        Order order = new Order(meals, 2, Order.Status.PREPARED);
//        Order order1 = new Order(meals, 2, Order.Status.PREPARED);
//
//        order.setTimeOfDelivery(deliveryTime);
//        order1.setTimeOfDelivery(deliveryTime1);
//        OrderTimerManager timerManager = new OrderTimerManager();
//        System.out.println("Order status: " + order.getState());
//        System.out.println("Order status: " + order.getState());
//        timerManager.showRemainingTime(order);
//        timerManager.showRemainingTime(order1);

//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Final order status: " + order.getState());





//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        System.out.println("Final order status: " + order.getState());



//        Report report = new Report(2,333);
//        Meal meal = new Meal("piz123","21321",33,"21321");
//        Meal meal1 = new Meal("pizz","21321",33,"21321");
//        Meal meal3 = new Meal("forma","21321",33,"21321");
//        report.incrementMealCount(meal,201);
//        report.incrementMealCount(meal3,1221);
//        report.incrementMealCount(meal1,1000);
//        System.out.println(report.getMostSoldMeal().getMealName());
//        report.incrementMealCount(meal,10000);
//        System.out.println(report.getMostSoldMeal().getMealName());

//        SwingUtilities.invokeLater(MainFrame :: new);
//        LoginAndRegistrationFrame loginAndRegistrationFrame = new LoginAndRegistrationFrame();
//        loginAndRegistrationFrame.setVisible(true);

        // just a simple test AYA, to try the meals panel ... disfruta 🤌 (ya3ni enjoy in spanish)
        ArrayList<Meal> mealsAya = new ArrayList<>();
        for (int i = 1 ; i < 16 ; i ++) {
            String name = "meal " + i ;
            String ing = "ing " + i ;
            float price = i ;
            mealsAya.add(new Meal(name, ing, price, "src/pics/" + i + ".jpg"));
        }

        // user type : 0 -> customer (order panel)      1,2 -> worker, boss (add meal panel)
        MainFrame mainFrame = new MainFrame(0);
        mainFrame.mealsPanel.fillMainMenu(mealsAya);
        //---------------------------------------------------------------------------just for fun
//        mainFrame.mealsPanel.getSidePanel().createPaymentDialog();
//        mainFrame.mealsPanel.getSidePanel().cancelPay.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                mainFrame.mealsPanel.getSidePanel().paymentDialog.dispose();
//            }
//        });

//        LoginAndRegistrationFrame loginAndRegistrationFrame= new LoginAndRegistrationFrame();
    }
}