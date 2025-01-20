package Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            System.out.println(2);
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

    public boolean saveImgToProject() {
        boolean saveImg = true;
        System.out.println(imgSrc);
        System.out.println(imgSrc.contains("src/pics"));
        if(!imgSrc.contains("src/pics")){
            try{
                BufferedImage localImg = ImageIO.read(new File(imgSrc));
                File file = new File("src/pics", new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date()) + ".jpg");
                saveImg = ImageIO.write(localImg, "jpg", file) ;
                if(saveImg){
                    System.out.println("img saved successfully :]");
                    imgSrc = file.getPath();
                }else{
                    System.out.println("img not saved for some reason");
                    imgSrc = "src/pics/default.jpg" ;
                }
                return saveImg;
            }catch (IOException e){
                System.out.println("IO exception in image saving");
            }
        }
        String s = imgSrc.substring(imgSrc.indexOf("src"));
        imgSrc = s ;
        return saveImg;
    }

}
