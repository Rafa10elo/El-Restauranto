package View;

import Model.Meal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MealsPanel extends JPanel {
    JPanel mainMenu;
    SidePanel sidePanel;
    int userType;

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
        int rowsNum = meals.size() / 3 + 1;
        int gap = 10 ;
        mainMenu.setLayout(new GridLayout(rowsNum, 3, gap, gap));

        for (Meal m : meals){
            mainMenu.add(addMealTOMenu(m));
        }

        int panelWidth = 900; // 900 - gap * 2 --> cell width
        int panelHeight = 350 * rowsNum + (rowsNum - 1) * gap ; // cell Height * rowsNum + (rowsNum - 1) * gap
        mainMenu.setPreferredSize(new Dimension(panelWidth, panelHeight));

        // Add buttons to the panel
//        for (int i = 1; i <= 15; i++) {
//            JButton button = new JButton("Button " + i);
//            button.setPreferredSize(new Dimension(300, 400));
//            add(button);
//        }

    }

    JPanel addMealTOMenu(Meal meal) {
        JPanel mealPanel = new JPanel() ;
        mealPanel.setSize(new Dimension(300, 400));
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
        JLabel mealPrice = new JLabel(":   " +String.valueOf(meal.getPrice()) + " $");
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
        JTextArea mealIngredients = new JTextArea(":   " +meal.getIngredients());
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

        // adding the photo and info
        mealPanel.add(mealPhoto, BorderLayout.NORTH) ;
        mealPanel.add(infoPanel, BorderLayout.CENTER);

        // adding mouse listener to make the panel act like a button 🐰
        mealPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (userType == 0) {
                    addMealToOrder(meal);
                    System.out.println("adding meal to the order");
                }else{
                    System.out.println("editing the meal dialog");
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

        this.revalidate();
        this.repaint();
        return mealPanel;
    }

    void addMealToOrder(Meal meal) {
        sidePanel.meals.add(meal) ;

        JPanel mealPanel = new JPanel() ;
        mealPanel.setSize(new Dimension(300, 400));
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
        JTextArea mealIngredients = new JTextArea(":   " +meal.getIngredients());
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

        // adding the photo and info
        mealPanel.add(mealPhoto, BorderLayout.NORTH) ;
        mealPanel.add(infoPanel, BorderLayout.CENTER);

        // adding mouse listener to make the panel act like a button 🐰
        mealPanel.addMouseListener(new MouseAdapter() {
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
        sidePanel.centerPanel.add(Box.createRigidArea(new Dimension(0, 10))) ;
        sidePanel.totalPrice.setText(String.valueOf(Float.parseFloat(sidePanel.totalPrice.getText()) + meal.getPrice()));
        this.revalidate();
        this.repaint();
    }


}
