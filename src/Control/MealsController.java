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
            // add click action listener to meal panels in main menu : click = add to order
            for (Map.Entry<Meal, MealPanel> entry : mealsPanel.getAllMeals().entrySet()) {
                entry.getValue().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        mealsPanel.addMealPanelToOrder(entry.getKey());
                    }
                });
            }
        }
        else{
            // add click action listener to meal panels in main menu : click = EDIT
            for (Map.Entry<Meal, MealPanel> entry : mealsPanel.getAllMeals().entrySet()) {
                entry.getValue().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mealsPanel.createEditMealDialog(entry.getKey());
                }
            });
            }
            // ADD MEAL BUTTON
            ActionListener addMealActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (mealsPanel.getSidePanel().mealInfoValid()){
                        Meal newMeal = mealsPanel.getNewMealInfo() ;
                        // add to model + write
                        meals.addMeal(newMeal);
                        meals.writerThread();
                        // edit view + add it to hashmap
                        mealsPanel.getSidePanel().newMealReset();
                        mealsPanel.fillMainMenu(meals.getMeals());
                        JOptionPane.showMessageDialog(mealsPanel, "Meal added successfully! :)", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                }};
            mealsPanel.getSidePanel().getAddMealButton().addActionListener(addMealActionListener);

            // EDIT MEAL
            ActionListener editMealActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (mealsPanel.editMealInfoValid()){
                        Meal editedMeal = mealsPanel.getEditedMealInfo() ;
                        // edit view
                        mealsPanel.getMealPanel(mealsPanel.getCurrentMeal()).setMealInfo(editedMeal);
                        mealsPanel.editMeals(mealsPanel.getCurrentMeal(), editedMeal) ;
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
            mealsPanel.getEditMealButton().addActionListener(editMealActionListener);

            // DELETE MEAL
            ActionListener deleteMeal = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // edit model
                    meals.deleteMeal(mealsPanel.getCurrentMeal()) ;
                    meals.writerThread();
                    // edit view
                    mealsPanel.getAllMeals().remove(mealsPanel.getCurrentMeal()) ;
                    mealsPanel.fillMainMenu(meals.getMeals());

                    mealsPanel.setCurrentMeal(null);

                    mealsPanel.getEditMealDialog().removeAll();
                    mealsPanel.getEditMealDialog().dispose();

                    JOptionPane.showMessageDialog(mealsPanel, "Meal deleted successfully!", "", JOptionPane.INFORMATION_MESSAGE);
                }
            };
            mealsPanel.getDeleteMeal().addActionListener(deleteMeal);
        }

    }
}
