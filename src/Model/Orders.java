package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Orders {
    private HashMap<User, ArrayList<Order>> ordersOfUsers;
    private static Orders orders;
    public Orders() {
        this.ordersOfUsers = new HashMap<>();
    }

    public static Orders getOrdersSing() {
        if (orders == null) {
            orders = new Orders();
        }
        return orders;
    }

    public void addOrderForUser(User user, Order order) {
        if (!ordersOfUsers.containsKey(user)) {
            ordersOfUsers.put(user, new ArrayList<>());
        }
        ordersOfUsers.get(user).add(order);
    }

    public ArrayList<Order> getOrdersForUser(User user) {
        return ordersOfUsers.getOrDefault(user, new ArrayList<Order>());
    }

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> allOrders = new ArrayList<>();
        for (List<Order> orders : ordersOfUsers.values()) {
            allOrders.addAll(orders);
        }
        return allOrders;
    }

    public List<Order> sortedListOfOrderes() {
        ArrayList<Order> allOrders = getAllOrders();
        allOrders.sort(Comparator.comparing(Order::getTimeOfDelivery));
        return allOrders;
    }

    public boolean updateOrder(User user, Order order, Order.Status status) {
        List<Order> orders = ordersOfUsers.get(user);

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
        List<Order> orders = ordersOfUsers.get(user);
        if (orders != null) {
            boolean removed = orders.remove(order);
            if (removed) {
                return true;
            }
        }
        return false;
    }


}
