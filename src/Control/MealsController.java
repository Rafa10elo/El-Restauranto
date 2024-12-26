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

        for (int i = 0; i < mealsPanel.getAllMeals().size() ; i ++) {

        }

        mealsPanel.getSidePanel().getEditMealButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < meals.getMeals().size() ; i ++ ){
                    if ( meals.getMeals().get(i) == mealsPanel.getCurrentMeal()) {
                        // edit meals in model
                        Meal editedMeal = mealsPanel.getEditedMealInfo();
                        meals.modifyMeal(i, editedMeal) ;
                        // edit meal panel
                        mealsPanel.getMealPanel(mealsPanel.getCurrentMeal()).setMealInfo(editedMeal);
                        //edit meal in view
                        mealsPanel.editMeals(mealsPanel.getCurrentMeal(), editedMeal, mealsPanel.getMealPanel(mealsPanel.getCurrentMeal()));
                        //current meal = null
                        mealsPanel.resetCurrentMeal();
                    }
                }
            }
        });
    }
}
