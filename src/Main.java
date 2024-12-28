import Control.LoginAndRegisterManager;
import Control.MainController;

import Control.OrderTimerManager;
import Model.Meal;
import Model.Report;
import Model.Order;
import View.AllOrdersPanel;
import View.LoginAndRegistrationFrame;
import View.MainFrame;
import View.ReportPanel;
import Model.*;
import View.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
//import java.util.Timer;


public class Main {

//ManagmentManagmenterForManagingTheManagers
    public static void main(String[] args) {
////
//        HashMap<Meal, Integer> meals = new HashMap<>();
//        meals.put(new Meal("Pizza", "Salami", 34500, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("beep", "Salami", 6600, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("boop", "Salami", 4300, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("bap", "Salami", 34540, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("bop", "Salami", 35400, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("Pasta", "Sauce", 24500, "src/View/Images/profilePicture.png"), 20);
//        meals.put(new Meal("Pizza", "Salami", 34500, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("beep", "Salami", 6600, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("boop", "Salami", 4300, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("bap", "Salami", 34540, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("bop", "Salami", 35400, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("Pasta", "Sauce", 24500, "src/View/Images/profilePicture.png"), 20);
//      LocalDateTime time = LocalDateTime.now();
//        Order order1 = new Order(meals,40, 23,Order.Status.DELIVERED,time.plusSeconds(20),"wqe");
//        Order order2 =new Order(meals,30, 23,Order.Status.DELIVERED,time.plusSeconds(15),"wqe");
//        Order order3 =new Order(meals,20, 23,Order.Status.DELIVERED,time.plusSeconds(10),"wqe");
//        Order order4 = new Order(meals,10, 23,Order.Status.DELIVERED,time.plusSeconds(5),"wqe");
//        Users users = new Users();
//        User user = new User("roro","roro@gmail.com","12345678",0);
//        users.addUser(user);
//        Orders orders=Orders.getOrdersSing();
//        orders.addOrderForUser(user,order1);
//        orders.addOrderForUser(user,order2);
//        users.writerThread();
//////
////        OrderTimerManager orderTimerManager = new OrderTimerManager();
////        orderTimerManager.showRemainingTime(order1);
////        orderTimerManager.showRemainingTime(order2);

        MainController mainController =new MainController();


//        HashMap<Meal, Integer> meals = new HashMap<>();
//        meals.put(new Meal("Pizza", "Salami", 34500, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("beep", "Salami", 6600, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("boop", "Salami", 4300, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("bap", "Salami", 34540, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("bop", "Salami", 35400, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("Pasta", "Sauce", 24500, "src/View/Images/profilePicture.png"), 20);
//        meals.put(new Meal("Pizza", "Salami", 34500, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("beep", "Salami", 6600, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("boop", "Salami", 4300, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("bap", "Salami", 34540, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("bop", "Salami", 35400, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("Pasta", "Sauce", 24500, "src/View/Images/profilePicture.png"), 20);
//      LocalDateTime time = LocalDateTime.now();
//        Order order1 = new Order(meals,40, 23,Order.Status.DELIVERED,time.plusSeconds(20),"wqe");
//        Order order2 =new Order(meals,30, 23,Order.Status.DELIVERED,time.plusSeconds(15),"wqe");
//        Order order3 =new Order(meals,20, 23,Order.Status.DELIVERED,time.plusSeconds(10),"wqe");
//        Order order4 = new Order(meals,10, 23,Order.Status.DELIVERED,time.plusSeconds(5),"wqe");
//
//        OrderTimerManager orderTimerManager = new OrderTimerManager();
//        orderTimerManager.showRemainingTime(order1);
//        orderTimerManager.showRemainingTime(order2);
//
//        orderTimerManager.showRemainingTime(order3);
//
//        orderTimerManager.showRemainingTime(order4);
//
//

//        Users users= new Users();
//users.loadFromFile();
//
//        users.getUsers().get(0).setUserType(2);;
//users.getUsers().get(0).getOrders().get(0).getMeals().get(0);
//users.saveToFile();
//users.loadFromFile();
//
//        try {
//            Thread.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        users.getUsers().get(0).getOrders().get(0).getMeals().get(0);



//        Users users= new Users();
//        users.loadFromFile();
//
//        users.getUsers().get(0).setUserType(2);;
//        users.getUsers().get(0).getOrders().get(0).getMeals().get(0);
//        users.saveToFile();
//        users.loadFromFile();
//
//        try {
//            Thread.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        users.getUsers().get(0).getOrders().get(0).getMeals().get(0);
//
//        users.addUser(new User("wqe","wqe","12345678",0));
//        HashMap<Meal,Integer> hashMap= new HashMap<>();
//        hashMap.put(new Meal("r","r",3,"3"),1);
//        Order order = new Order(hashMap,213, Order.Status.DELIVERED);
////        users.getUsers().get(0).getOrders().addOrderForUser(users.getUsers().get(0),order);
//        Report report = new Report(1,2000);
//        users.saveToFile();
//        report.saveToFile();

//        HashMap<Meal, Integer> map = new HashMap<>();
//
//        Meal meal=  new Meal("213","21321",213,"213");
//        Meal meal1=  new Meal("213","21321",213,"213");
//
//        map.put(meal,map.getOrDefault(meal,0)+1);
//        map.put(meal1,map.getOrDefault(meal1,0)+1);
//
//
//        System.out.println(map.get(new Meal("213","21321",213,"213")));




//        // sarah test
//        MainFrame mainFrame = new MainFrame(0);
//        mainFrame.add(new LoginPanel(new JPanel(),new CardLayout()));

//        ReportPanel reportPanel = new ReportPanel(new Report(54,5562),50,19);
//        mainFrame.add(reportPanel, BorderLayout.CENTER);

//        LoginAndRegistrationFrame loginAndRegistrationFrame = new LoginAndRegistrationFrame();
//        loginAndRegistrationFrame.setVisible(true);
        //mainFrame.setVisible(true);


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
//        ArrayList<Meal> mealsAya = new ArrayList<>();
//        Meals mealsC = null;
//        for (int i = 1 ; i < 16 ; i ++) {
//            String name = "meal " + i ;
//            String ing = "ing " + i ;
//            float price = i ;
//            mealsAya.add(new Meal(name, ing, price, "src/pics/" + i + ".jpg"));
//            mealsC.addMeal(new Meal(name, ing, price, "src/pics/" + i + ".jpg"));
//        }
//

//        User user = new User("hamoudeh","mumu.2005@gmail.com","anaHamoudeh1234",1);

//        User user = new User("hamoudeh","mumu.2005@gmail.com","000",0);

//        ProfilePanel profilePanel= new ProfilePanel(user);
//
//        ReportPanel reportPanel = new ReportPanel(new Model.Report(54,5562),50,19);
//
//      HashMap<Meal, Integer> meals = new HashMap<>();
//        meals.put(new Meal("Pizza", "Salami", 34500, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("beep", "Salami", 6600, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("boop", "Salami", 4300, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("bap", "Salami", 34540, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("bop", "Salami", 35400, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("Pasta", "Sauce", 24500, "src/View/Images/profilePicture.png"), 20);
//        meals.put(new Meal("Pizza", "Salami", 34500, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("beep", "Salami", 6600, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("boop", "Salami", 4300, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("bap", "Salami", 34540, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("bop", "Salami", 35400, "src/View/Images/profilePicture.png"), 10);
//        meals.put(new Meal("Pasta", "Sauce", 24500, "src/View/Images/profilePicture.png"), 20);
//        LocalDateTime time = LocalDateTime.now();
//       Order order1 = new Order(meals,40, 23,Order.Status.DELIVERED,time.plusSeconds(20),"wqe");
//       Order order2 =new Order(meals,30, 23,Order.Status.DELIVERED,time.plusSeconds(15),"wqe");
//       Order order3 =new Order(meals,20, 23,Order.Status.DELIVERED,time.plusHours(1),"wqe");
//        Order order4 = new Order(meals,10, Order.Status.DELIVERED);
//        Orders orders = Orders.getOrdersSing();
//        User user =new User("name","r","12345678",0);
//        orders.addOrderForUser(user,order1);
//        orders.addOrderForUser(user,order2);
//        orders.addOrderForUser(user,order3);
//
//
//
//        AllOrdersPanel allOrdersPanel = new AllOrdersPanel(user,orders);
//        JFrame frame = new JFrame();
//        frame.add(allOrdersPanel);
//        frame.setVisible(true);
//
//        // user type : 0 -> customer (order panel)      1,2 -> worker, boss (add meal panel)
//        MainFrame mainFrame = new MainFrame(user.getUserType(), profilePanel, reportPanel, allOrdersPanel);

//        mainFrame.mealsPanel.fillMainMenu(mealsAya);
//        //---------------------------------------------------------------------------just for fun

//        mainFrame.mealsPanel.getSidePanel().createPaymentDialog();
//        mainFrame.mealsPanel.getSidePanel().cancelPay.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                mainFrame.mealsPanel.getSidePanel().paymentDialog.dispose();
//            }
//        });
//
//        LoginAndRegistrationFrame loginAndRegistrationFrame= new LoginAndRegistrationFrame();

}}