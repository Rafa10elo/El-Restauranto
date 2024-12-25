package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class gitOrders {
    private HashMap<User, List<Order>> userOrders;

    public Orders() {
        this.userOrders = new HashMap<>();
    }

    public void addOrderForUser(User user, Order order) {
        if (!userOrders.containsKey(user)) {
            userOrders.put(user, new ArrayList<>());
        }
        userOrders.get(user).add(order);
    }

    public List<Order> getOrdersForUser(User user) {
        return userOrders.getOrDefault(user, new ArrayList<>());
    }

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> allOrders = new ArrayList<>();
        for (List<Order> orders : userOrders.values()) {
            allOrders.addAll(orders);
        }
        return allOrders;
    }

    public boolean updateOrder(User user, Order order, Order.Status status) {
        List<Order> orders = userOrders.get(user);
        user.getOrders().get(user.findOrder(order)).setState(status);

        if (orders != null) {
            for (Order existingOrder : orders) {
                if (existingOrder.equals(order)) {
                    existingOrder.setState(status);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean removeOrder(User user, Order order) {
        user.getOrders().remove(order);
        List<Order> orders = userOrders.get(user);
        if (orders != null) {
            boolean removed = orders.remove(order);
            if (removed) {
                return true;
            }
        }
        return false;
    }





}
