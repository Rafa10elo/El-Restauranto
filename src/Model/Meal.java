package Model;

import javax.swing.*;
import java.util.Objects;

public class Meal {
    String mealName;
    String ingredients;
    float price;
    String imgSrc;
    ImageIcon img;

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }


    public String getIngredients() {
        return ingredients;
    }

    public ImageIcon getImg() {
        return img;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public float getPrice() {
        return price;
    }

    public String getMealName() {
        return mealName;
    }

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
        return String.join("&@", mealName, ingredients, String.valueOf(price), imgSrc)+"&@";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Float.compare(meal.price, price) == 0 &&
                Objects.equals(mealName, meal.mealName) &&
                Objects.equals(ingredients, meal.ingredients) &&
                Objects.equals(imgSrc, meal.imgSrc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealName, ingredients, price, imgSrc);
    }

}
