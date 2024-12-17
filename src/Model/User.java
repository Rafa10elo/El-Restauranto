package Model;

import java.util.ArrayList;

public class User {
    private String userName;
    private String password;
    private int userType;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public int getUserType() {
        return userType;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    private ArrayList<Order> orders;

    public User(String userName, String password, int userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public String toFileFormat() {
        String userString = userName + "***" + password + "***" + userType + "***";

        for (Order order : orders) {
            userString += order.toFileFormat() + "###";
        }

        return userString;
    }

    public static User fromFileFormat(String str) {
        try {
            String[] userParts = str.split("\\*\\*\\*");
            String userName = userParts[0];
            String password = userParts[1];
            int userType= Integer.parseInt(userParts[2]);

            User user = new User(userName, password, userType);

            if (userParts.length > 3) {
                String[] orderStrings = userParts[3].split("###");
                for (String orderStr : orderStrings) {
                    Order order = Order.fromFileFormat(orderStr);
                    if (order != null) {
                        user.addOrder(order);
                    }
                }
            }

            return user;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
