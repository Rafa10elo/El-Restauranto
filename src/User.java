import java.util.ArrayList;

public class User {
    private String userName;
    private String password;
    private boolean isEmployee;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    private ArrayList<Order> orders;

    public User(String userName, String password, boolean isEmployee) {
        this.userName = userName;
        this.password = password;
        this.isEmployee = isEmployee;
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public String toFileFormat() {
        String userString = userName + "***" + password + "***" + isEmployee + "***";

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
            boolean isEmployee = Boolean.parseBoolean(userParts[2]);

            User user = new User(userName, password, isEmployee);

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
