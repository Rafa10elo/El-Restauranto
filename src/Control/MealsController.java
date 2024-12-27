package Control;

import Model.Meal;
import Model.Meals;
import View.MealsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MealsController {
    Meals meals;
    MealsPanel mealsPanel;
    public MealsController(Meals meals, MealsPanel mealsPanel){
        this.meals =meals;
        this.mealsPanel = mealsPanel;

    }
}
