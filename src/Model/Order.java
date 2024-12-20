package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private ArrayList<Meal> meals;
    private float totalPrice = 0;
    private float tip;
    private Status state;
    private LocalDateTime timeOfDelivery;
    private String paymentId;

    public enum Status {PREPARED, DELIVERED, CANCELED}

    public Order(ArrayList<Meal> meals, float tip, Status state) {
        this.meals = meals;
        this.tip = tip;
        this.state = state;
        for (Meal meal : meals) {
            this.totalPrice += meal.price;
        }
        this.totalPrice += tip;
        this.timeOfDelivery = null;
        this.paymentId = null;
    }

    public Order(ArrayList<Meal> meals, float totalPrice, float tip, Status state,
                 LocalDateTime timeOfDelivery, String paymentId) {
        this.meals = meals;
        this.totalPrice = totalPrice;
        this.tip = tip;
        this.state = state;
        this.timeOfDelivery = timeOfDelivery;
        this.paymentId = paymentId;
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
    public Status getState(){
        return this.state;
    }

    public String toFileFormat() {
        String orderString = "";

        for (Meal meal : this.meals) {
            orderString += meal.toFileFormat() + "##";
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
            String[] mealStrings = orderParts[0].split("##");
            ArrayList<Meal> meals = new ArrayList<Meal>();

            for (String mealStr : mealStrings) {
                Meal meal = Meal.fromFileFormat(mealStr);
                if (meal != null) {
                    meals.add(meal);
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
