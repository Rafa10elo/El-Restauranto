import java.util.ArrayList;

public class Order {
    ArrayList<Meal> meals;
    float totalPrice=0;
    float tip;
    Status state;
    enum Status {PREPARED,DELIVERED,CANCELED};

    public Order(ArrayList<Meal> meals, float tip) {
        this.meals = meals;
        this.tip = tip;
        for(Meal meal : meals){
            totalPrice+= meal.price;
        }
        totalPrice+=tip;
    }
    Order fromFileFormat(){



    }
    //





}
