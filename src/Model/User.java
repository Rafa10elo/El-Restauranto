package Model;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private String userName;
    private String email;
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

    public String getEmail() { return email; }

    private ArrayList<Order> orders;

    public User(String userName, String email,String password, int userType) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public String toFileFormat() {
        String userString = userName + "***" + email + "***"+ password + "***" + userType + "***";

        for (Order order : orders) {
            userString += order.toFileFormat() + "###";
        }

        return userString;
    }

    public static User fromFileFormat(String str) {
        try {
            String[] userParts = str.split("\\*\\*\\*");
            String userName = userParts[0];
            String email = userParts[1];
            String password = userParts[2];
            int userType= Integer.parseInt(userParts[3]);

            User user = new User(userName,email,password, userType);

            if (userParts.length > 4) {
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userType == user.userType &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, email, password, userType, orders);
    }

}
