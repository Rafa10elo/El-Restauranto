package View;

import Model.Meal;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class SidePanel extends JPanel {
    JLabel totalPrice = new JLabel("0") ;
    JComboBox tipsCombo;
    JPanel centerPanel;
    JTextField nameField;
    JTextField priceField;
    JTextField ingredientsField;
    JTextField imgSrcField;
    JButton submitOrder ;
    JButton addMeal ;
    ArrayList<Meal> meals = new ArrayList<>() ;



    public SidePanel(int userType) {
        setLayout(new BorderLayout());
        setBackground(MainFrame.darkGray);
        setPreferredSize(new Dimension(350, this.getHeight()));
        setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, MainFrame.orange));

        // top panel
        JPanel topPanel = new JPanel() ;
        topPanel.setBackground(MainFrame.darkGray);
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, MainFrame.orange));

        if (userType == 0){
            // Your order :
            JLabel youOrder = new JLabel("Your order :");
            youOrder.setForeground(MainFrame.orange);
            youOrder.setFont(MainFrame.fontBold);
            topPanel.add(youOrder) ;
        }
        else{
            // Add new meal :
            JLabel newMeal = new JLabel("Add new meal :");
            newMeal.setForeground(MainFrame.orange);
            newMeal.setFont(MainFrame.fontBold);
            topPanel.add(newMeal) ;
        }
        add(topPanel, BorderLayout.NORTH);


        // center panel
        centerPanel = new JPanel();
        if (userType == 0) {
            // order meals
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
            centerPanel.setBorder(new EmptyBorder(5, 15, 5, 15));
            centerPanel.setBackground(MainFrame.darkGray);
            add(new JScrollPane(centerPanel), BorderLayout.CENTER);
        }
        else {
            // name, price,ingredients and img path text fields
            centerPanel.setLayout(new GridBagLayout());
            centerPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
            centerPanel.setBackground(MainFrame.darkGray);
            GridBagConstraints gbc = new GridBagConstraints() ;

            // name
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            JLabel name = new JLabel("name :");
            name.setForeground(MainFrame.orange);
            name.setFont(MainFrame.fontBold);
            name.setBackground(MainFrame.darkGray);
            centerPanel.add(name, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.1;
            nameField = new JTextField("name");
            nameField.setFont(MainFrame.fontBold.deriveFont(20f));
            nameField.setForeground(MainFrame.extraLightGray);
            nameField.setBackground(MainFrame.darkGray);
            nameField.setBorder(new LineBorder(MainFrame.extraLightGray, 1));
            nameField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (nameField.getText().equals("name")) {
                        nameField.setText("");
                        nameField.setForeground(MainFrame.orange);
                    }
                    nameField.setBorder(new LineBorder(MainFrame.orange, 1));
                }
                @Override
                public void focusLost(FocusEvent e) {
                    if (nameField.getText().isEmpty()) {
                        nameField.setText("name");
                        nameField.setForeground(MainFrame.extraLightGray);
                    }
                    nameField.setBorder(new LineBorder(MainFrame.extraLightGray, 1));
                }
            });
            centerPanel.add(nameField, gbc) ;

            //price :
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            JLabel price = new JLabel("price : ($)");
            price.setForeground(MainFrame.orange);
            price.setFont(MainFrame.fontBold);
            price.setBackground(MainFrame.darkGray);
            centerPanel.add(price, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.1;
            priceField = new JTextField("price");
            priceField.setFont(MainFrame.fontBold.deriveFont(20f));
            priceField.setForeground(MainFrame.extraLightGray);
            priceField.setBackground(MainFrame.darkGray);
            priceField.setBorder(new LineBorder(MainFrame.extraLightGray, 1));
            priceField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    priceField.setBorder(new LineBorder(MainFrame.orange, 1));
                    if (priceField.getText().equals("price")) {
                        priceField.setText("");
                        priceField.setForeground(MainFrame.orange);
                    }
                }
                @Override
                public void focusLost(FocusEvent e) {
                    priceField.setBorder(new LineBorder(MainFrame.extraLightGray, 1));
                    if (priceField.getText().isEmpty()) {
                        priceField.setText("price");
                        priceField.setForeground(MainFrame.extraLightGray);
                    }
                }
            });
            priceField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    checkInput();
                }
                @Override
                public void keyTyped(KeyEvent e) {
                    checkInput();
                }
                public void checkInput() {
                    String text = priceField.getText();
                    if (!text.matches("\\d+")) {
                        priceField.setBorder(new LineBorder(Color.RED, 1));
                        priceField.setForeground(Color.RED);
                    } else {
                        priceField.setBorder(new LineBorder(MainFrame.orange, 1));
                        priceField.setForeground(MainFrame.orange);
                    }
                }
            });
            centerPanel.add(priceField, gbc) ;

            // ingredients
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            JLabel ingredients = new JLabel("ingredients :");
            ingredients.setForeground(MainFrame.orange);
            ingredients.setFont(MainFrame.fontBold);
            ingredients.setBackground(MainFrame.darkGray);
            centerPanel.add(ingredients, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.1;
            ingredientsField = new JTextField("ingredients");
            ingredientsField.setFont(MainFrame.fontBold.deriveFont(20f));
            ingredientsField.setForeground(MainFrame.extraLightGray);
            ingredientsField.setBackground(MainFrame.darkGray);
            ingredientsField.setBorder(new LineBorder(MainFrame.extraLightGray, 1));
            ingredientsField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (ingredientsField.getText().equals("ingredients")) {
                        ingredientsField.setText("");
                        ingredientsField.setForeground(MainFrame.orange);
                    }
                    ingredientsField.setBorder(new LineBorder(MainFrame.orange, 1));
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (ingredientsField.getText().isEmpty()) {
                        ingredientsField.setText("ingredients");
                        ingredientsField.setForeground(MainFrame.extraLightGray);
                    }
                    ingredientsField.setBorder(new LineBorder(MainFrame.extraLightGray, 1));
                }
            });
            centerPanel.add(ingredientsField, gbc) ;

            // img src
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            JLabel imagePath = new JLabel("image path :");
            imagePath.setForeground(MainFrame.orange);
            imagePath.setFont(MainFrame.fontBold);
            imagePath.setBackground(MainFrame.darkGray);
            centerPanel.add(imagePath, gbc);

            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.1;
            imgSrcField = new JTextField("image path");
            imgSrcField.setPreferredSize(new Dimension(280, 50));
            imgSrcField.setFont(MainFrame.fontBold.deriveFont(20f));
            imgSrcField.setForeground(MainFrame.extraLightGray);
            imgSrcField.setBackground(MainFrame.darkGray);
            imgSrcField.setBorder(new LineBorder(MainFrame.extraLightGray, 1));
            imgSrcField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (imgSrcField.getText().equals("image path")) {
                        imgSrcField.setText("");
                        imgSrcField.setForeground(MainFrame.orange);
                    }
                    imgSrcField.setBorder(new LineBorder(MainFrame.orange, 1));
                }
                @Override
                public void focusLost(FocusEvent e) {
                    if (imgSrcField.getText().isEmpty()) {
                        imgSrcField.setText("image path");
                        imgSrcField.setForeground(MainFrame.extraLightGray);
                    }
                    imgSrcField.setBorder(new LineBorder(MainFrame.extraLightGray, 1));
                }
            });
            centerPanel.add(imgSrcField, gbc) ;

            add(centerPanel, BorderLayout.CENTER);
        }



        // bottom panel
        if (userType == 0) {
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            bottomPanel.setBackground(MainFrame.darkGray);
            bottomPanel.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, MainFrame.orange));

            //total price panel : (TOTAL PRICE : ??? $)
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.gridheight = 2;
            gbc.weightx = 2.0;
            gbc.weighty = 2.0;
            gbc.fill = GridBagConstraints.BOTH;

            JPanel totalPricePanel = new JPanel() ;
            totalPricePanel.setBackground(MainFrame.darkGray);

            JLabel totalPriceLabel = new JLabel(" Total Price : " );
            totalPriceLabel.setForeground(MainFrame.orange);
            totalPriceLabel.setFont(MainFrame.fontBold);
            totalPriceLabel.setBackground(MainFrame.darkGray);
            totalPricePanel.add(totalPriceLabel) ;

            totalPrice.setFont(MainFrame.fontBold);
            totalPrice.setBackground(MainFrame.darkGray);
            totalPricePanel.add(totalPrice) ;

            JLabel dollar = new JLabel(" $") ;
            dollar.setForeground(MainFrame.orange);
            dollar.setFont(MainFrame.fontBold);
            dollar.setBackground(MainFrame.darkGray);
            totalPricePanel.add(dollar) ;

            bottomPanel.add(totalPricePanel, gbc) ;

            //tip
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;

            JPanel tipPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            tipPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, MainFrame.extraLightGray));
            tipPanel.setBackground(MainFrame.darkGray);

            JLabel tipLabel = new JLabel("tip :");
            tipLabel.setForeground(MainFrame.orange);
            tipLabel.setFont(MainFrame.fontBold.deriveFont(25f));

            Integer[] tips = new Integer[] {0, 5, 10, 15} ;
            tipsCombo = new JComboBox(tips) ;
            tipsCombo.setFont(MainFrame.fontBold.deriveFont(25f));
            tipsCombo.setBackground(MainFrame.darkGray);
            tipsCombo.setBorder(new LineBorder(MainFrame.extraLightGray, 1));

            tipPanel.add(tipLabel);
            tipPanel.add(tipsCombo);

            bottomPanel.add(tipPanel, gbc) ;

            // submit order
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            gbc.gridheight = 3;
            gbc.weightx = 3.0;
            gbc.weighty = 3.0;
            gbc.fill = GridBagConstraints.BOTH;
            submitOrder = new JButton("Submit Order");
            submitOrder.setForeground(MainFrame.orange);
            submitOrder.setFont(MainFrame.fontBold);
            submitOrder.setBackground(MainFrame.darkGray);
            submitOrder.setBorder(new LineBorder(MainFrame.extraLightGray, 2));

            // this action listener is just for fun ... it should be in the controller
            submitOrder.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    orderReset();
                }
            });
            bottomPanel.add(submitOrder, gbc);

            add(bottomPanel, BorderLayout.SOUTH);
        }
        else{
            addMeal  = new JButton("add meal");
            addMeal.setPreferredSize(new Dimension(this.getWidth(), 100));
            addMeal.setForeground(MainFrame.orange);
            addMeal.setFont(MainFrame.fontBold);
            addMeal.setBackground(MainFrame.darkGray);
            addMeal.setBorder(new LineBorder(MainFrame.orange, 2));

            // this action listener is just for fun ... it should be in the controller  x 2
            addMeal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (mealInfoValid()){
                        textFieldsReset();
                        System.out.println("tmmmm");
                    }else
                        System.out.println("wrong");
                }
            });
            add(addMeal, BorderLayout.SOUTH) ;
        }

    }
    boolean mealInfoValid(){
        File temp = new File(imgSrcField.getText()) ;
        boolean tempIsImg = temp.getPath().endsWith(".jpg") || temp.getPath().endsWith(".jpeg") || temp.getPath().endsWith(".png")
                || temp.getPath().endsWith(".gif") || temp.getPath().endsWith(".bmp") ;
        if (!nameField.getText().equals("name") && priceField.getText().matches("\\d+") &&
                !ingredientsField.getText().equals("ingredients") && temp.exists() && tempIsImg){
            textFieldsReset();
            return true;
        }else{
            if (nameField.getText().isEmpty() || nameField.getText().equals("name")){
                nameField.setBorder(new LineBorder(Color.RED, 1));
            }
            if (priceField.getText().isEmpty() || priceField.getText().equals("price")){
                priceField.setBorder(new LineBorder(Color.RED, 1));
            }
            if (ingredientsField.getText().isEmpty() || ingredientsField.getText().equals("ingredients")){
                ingredientsField.setBorder(new LineBorder(Color.RED, 1));
            }
            if( imgSrcField.getText().isEmpty() || imgSrcField.getText() == null || !temp.exists() || !tempIsImg ) {
                imgSrcField.setBorder(new LineBorder(Color.RED, 1));
            }
            return false;
        }

    }
    void textFieldsReset () {
        nameField.setText("name");
        nameField.setForeground(MainFrame.extraLightGray);
        priceField.setText("price");
        priceField.setForeground(MainFrame.extraLightGray);
        ingredientsField.setText("ingredients");
        ingredientsField.setForeground(MainFrame.extraLightGray);
        imgSrcField.setText("image path");
        imgSrcField.setForeground(MainFrame.extraLightGray);

    }
    void orderReset(){
        totalPrice.setText("0");
        centerPanel.removeAll();
        tipsCombo.setSelectedIndex(0);
        revalidate();
        repaint();
    }
}
