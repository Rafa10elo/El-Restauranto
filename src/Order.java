import java.util.ArrayList;

public class Order {
    ArrayList<Meal> meals;
    float totalPrice = 0;
    float tip;
    Status state;

    enum Status {PREPARED, DELIVERED, CANCELED}

    public Order(ArrayList<Meal> meals, float tip, Status state) {
        this.meals = meals;
        this.tip = tip;
        this.state = state;
        for (Meal meal : meals) {
            totalPrice += meal.price;
        }
        totalPrice += tip;
    }

    public String toFileFormat() {
        String orderString = "";

        for (Meal meal : meals) {
            orderString += meal.toFileFormat() + "##";
        }

        orderString += "@@" + tip + "@@" + state.name();

        return orderString;
    }

    public static Order fromFileFormat(String str) {
        try {
            String[] orderParts = str.split("@@");
            String[] mealStrings = orderParts[0].split("##");
            ArrayList<Meal> meals = new ArrayList<>();

            for (String mealStr : mealStrings) {
                Meal meal = Meal.fromFileFormat(mealStr);
                if (meal != null) {
                    meals.add(meal);
                }
            }

            float tip = Float.parseFloat(orderParts[1]);
            Status state = Status.valueOf(orderParts[2]);

            return new Order(meals, tip, state);

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
