package View;
import Model.Meal;
import Model.Order;
import Model.User;
import com.formdev.flatlaf.FlatDarkLaf ;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainFrame extends JFrame {
    public MealsPanel mealsPanel;

    public static Color darkGray = new Color(30, 31, 34) ;
    public static Color lightGray = new Color(43, 45, 48) ;
    public static Color extraLightGray = new Color(57, 59, 64) ;
    public static Color orange = new Color(206, 129, 76) ;
    public static Font fontBold = null ;
    public static Font fontRegular = null ;
    public static CardLayout cardLayout ;

    static {
        try {
            fontBold = Font.createFont( Font.TRUETYPE_FONT, new File("src/View/Fonts/AmaticSC-Bold.ttf")).deriveFont(35f) ;
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            fontRegular = Font.createFont( Font.TRUETYPE_FONT, new File("src/View/Fonts/AmaticSC-Regular.ttf")).deriveFont(35f) ;
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  MainFrame(int userType){
        try{
            UIManager.setLookAndFeel(new FlatDarkLaf());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setSize(new Dimension(1280,720));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());

        // The top panel, which contains the buttons : Meals, Profile, and All Orders
        JPanel navigationBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)) ;
        navigationBarPanel.setBackground(lightGray);
        navigationBarPanel.setPreferredSize(new Dimension(this.getWidth() , 50));
        navigationBarPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, orange));
        JButton profileButton = createButton("Your Profile");
        JButton mainMenuButton = createButton("Main Menu") ;
        JButton allOrdersButton = createButton("All Orders") ;
        JButton reportButton = createButton("Report") ;

        navigationBarPanel.add(profileButton);
        navigationBarPanel.add(mainMenuButton);
        navigationBarPanel.add(allOrdersButton);
        navigationBarPanel.add(reportButton);

        add(navigationBarPanel, BorderLayout.NORTH) ;

        // cards panel
        cardLayout = new CardLayout();
        JPanel cardsPanel = new JPanel(cardLayout);
        mealsPanel = new MealsPanel(userType) ;
        cardsPanel.add(mealsPanel,"mealsPanel") ;
       add(cardsPanel, BorderLayout.CENTER) ;

        //for run purposes
//        User user = new User("hamoudeh","mumu.2005@gmail.com","anaHamoudeh1234",0);
//        add(new ProfilePanel(user),BorderLayout.CENTER);
//        ReportPanel reportPanel = new ReportPanel(new Model.Report(54,5562),50,19);
//        add(reportPanel, BorderLayout.CENTER);
        //will get deleted
        User user = new User("hamoudeh","mumu.2005@gmail.com","anaHamoudeh1234",0);
        ProfilePanel profilePanel= new ProfilePanel(user);
        cardsPanel.add(profilePanel,"profilePanel");
//
        ReportPanel reportPanel = new ReportPanel(new Model.Report(54,5562),50,19);
        cardsPanel.add(reportPanel,"reportPanel");

        HashMap<Meal, Integer> meals = new HashMap<>();
        meals.put(new Meal("Pizza", "Salami", 34500, "src/View/Images/profilePicture.png"), 10);
        meals.put(new Meal("beep", "Salami", 6600, "src/View/Images/profilePicture.png"), 10);
        meals.put(new Meal("boop", "Salami", 4300, "src/View/Images/profilePicture.png"), 10);
        meals.put(new Meal("bap", "Salami", 34540, "src/View/Images/profilePicture.png"), 10);
        meals.put(new Meal("bop", "Salami", 35400, "src/View/Images/profilePicture.png"), 10);
        meals.put(new Meal("Pasta", "Sauce", 24500, "src/View/Images/profilePicture.png"), 20);
        meals.put(new Meal("Pizza", "Salami", 34500, "src/View/Images/profilePicture.png"), 10);
        meals.put(new Meal("beep", "Salami", 6600, "src/View/Images/profilePicture.png"), 10);
        meals.put(new Meal("boop", "Salami", 4300, "src/View/Images/profilePicture.png"), 10);
        meals.put(new Meal("bap", "Salami", 34540, "src/View/Images/profilePicture.png"), 10);
        meals.put(new Meal("bop", "Salami", 35400, "src/View/Images/profilePicture.png"), 10);
        meals.put(new Meal("Pasta", "Sauce", 24500, "src/View/Images/profilePicture.png"), 20);
        Order order1 = new Order(meals,10, Order.Status.DELIVERED);
        Order order2 = new Order(meals,15, Order.Status.CANCELED);
        Order order3 = new Order(meals,5, Order.Status.PREPARED );
        Order order4 = new Order(meals,10, Order.Status.DELIVERED);
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);

        AllOrdersPanel allOrdersPanel = new AllOrdersPanel(orders);
        cardsPanel.add(allOrdersPanel,"allOrdersPanel") ;


        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardsPanel, "profilePanel");
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardsPanel, "mealsPanel");
            }
        });

        allOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardsPanel, "allOrdersPanel");
            }
        });

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardsPanel, "reportPanel");
            }
        });
    }


    JButton createButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(orange);
        button.setFont(fontBold);
        button.setPreferredSize(new Dimension(170, 40));
        return button;

    }
}
