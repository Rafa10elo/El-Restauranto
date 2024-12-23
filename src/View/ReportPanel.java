package View;
import Model.Meal;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportPanel extends JPanel {
    public ReportPanel (Model.Report report,int numberOfUsers,int numberOfMeals){



        setLayout(new BorderLayout());
        //title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBackground(MainFrame.lightGray);
        titlePanel.setPreferredSize(new Dimension(getWidth(),50));
        add(titlePanel,BorderLayout.NORTH);
        GridBagConstraints titleGbc = new GridBagConstraints();
        titleGbc.insets = new Insets(0,250,0,0);
        titleGbc.anchor= GridBagConstraints.CENTER;

        titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, MainFrame.orange));
        JLabel titleLabel = createLabel("El Restauranto Detailed Report",MainFrame.fontBold.deriveFont(40F));
        titleLabel.setPreferredSize(new Dimension(345,40));
        titleLabel.setForeground(MainFrame.orange);
        titlePanel.add(titleLabel,titleGbc);


        //top 3 meals panel
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridBagLayout());
        sidePanel.setBackground(MainFrame.lightGray);
        sidePanel.setPreferredSize(new Dimension(250, getHeight()));
        sidePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, MainFrame.orange));
        add(sidePanel, BorderLayout.WEST);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 90, 0, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor= GridBagConstraints.WEST;
        gbc.gridwidth=1;
        gbc.weightx=1.0;

        JLabel top3Meals = createLabel("Top 3 Meals: ",MainFrame.fontBold.deriveFont(22F));
        top3Meals.setForeground(MainFrame.orange);
        gbc.gridx = 0;
        gbc.gridy = 0;
        sidePanel.add(top3Meals,gbc);
        gbc.insets = new Insets(2, 5, 2, 5);

//        actual code is:
//        List<Map.Entry<Meal, Integer>> meals = report.getSortedOrderedMeals();
//        this is for run purposes:
        List<Map.Entry<Meal, Integer>> meals = new ArrayList<>();
        Meal meal1 = new Meal("Pizza", "Delicious cheesy pizza", 100, "src/View/Images/profilePicture.png");
        Meal meal2 = new Meal("Burger", "Juicy beef burger", 150, "src/View/Images/profilePicture.png");
        Meal meal3 = new Meal("Pasta", "Creamy Alfredo pasta", 120, "src/View/Images/profilePicture.png");
        meals.add(new AbstractMap.SimpleEntry<>(meal1, 1));
        meals.add(new AbstractMap.SimpleEntry<>(meal2, 2));
        meals.add(new AbstractMap.SimpleEntry<>(meal3, 3));
//        till here
        for(int i=0;i<3;i++){
            Map.Entry<Meal, Integer> entry = meals.get(i);
            Meal meal = entry.getKey();
            JPanel mealPanel = createMealPanel(meal);
            gbc.gridy=i+1;
            sidePanel.add(mealPanel,gbc);
        }

        JPanel mainReportPanel = new JPanel();
        mainReportPanel.setLayout(new GridBagLayout());
        mainReportPanel.setBackground(MainFrame.darkGray);
        add(mainReportPanel,BorderLayout.CENTER);
        gbc.insets = new Insets(4,4,4,4);
//        JPanel ordersStats = createInfoPanel("Total Number of Orders: ",String.valueOf(report.getNumberOfOrders()),fontBold,fontRegular);
        JPanel ordersStats = createInfoPanel("Total Number of Orders: ",String.valueOf(129),MainFrame.fontBold.deriveFont(22F),MainFrame.fontRegular.deriveFont(22F));
        gbc.gridx=0;
        gbc.gridy=0;
        mainReportPanel.add(ordersStats,gbc);

        JPanel mealsStats = createInfoPanel("Total Number of Meals: ",String.valueOf(numberOfMeals),MainFrame.fontBold.deriveFont(22F),MainFrame.fontRegular.deriveFont(22F));
        gbc.gridx=0;
        gbc.gridy=1;
        mainReportPanel.add(mealsStats,gbc);
//        JPanel usersStats = createInfoPanel("Total Users: ",String.valueOf(numberOfUsers),fontBold,fontRegular);
        JPanel usersStats = createInfoPanel("Total Users: ",String.valueOf(50),MainFrame.fontBold.deriveFont(22F),MainFrame.fontRegular.deriveFont(22F));//here we put total num of users given by order
        gbc.gridx=0;
        gbc.gridy=2;
        mainReportPanel.add(usersStats,gbc);
//        JPanel mostFrequentUser = createInfoPanel("Most Ordering Customer: ",report.getMostOrderingUser().getUserName(),fontBold,fontRegular);
        JPanel mostFrequentUser = createInfoPanel("Most Ordering Customer: ","Hamoudeh",MainFrame.fontBold.deriveFont(22F),MainFrame.fontRegular.deriveFont(22F));//here we put most ordering user
        gbc.gridx=0;
        gbc.gridy=3;
        mainReportPanel.add(mostFrequentUser,gbc);

//        JPanel mostFrequentlyOrderedMeal = createInfoPanel("Customers' Favorite Meal: ",report.getMostSoldMeal().getMealName(),fontBold,fontRegular);
        JPanel mostFrequentlyOrderedMeal = createInfoPanel("Customers' Favorite Meal: ","Shawarma",MainFrame.fontBold.deriveFont(22F),MainFrame.fontRegular.deriveFont(22F));//here we put top meal
        gbc.gridx=0;
        gbc.gridy=4;
        mainReportPanel.add(mostFrequentlyOrderedMeal,gbc);

        JPanel totalIncome = createInfoPanel("Total income: ",String.valueOf(report.getTotalMoney()),MainFrame.fontBold.deriveFont(22F),MainFrame.fontRegular.deriveFont(22F));
        totalIncome.setBorder(BorderFactory.createMatteBorder(1,0,1,0,MainFrame.orange));
        gbc.gridx=0;
        gbc.gridy=5;
        mainReportPanel.add(totalIncome,gbc);


    }

    JPanel createInfoPanel(String message1, String message2, Font fontBold, Font fontRegular){
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        infoPanel.setBackground(MainFrame.darkGray);
        gbc.insets = new Insets(7, 7, 7, 7);
        infoPanel.setBorder(BorderFactory.createMatteBorder(1,0,1,0,MainFrame.lightGray));
        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor= GridBagConstraints.WEST;
        gbc.gridwidth=1;
        gbc.weightx=1.0;

        JLabel titleLabel=createLabel(message1,fontBold);
        titleLabel.setForeground(Color.white);
        gbc.gridx=0;
        gbc.gridy=0;
        infoPanel.add(titleLabel,gbc);

        JLabel infoLabel =createLabel(message2,fontRegular);
        infoLabel.setForeground(Color.white);
        gbc.gridx=0;
        gbc.gridy=1;
        infoPanel.add(infoLabel,gbc);

        return infoPanel;
    }

    JLabel createLabel(String message,Font font){
        JLabel label = new JLabel(message);
        label.setFont(font);
        return label;
    }

    JPanel createMealPanel (Model.Meal meal){
        JPanel mealPanel = new JPanel();
        mealPanel.setLayout(new GridBagLayout());
        mealPanel.setBackground(MainFrame.darkGray);
        mealPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,MainFrame.lightGray));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth=2;

        ImageIcon profileIcon = new ImageIcon(meal.getImgSrc());
        Image scaledImage = profileIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel mealPicLabel = new JLabel(new ImageIcon(scaledImage));
        mealPicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        mealPanel.add(mealPicLabel, gbc);

        gbc.gridwidth=1;
        gbc.insets = new Insets(3,3,3,3);
        JLabel nameLabel = createLabel("Name:",MainFrame.fontBold.deriveFont(22F));
        gbc.gridx=0;
        gbc.gridy=1;
        mealPanel.add(nameLabel,gbc);
        JLabel mealNameLabel = createLabel(meal.getMealName(),MainFrame.fontRegular.deriveFont(22F));
        gbc.gridx=1;
        gbc.gridy=1;
        mealPanel.add(mealNameLabel,gbc);
        JLabel priceLabel = createLabel("Price:",MainFrame.fontBold.deriveFont(22F));
        gbc.gridx=0;
        gbc.gridy=2;
        mealPanel.add(priceLabel,gbc);
        JLabel mealPriceLabel = createLabel(String.valueOf(meal.getPrice()),MainFrame.fontRegular.deriveFont(22F));
        gbc.gridx=1;
        gbc.gridy=2;
        mealPanel.add(mealPriceLabel,gbc);

        return mealPanel;
    }
}
