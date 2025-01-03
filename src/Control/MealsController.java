package Control;

import Model.Meal;
import Model.Meals;
import Model.Payment;
import Model.User;
import View.MealPanel;
import View.MealsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class MealsController {
    Meals meals;
    MealsPanel mealsPanel;
    User user ;
    public MealsController(Meals meals, MealsPanel mealsPanel, User user){
        this.meals =meals;
        this.mealsPanel = mealsPanel;
        this.user = user;

        if( user.getUserType() == 0){
            mealsPanel.fillMainMenu(meals.getMeals());
            // add click action listener to meal panels in main menu : click = add to order
            for (MealPanel m : mealsPanel.getAllMeals()) {
                m.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        mealsPanel.addMealPanelToOrder(m.getMeal());
                    }
                });
            }
        }
        else{
            repaintMainMenu();
            // ADD MEAL BUTTON
            ActionListener addMealListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (mealsPanel.getSidePanel().mealInfoValid()){
                        Meal newMeal = mealsPanel.getNewMealInfo() ;
                        // add to model + write
                        meals.addMeal(newMeal);
                        meals.writerThread();
                        // edit view + add it to hashmap
                        mealsPanel.getSidePanel().newMealReset();
                        repaintMainMenu();
                        JOptionPane.showMessageDialog(mealsPanel, "Meal added successfully! :)", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                }};
            mealsPanel.getSidePanel().getAddMealButton().addActionListener(addMealListener);

            // EDIT MEAL
            ActionListener editMealListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (mealsPanel.editMealInfoValid()){
                        Meal editedMeal = mealsPanel.getEditedMealInfo() ;
                        // edit view
                        mealsPanel.getMealPanel(mealsPanel.getCurrentMeal()).setMealInfo(editedMeal);
//                        mealsPanel.editMeals(mealsPanel.getCurrentMeal(), editedMeal) ;
                        // edit model
                        meals.modifyMeal(mealsPanel.getCurrentMeal(), editedMeal);
                        meals.writerThread();

                        mealsPanel.setCurrentMeal(null);

                        mealsPanel.getEditMealDialog().removeAll();
                        mealsPanel.getEditMealDialog().dispose();

                        JOptionPane.showMessageDialog(mealsPanel, "Meal edited successfully!", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            };
            mealsPanel.getEditMealButton().addActionListener(editMealListener);

            // DELETE MEAL
            ActionListener deleteMealListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // edit model
                    meals.deleteMeal(mealsPanel.getCurrentMeal()) ;
                    meals.writerThread();
                    // edit view
                    mealsPanel.getAllMeals().remove(mealsPanel.getCurrentMeal()) ;
                    repaintMainMenu();
                    mealsPanel.setCurrentMeal(null);

                    mealsPanel.getEditMealDialog().removeAll();
                    mealsPanel.getEditMealDialog().dispose();

                    JOptionPane.showMessageDialog(mealsPanel, "Meal deleted successfully!", "", JOptionPane.INFORMATION_MESSAGE);
                }
            };
            mealsPanel.getDeleteMeal().addActionListener(deleteMealListener);
        }

    }
    void repaintMainMenu (){
        mealsPanel.fillMainMenu(meals.getMeals());
        for (MealPanel m: mealsPanel.getAllMeals()) {
            m.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mealsPanel.setCurrentMeal(m.getMeal());
                    mealsPanel.createEditMealDialog(m.getMeal());
                }
            });
        }
    }
}
