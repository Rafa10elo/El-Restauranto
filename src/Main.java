import Control.OrderTimerManager;
import Model.Meal;
import Model.Report;
import Model.Order;
import View.LoginAndRegistrationFrame;
import View.MainFrame;

import javax.swing.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        ArrayList<Meal> meals = new ArrayList<>();
        meals.add(new Meal("Burger", "213",321,"3"));
        meals.add(new Meal("Fries", "31",231,"213"));

        ArrayList<Meal> meals1 = new ArrayList<>();
        meals.add(new Meal("Burger", "213",321,"3"));
        meals.add(new Meal("Fries", "31",231,"213"));

        LocalDateTime deliveryTime = LocalDateTime.now().plusSeconds(5);
        LocalDateTime deliveryTime1 = LocalDateTime.now().plusSeconds(10);

        Order order = new Order(meals, 2, Order.Status.PREPARED);
        Order order1 = new Order(meals, 2, Order.Status.PREPARED);

        order.setTimeOfDelivery(deliveryTime);
        order1.setTimeOfDelivery(deliveryTime1);
        OrderTimerManager timerManager = new OrderTimerManager();
        System.out.println("Order status: " + order.getState());
        System.out.println("Order status: " + order.getState());
        timerManager.showRemainingTime(order);
        timerManager.showRemainingTime(order1);



        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final order status: " + order.getState());


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
//
//        SwingUtilities.invokeLater(MainFrame :: new);
//        LoginAndRegistrationFrame loginAndRegistrationFrame = new LoginAndRegistrationFrame();
//        loginAndRegistrationFrame.setVisible(true);

    }
}