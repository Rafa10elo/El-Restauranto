package Model;

import javax.swing.*;

public class Meal {
    String mealName;
    String ingredients;
    float price;
    String imgSrc;
    ImageIcon img;

    public Meal(String mealName, String ingredients, float price, String imgSrc) {
        this.mealName = mealName;
        this.ingredients = ingredients;
        this.price = price;
        this.imgSrc = imgSrc;
        this.img = new ImageIcon(imgSrc);
    }

    public static Meal fromFileFormat(String str) {
        try {
            String[] mealStrings = str.split("&@");
            String mealName = mealStrings[0];
            String ingredients = mealStrings[1];
            float price = Float.parseFloat(mealStrings[2]);
            String imgSrc = mealStrings[3];
            return new Meal(mealName, ingredients, price, imgSrc);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public String toFileFormat() {
        return String.join("&@", mealName, ingredients, String.valueOf(price), imgSrc);
    }

    public String getMealName() {
        return mealName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public float getPrice() {
        return price;
    }

    public String getImgSrc() {
        return imgSrc;
    }
}
