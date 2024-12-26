package Model;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private HashMap<Meal, Integer> meals;
    // -------------------------------------------------------------change float to double
    private float totalPrice = 0;
    private float tip;
    private Status state;
    private LocalDateTime timeOfDelivery;
    private String paymentId;

    public enum Status {PREPARED, DELIVERED, CANCELED}

    public Order(HashMap<Meal, Integer> meals, float tip, Status state) {
        this.meals = meals;
        this.tip = tip;
        this.state = state;
        calculateTotalPrice();
        this.timeOfDelivery = null;
        this.paymentId = null;
    }

    public Order(HashMap<Meal, Integer> meals, float totalPrice, float tip, Status state,
                 LocalDateTime timeOfDelivery, String paymentId) {
        this.meals = meals;
        this.totalPrice = totalPrice;
        this.tip = tip;
        this.state = state;
        this.timeOfDelivery = timeOfDelivery;
        this.paymentId = paymentId;
    }

    public HashMap<Meal, Integer> getMeals() {
        return meals;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public float getTip() {
        return tip;
    }

    //------------------------------------------------------------------------------------------
    private void calculateTotalPrice() {
        totalPrice = tip;
        for (Map.Entry<Meal, Integer> entry : meals.entrySet()) {
            totalPrice += entry.getKey().price * entry.getValue();
        }
    }

    public synchronized void setState(Status state) {
        this.state = state;
    }

    public LocalDateTime getTimeOfDelivery() {
        return this.timeOfDelivery;
    }

    public void setTimeOfDelivery(LocalDateTime timeOfDelivery) {
        this.timeOfDelivery = timeOfDelivery;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Status getState() {
        return this.state;
    }

    //---------------------------------- tmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
    public void addMeal(Meal meal) {
        meals.put(meal, meals.getOrDefault(meal, 0) + 1);
        calculateTotalPrice();
    }

    //---------------------------------------tmmmmmmmmmmmmmmmmmmmmm
    public boolean removeMeal(Meal meal) {
        if (meals.containsKey(meal)) {
            meals.remove(meal);
            calculateTotalPrice();
            return true;
        }
        return false;
    }

    //------------------------------------
    public boolean modifyMealCnt(Meal meal, int cnt) {
        if (meals.containsKey(meal)) {
            if (cnt > 0) {
                meals.put(meal, cnt);
            } else {
                meals.remove(meal);
            }
            calculateTotalPrice();
            return true;
        }
        return false;
    }

    public String toFileFormat() {
        String orderString = "";

        for (Map.Entry<Meal, Integer> entry : meals.entrySet()) {
            orderString += entry.getKey().toFileFormat() + "##" + entry.getValue() + "||";
        }

        orderString += "@@" + this.totalPrice;
        orderString += "@@" + this.tip;
        orderString += "@@" + this.state.name();

        if (this.timeOfDelivery != null) {
            orderString += "@@" + this.timeOfDelivery.toString();
        } else {
            orderString += "@@null";
        }

        if (this.paymentId != null) {
            orderString += "@@" + this.paymentId;
        } else {
            orderString += "@@null";
        }

        return orderString;
    }

    public static Order fromFileFormat(String str) {
        try {
            String[] orderParts = str.split("@@");
            String[] mealStrings = orderParts[0].split("\\|\\|");
            HashMap<Meal, Integer> meals = new HashMap<>();

            for (String mealStr : mealStrings) {
                if (!mealStr.isEmpty()) {
                    String[] mealData = mealStr.split("##");
                    Meal meal = Meal.fromFileFormat(mealData[0]);
                    int count = Integer.parseInt(mealData[1]);
                    meals.put(meal, count);
                }
            }

            float totalPrice = Float.parseFloat(orderParts[1]);
            float tip = Float.parseFloat(orderParts[2]);
            Status state = Status.valueOf(orderParts[3]);
            LocalDateTime timeOfDelivery = null;

            if (!orderParts[4].equals("null")) {
                timeOfDelivery = LocalDateTime.parse(orderParts[4]);
            }

            String paymentId = null;
            if (!orderParts[5].equals("null")) {
                paymentId = orderParts[5];
            }
            

            return new Order(meals, totalPrice, tip, state, timeOfDelivery, paymentId);

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
