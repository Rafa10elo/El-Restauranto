package Model;

import java.io.*;
import java.util.ArrayList;

public class Meals {
    ArrayList<Meal> meals = new ArrayList<>();

    public ArrayList<Meal> getMeals(){
        return meals;
    }
    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public boolean deleteMeal(String mealName) {
        return meals.removeIf(meal -> meal.mealName.equals(mealName));
    }
    public boolean deleteMeal(Meal meal) {
        return meals.removeIf(meal :: equals);
    }


    public boolean modifyMeal(String mealName, Meal updatedMeal) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).mealName.equals(mealName)) {
                meals.set(i, updatedMeal);
                return true;
            }
        }
        return false;
    }

    public boolean modifyMeal(Meal oldMeal, Meal updatedMeal) {
        for (int i = 0; i < meals.size(); i++){
            if (meals.get(i) == oldMeal) {
                meals.set(i, updatedMeal);
                return true;
            }
        }
        return false;
    }

    public void writerThread(){

        new Thread(()->{
            saveToFile();
        }).start();

    }
    public void readerThread(){

        new Thread(()->{
            loadFromFile();
        }).start();

    }

    public void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("MealsFile.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Meal meal = Meal.fromFileFormat(line);
                if (meal != null) {
                    meals.add(meal);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("MealsFile.txt"))) {
            for (Meal meal : meals) {
                bw.write(meal.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
