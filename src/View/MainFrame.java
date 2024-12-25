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
    public ProfilePanel profilePanel ;
    public ReportPanel reportPanel;
    public AllOrdersPanel allOrdersPanel;


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

    public  MainFrame(int userType, ProfilePanel profilePanel, ReportPanel reportPanel, AllOrdersPanel allOrdersPanel){
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

        this.profilePanel = profilePanel ;
        cardsPanel.add(profilePanel,"profilePanel");

        this.reportPanel = reportPanel;
        cardsPanel.add(reportPanel,"reportPanel");

        this.allOrdersPanel = allOrdersPanel ;
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

        revalidate();
        repaint();
    }

    public void switchCard(JPanel parent,String child){
        cardLayout.show(parent,child);
    }


    JButton createButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setBorderPainted(false);
//        button.setContentAreaFilled(false);
        button.setBackground(lightGray);
        button.setForeground(orange);
        button.setFont(fontBold);
        button.setPreferredSize(new Dimension(170, 40));
        return button;

    }
}
