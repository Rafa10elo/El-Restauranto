import javax.swing.*;

public class Meal {
    String mealName;
    String ingredients;
    float price;
    String imgSrc;
    ImageIcon img ;

    public Meal(String mealName, String ingredients, float price, String imgSrc) {
        this.mealName = mealName;
        this.ingredients = ingredients;
        this.price = price;
        this.imgSrc=imgSrc;
        this.img = new ImageIcon(imgSrc);
    }

    Meal fromFileFormat(String str){
        String[] mealStrings=str.split("&@");
        mealName = mealStrings[0];
        ingredients = mealStrings[1];
        price = Float.parseFloat(mealStrings[2]);
        imgSrc = mealStrings[3];
        return new Meal(mealName,ingredients,price,imgSrc);
    }
    String toFileFormat(Meal meal){
        String mealString = "";
        mealString += meal.mealName + "&@";
        mealString += meal.ingredients + "&@";
        mealString += meal.price + "&@";
        mealString += meal.imgSrc ;

        return mealString;


    }

}
