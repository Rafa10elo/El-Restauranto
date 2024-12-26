package View;

import Model.Meal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MealsPanel extends JPanel {
    JPanel mainMenu;
    SidePanel sidePanel;
    HashMap<Meal, MealPanel> allMeals = new HashMap<>() ;
    int userType;
    Meal currentEditMeal = null ;

    public MealsPanel (int userType) {
        this.userType = userType ;
        setLayout(new BorderLayout());
        sidePanel = new SidePanel(userType) ;
        add(sidePanel, BorderLayout.EAST) ;

        mainMenu = new JPanel() ;
        mainMenu.setBackground(MainFrame.darkGray);
        mainMenu.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane scrollMainMenu = new JScrollPane(mainMenu);
        scrollMainMenu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollMainMenu, BorderLayout.CENTER) ;
    }

    public void fillMainMenu(ArrayList<Meal> meals) {
        mainMenu.removeAll();
        int rowsNum ;
        if (meals.size() % 3 == 0){
            rowsNum = meals.size() / 3 ;
        }else{
            rowsNum = meals.size() / 3 + 1;
        }
        int gap = 10 ;
        mainMenu.setLayout(new GridLayout(0, 3, gap, gap));

        for (Meal m : meals){
            MealPanel mealPanel = createMealPanelTOMenu(m) ;
            mainMenu.add(mealPanel);
            allMeals.put(m, mealPanel) ;
        }

        int panelWidth = 900; // 900 - gap * 2 --> cell width
        int panelHeight = 350 * rowsNum + (rowsNum - 1) * gap ; // cell Height * rowsNum + (rowsNum - 1) * gap
        mainMenu.setPreferredSize(new Dimension(panelWidth, panelHeight));
        mainMenu.revalidate();
        mainMenu.repaint();

    }

    MealPanel createMealPanelTOMenu(Meal meal) {
        MealPanel mealPanel = new MealPanel(meal) ;

        // adding mouse listener to make the panel act like a button 🐰
//        mealPanel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (userType == 0) {
//                    addMealToOrder(mealPanel.meal);
//                    System.out.println("adding meal to the order");
//                }else{
//                    //-----------------------------------------------------------------------------------------------------edit, delete meal dialog and Meals arraylist
//                    sidePanel.createEditMealDialog(mealPanel.meal);
//                    currentEditMeal = mealPanel.meal;
//                    System.out.println("editing the meal dialog");
//                }
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                mealPanel.setBorder(new LineBorder(new Color(91, 94, 102), 1));
////                infoPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(91, 94, 102)));
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                mealPanel.setBorder(new LineBorder(MainFrame.extraLightGray, 1));
////                infoPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, MainFrame.extraLightGray));
//            }
//        });
        return mealPanel;
    }

    void addMealPanelToOrder(Meal meal) {
        if (sidePanel.meals.containsKey(meal)) {
            // edit cnt in meals hashmap
            sidePanel.meals.put(meal, sidePanel.meals.getOrDefault(meal, 0) + 1);
            //edit label in hashmap
            sidePanel.mealsCntLabels.get(meal).setText(":   " + String.valueOf(sidePanel.meals.get(meal)));
            //edit total price
            sidePanel.totalPrice.setText(String.valueOf(Float.parseFloat(sidePanel.totalPrice.getText()) + meal.getPrice()));
            sidePanel.mealsCntLabels.get(meal).revalidate();
            sidePanel.mealsCntLabels.get(meal).repaint();
            return;
        }
            //add to meals hashmap
        sidePanel.meals.put(meal, sidePanel.meals.getOrDefault(meal, 0) + 1);

        Component smallGap = Box.createRigidArea(new Dimension(0, 10)) ;

        JPanel mealPanel = new JPanel() ;
        mealPanel.setSize(new Dimension(300, 500));
        mealPanel.setLayout(new BorderLayout());
        mealPanel.setBackground(MainFrame.darkGray);
        mealPanel.setBorder(new LineBorder(MainFrame.extraLightGray, 1));

        // meal image
        JPanel mealPhoto = new JPanel() ;
        mealPhoto.setBackground(MainFrame.darkGray);
        Image img = Toolkit.getDefaultToolkit().getImage(meal.getImgSrc()).getScaledInstance(mealPanel.getWidth() - 20, 200, Image.SCALE_SMOOTH) ;
        JLabel imgLabel = new JLabel(new ImageIcon(img)) ;
        mealPhoto.add(imgLabel) ;

        // meal info panel
        JPanel infoPanel = new JPanel(new GridBagLayout()) ;
        infoPanel.setBackground(MainFrame.darkGray);
        infoPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, MainFrame.extraLightGray));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 10, 0) ;

        //the info :
        // meal name :
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JLabel name = new JLabel("Name");
        name.setForeground(MainFrame.orange);
        name.setFont(MainFrame.fontBold.deriveFont(25f));
        infoPanel.add(name, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 2.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JLabel mealName = new JLabel(":   " + meal.getMealName());
        mealName.setForeground(MainFrame.orange);
        mealName.setFont(MainFrame.fontBold.deriveFont(20f));
        infoPanel.add(mealName, gbc);


        // meal price :
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JLabel price = new JLabel("Price");
        price.setForeground(MainFrame.orange);
        price.setFont(MainFrame.fontBold.deriveFont(25f));
        infoPanel.add(price, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 2.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JLabel mealPrice = new JLabel(":   " + String.valueOf(meal.getPrice()) + " $");
        mealPrice.setForeground(MainFrame.orange);
        mealPrice.setFont(MainFrame.fontBold.deriveFont(20f));
        infoPanel.add(mealPrice, gbc);


        //meal ingredients :
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH ;
        JLabel ingredients = new JLabel("Ingredients ");
        ingredients.setForeground(MainFrame.orange);
        ingredients.setFont(MainFrame.fontBold.deriveFont(25f));
        infoPanel.add(ingredients, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.weightx = 2.0;
        gbc.weighty = 2.0;
        gbc.fill = GridBagConstraints.BOTH;
        JTextArea mealIngredients = new JTextArea(":   " + meal.getIngredients());
        mealIngredients.setBackground(MainFrame.darkGray);
        mealIngredients.setLineWrap(true);
        mealIngredients.setWrapStyleWord(true);
        mealIngredients.setEditable(false);
        mealIngredients.setForeground(MainFrame.orange);
        mealIngredients.setFont(MainFrame.fontBold.deriveFont(20f));
        mealIngredients.setBorder(BorderFactory.createEmptyBorder());
        JScrollPane scrollPane = new JScrollPane(mealIngredients);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        infoPanel.add(scrollPane, gbc);

        //meal amount
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER ;
        JLabel amount = new JLabel("Amount ");
        amount.setForeground(MainFrame.orange);
        amount.setFont(MainFrame.fontBold.deriveFont(25f));
        infoPanel.add(amount, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 2.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST ;
        JLabel mealAmount = new JLabel(":   " + String.valueOf(sidePanel.meals.get(meal)) );
        System.out.println(sidePanel.meals.get(meal));
        mealAmount.setForeground(MainFrame.orange);
        mealAmount.setFont(MainFrame.fontBold.deriveFont(20f));
        // add label to hashmap
        sidePanel.mealsCntLabels.put(meal, mealAmount);
        infoPanel.add(sidePanel.mealsCntLabels.get(meal), gbc);

        // adding the photo and info
        mealPanel.add(mealPhoto, BorderLayout.NORTH) ;
        mealPanel.add(infoPanel, BorderLayout.CENTER);

        // adding mouse listener to make the panel act like a button 🐰
        mealPanel.addMouseListener(new MouseAdapter() {
            JDialog deleteDialog;
            @Override
            public void mouseClicked(MouseEvent e) {
                mealPanel.setBorder(new LineBorder(Color.RED, 1));
                if (deleteDialog == null) {
                    deleteDialog = new JDialog();

                    deleteDialog.setSize(new Dimension(450, 200));
                    deleteDialog.setLocationRelativeTo(null);
                    deleteDialog.setVisible(true);
                    // -------------------------------------------------------------------------------Modal ?????!!!!!!
                    deleteDialog.setModal(false);
                    deleteDialog.getContentPane().setBackground(MainFrame.darkGray);
                    deleteDialog.setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();

                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.gridwidth = 2;
                    gbc.gridheight = 1;
                    gbc.weightx = 2.0;
                    gbc.weighty = 1.0;
                    gbc.fill = GridBagConstraints.NONE;
                    //---------------------------------------------------------------if the meal cnt > 1 , we can make him choose if he wants to only delete one or the whole thing
                    JLabel confirmation = new JLabel("Do you want to remove this meal from your order ?");
                    confirmation.setFont(MainFrame.fontBold.deriveFont(25f));
                    confirmation.setForeground(MainFrame.orange);
                    deleteDialog.add(confirmation, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    gbc.gridwidth = 1;
                    gbc.gridheight = 1;
                    gbc.weightx = 1.0;
                    gbc.weighty = 1.0;
                    JButton cancle = new JButton("cancle");
                    cancle.setPreferredSize(new Dimension(100, 40));
                    cancle.setBackground(MainFrame.lightGray);
                    cancle.setForeground(MainFrame.orange);
                    cancle.setFont(MainFrame.fontBold.deriveFont(25f));
                    cancle.setBorder(new LineBorder(MainFrame.extraLightGray, 1));
                    cancle.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            deleteDialog.dispose();
                            deleteDialog = null;
                        }
                    });
                    deleteDialog.add(cancle, gbc);

                    gbc.gridx = 1;
                    gbc.gridy = 1;
                    gbc.gridwidth = 1;
                    gbc.gridheight = 1;
                    gbc.weightx = 1.0;
                    gbc.weighty = 1.0;
                    JButton delete = new JButton("Delete");
                    delete.setPreferredSize(new Dimension(100, 40));
                    delete.setBackground(MainFrame.lightGray);
                    delete.setForeground(MainFrame.orange);
                    delete.setFont(MainFrame.fontBold.deriveFont(25f));
                    delete.setBorder(new LineBorder(MainFrame.extraLightGray, 1));
                    delete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //delete from labels and meals hashmap
                            sidePanel.meals.remove(meal);
                            sidePanel.mealsCntLabels.remove(meal);

                            sidePanel.centerPanel.remove(mealPanel);
                            sidePanel.centerPanel.remove(smallGap);
                            sidePanel.totalPrice.setText(String.valueOf(Float.parseFloat(sidePanel.totalPrice.getText()) - meal.getPrice()));
                            sidePanel.revalidate();
                            sidePanel.repaint();
                            deleteDialog.dispose();
                            deleteDialog = null;
                        }
                    });
                    deleteDialog.add(delete, gbc);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                mealPanel.setBorder(new LineBorder(new Color(91, 94, 102), 1));
                infoPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(91, 94, 102)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mealPanel.setBorder(new LineBorder(MainFrame.extraLightGray, 1));
                infoPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, MainFrame.extraLightGray));
            }
        });

        sidePanel.centerPanel.add(mealPanel) ;
        sidePanel.centerPanel.add(smallGap) ;
        sidePanel.totalPrice.setText(String.valueOf(Float.parseFloat(sidePanel.totalPrice.getText()) + meal.getPrice()));
        sidePanel.revalidate();
        sidePanel.repaint();
    }

    // for add new meal
    public Meal getNewMealInfo () {
        Meal meal = new Meal(sidePanel.getNameField(), sidePanel.getIngredientsField(),
                sidePanel.getPriceField(), sidePanel.getImgSrcField());
        return meal;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }

    // for edit meal
    public JPanel getMainMenu() {
        return mainMenu;
    }

    public Meal getCurrentMeal() {
        return currentEditMeal ;
    }
    public Meal getEditedMealInfo() {
        Meal meal = new Meal(sidePanel.getNameEdit().getText(), sidePanel.getIngredientsEdit().getText(),
                Float.parseFloat(sidePanel.getPriceEdit().getText()), sidePanel.getImgSrcEdit().getText()) ;
        return meal;
    }
    public MealPanel getMealPanel(Meal meal) {
        return allMeals.get(meal) ;
    }
    public HashMap<Meal, MealPanel> getAllMeals () {
        return allMeals ;
    }

    public void resetCurrentMeal() {
        currentEditMeal = null;
    }
    public void editMeals(Meal oldMeal, Meal newMeal, MealPanel panel){
        allMeals.remove(oldMeal);
        allMeals.put(newMeal, panel) ;
    }

}
