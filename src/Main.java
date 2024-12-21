import Model.Meal;
import View.MainFrame;
import java.io.File;
import java.util.ArrayList;
//import java.util.Timer;

public class Main {
    File file = new File("");

    public static void main(String[] args) {

        // just a simple test
        ArrayList<Meal> meals = new ArrayList<>();
        for (int i = 1 ; i < 16 ; i ++) {
            String name = "meal " + i ;
            String ing = "ing " + i ;
            float price = i ;
            meals.add(new Meal(name, ing, price, "src/pics/" + i + ".jpg"));
        }

        // user type : 0 -> customer (order panel)      1,2 -> worker, boss (add meal panel)
        MainFrame mainFrame = new MainFrame(1);
        mainFrame.mealsPanel.fillMainMenu(meals);

    }
}