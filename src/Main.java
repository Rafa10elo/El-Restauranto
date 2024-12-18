import Model.Meal;
import Model.Report;
import View.MainFrame;
import java.io.File;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        Report report = new Report(2,333);
        Meal meal = new Meal("piz123","21321",33,"21321");
        Meal meal1 = new Meal("pizz","21321",33,"21321");
        Meal meal3 = new Meal("forma","21321",33,"21321");
        report.incrementMealCount(meal,201);
        report.incrementMealCount(meal3,1221);
        report.incrementMealCount(meal1,1000);
        System.out.println(report.getMostSoldMeal().getMealName());
        report.incrementMealCount(meal,10000);
        System.out.println(report.getMostSoldMeal().getMealName());


    }
}