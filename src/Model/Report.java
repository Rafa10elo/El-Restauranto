package Model;

import java.util.Map;

public class Report {
    int numberOfOrders;
    double totalMoney;
    Map<Integer,Meal> orderedMeals;
    Map<Integer,User> orderingUsers;


    public Report(int numberOfOrders, Map<Integer, Meal> orderedMeals, double totalMoney, Map<Integer, User> orderingUsers) {
        this.numberOfOrders = numberOfOrders;
        this.orderedMeals = orderedMeals;
        this.totalMoney = totalMoney;
        this.orderingUsers = orderingUsers;
    }
    void increaseNumberOfOrders(){
        numberOfOrders ++ ;
    }

    void increaseTotalMoney (Order order){
        totalMoney += order.totalPrice ;
    }

//    Meal mostOrderedMeal() {
//        return orderedMeals.get
//    }

    //mostorderingusers


}