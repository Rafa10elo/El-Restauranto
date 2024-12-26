package Control;

import Model.Meals;
import View.MealsPanel;

public class MealsController {
    Meals meals;
    MealsPanel mealsPanel;
    MealsController(Meals meals ,MealsPanel mealsPanel){
        this.meals =meals;
        this.mealsPanel = mealsPanel;

    }
}
