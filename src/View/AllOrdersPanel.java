package View;

import Model.Meal;
import Model.Order;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllOrdersPanel extends JPanel {

    public AllOrdersPanel (ArrayList<Order> orders){
        setPreferredSize(new Dimension(1280,670));
        setLayout(new BorderLayout());
        JPanel miniAllOrdersPanel = new JPanel();

        miniAllOrdersPanel.setLayout(new BoxLayout(miniAllOrdersPanel, BoxLayout.Y_AXIS));
        miniAllOrdersPanel.setBackground(MainFrame.darkGray);
        int cnt=0;
        for(Order order: orders){
           cnt++;
        }
        miniAllOrdersPanel.add(Box.createRigidArea(new Dimension(0,10)));
        for(int i=0; i<cnt;i++){
            JPanel orderPanel= createOrderPanel(orders.get(i),i+1);
            orderPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,MainFrame.orange));
            miniAllOrdersPanel.add(orderPanel);
            miniAllOrdersPanel.add(Box.createRigidArea(new Dimension(0,10)));
        }

        JScrollPane scrollPane = new JScrollPane(miniAllOrdersPanel);
        scrollPane.setVerticalScrollBar(new JScrollBar());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        miniAllOrdersPanel.setBorder(new EmptyBorder(0,30,0,30));
        add(scrollPane,BorderLayout.CENTER);


    }

    JLabel createLabel(String message,Font font,float fontSize){
        JLabel label = new JLabel(message);
        label.setFont(font.deriveFont(fontSize));
        return label;
    }

    JPanel createMealPanel (Meal meal){
        JPanel mealPanel = new JPanel();
        mealPanel.setLayout(new GridBagLayout());
        mealPanel.setBackground(MainFrame.darkGray);
        mealPanel.setPreferredSize(new Dimension(110,150));
        mealPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,MainFrame.extraLightGray));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,3,3,3);
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
        JLabel nameLabel = createLabel("Name:",MainFrame.fontBold,22);

        gbc.gridx=0;
        gbc.gridy=1;
        mealPanel.add(nameLabel,gbc);
        JLabel mealNameLabel = createLabel(meal.getMealName(),MainFrame.fontRegular,22);
        gbc.gridx=1;
        gbc.gridy=1;
        mealPanel.add(mealNameLabel,gbc);
        JLabel priceLabel = createLabel("Price:",MainFrame.fontBold,22);
        gbc.gridx=0;
        gbc.gridy=2;
        mealPanel.add(priceLabel,gbc);
        JLabel mealPriceLabel = createLabel(String.valueOf(meal.getPrice()),MainFrame.fontRegular,22);
        gbc.gridx=1;
        gbc.gridy=2;
        mealPanel.add(mealPriceLabel,gbc);

        return mealPanel;
    }

    JPanel createMultipleMealsPanel (HashMap<Meal,Integer> meals) {
        JPanel multipleMealsPanel = new JPanel();
        multipleMealsPanel.setBackground(MainFrame.lightGray);
        multipleMealsPanel.setBorder(new EmptyBorder(5,5,5,5));
        multipleMealsPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,MainFrame.lightGray));
        multipleMealsPanel.setPreferredSize(new Dimension(meals.size()*(115),210));
        multipleMealsPanel.setLayout(new BoxLayout(multipleMealsPanel,BoxLayout.X_AXIS));

//        multipleMealsPanel.add(Box.createRigidArea(new Dimension(3,0)));
        List<Map.Entry<Meal, Integer>> entryList = new ArrayList<>(meals.entrySet());
        for (int i=0;i<meals.size();i++){
            Map.Entry<Meal, Integer> entry = entryList.get(i);
            Meal meal = entry.getKey();
            JPanel mealPanel = createMealPanel(meal);

            multipleMealsPanel.add(mealPanel);
            multipleMealsPanel.add(Box.createRigidArea(new Dimension(3,0)));
        }

        return multipleMealsPanel;
    }

    JPanel createOrderPanel (Order order, int cnt){
        JPanel orderPanel = new JPanel();
        orderPanel.setPreferredSize(new Dimension(1200,280));
        orderPanel.setLayout(new BorderLayout());
        orderPanel.setBackground(MainFrame.darkGray);
        orderPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,MainFrame.lightGray));

        JPanel labelsPanel = new JPanel(new FlowLayout(0));
        orderPanel.add(labelsPanel,BorderLayout.NORTH);
        JLabel orderLabel = createLabel("Order no."+String.valueOf(cnt)+":                     ",MainFrame.fontBold,22);
        orderLabel.setForeground(MainFrame.orange);
        labelsPanel.add(orderLabel);

        JLabel timeTagLabel = createLabel("Time: ",MainFrame.fontBold,22);
        labelsPanel.add(timeTagLabel);
//        JLabel timeLabel = createLabel(order.getTimeOfDelivery().toString()+"          ",fontRegular);
        JLabel timeLabel = createLabel("20:23:42"+"          ",MainFrame.fontRegular,22);
        labelsPanel.add(timeLabel);

        JLabel priceTagLabel = createLabel("TotalPrice: ",MainFrame.fontBold,22);
        labelsPanel.add(priceTagLabel);
//        JLabel priceLabel = createLabel(String.valueOf(order.getTotalPrice()),fontRegular);
        JLabel priceLabel = createLabel(String.valueOf(3782)+"          ",MainFrame.fontRegular,22);
        labelsPanel.add(priceLabel);

        String orderState = "";
        if(order.getState()== Order.Status.PREPARED)
            orderState="Prepared";
        else if(order.getState()== Order.Status.DELIVERED)
            orderState="Delivered";
        else
            orderState="Canceled";

        JLabel stateTagLabel = createLabel("Order State: ",MainFrame.fontBold,22);
        labelsPanel.add(stateTagLabel);

        JLabel stateLabel = createLabel(orderState,MainFrame.fontRegular,22);
        labelsPanel.add(stateLabel);


        //
        JPanel panel = createMultipleMealsPanel(order.getMeals());
        JScrollPane multiMealScrollPane = new JScrollPane(panel);
        multiMealScrollPane.createHorizontalScrollBar();
        multiMealScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        multiMealScrollPane.setBorder(null);
        orderPanel.add(multiMealScrollPane, BorderLayout.CENTER);

        return orderPanel;
    }
}
