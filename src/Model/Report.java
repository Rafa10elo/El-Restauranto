package Model;

import java.io.*;
import java.util.*;

public class Report {
    private int numberOfOrders;
    private double totalMoney;

    private HashMap<Meal, Integer> orderedMeals;
    private HashMap<User, Integer> orderingUsers;

    public Report(int numberOfOrders, double totalMoney) {
        this.numberOfOrders = numberOfOrders;
        this.totalMoney = totalMoney;

        this.orderedMeals = new HashMap<>();
        this.orderingUsers = new HashMap<>();
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void incrementMealCount(Meal meal, int cnt) {
        orderedMeals.put(meal, orderedMeals.getOrDefault(meal, 0) + cnt);
    }

    public void incrementUserCount(User user, int cnt) {
        orderingUsers.put(user, orderingUsers.getOrDefault(user, 0) + cnt);
    }

    public void increaseNumberOfOrders() {
        numberOfOrders++;
    }

    public void addToTotalMoney(double amount) {
        totalMoney += amount;
    }

    public Meal getMostSoldMeal() {
        List<Map.Entry<Meal, Integer>> sortedMeals = getSortedOrderedMeals();
        return sortedMeals.isEmpty() ? null : sortedMeals.get(0).getKey();
    }

    public User getMostOrderingUser() {
        List<Map.Entry<User, Integer>> sortedUsers = getSortedOrderingUsers();
        return sortedUsers.isEmpty() ? null : sortedUsers.get(0).getKey();
    }

    public List<Map.Entry<Meal, Integer>> getSortedOrderedMeals() {
        List<Map.Entry<Meal, Integer>> list = new ArrayList<>(orderedMeals.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        System.out.println("Sorted meals: " + list);
        return list;
    }

    public List<Map.Entry<User, Integer>> getSortedOrderingUsers() {
        List<Map.Entry<User, Integer>> list = new ArrayList<>(orderingUsers.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        return list;
    }

    public void writerThread(){

        new Thread(()->{
            saveToFile();
        }).start();

    }
    public void readerThread(){

        new Thread(()->{
            loadFromFile();
        }).start();

    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Reports.txt"))) {
            writer.write(String.valueOf(numberOfOrders));
            writer.newLine();
            writer.write(String.valueOf(totalMoney));
            writer.newLine();

            for (Map.Entry<Meal, Integer> entry : orderedMeals.entrySet()) {
                writer.write(entry.getKey().toFileFormat() + "***" + entry.getValue());
                writer.newLine();
            }
            writer.write("#####");
            writer.newLine();

            for (Map.Entry<User, Integer> entry : orderingUsers.entrySet()) {
                writer.write(entry.getKey().toFileFormat() + "***" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static Report loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Reports.txt"))) {
            int numberOfOrders = Integer.parseInt(reader.readLine());
            double totalMoney = Double.parseDouble(reader.readLine());
            Report report = new Report(numberOfOrders, totalMoney);

            String line;
            while (!(line = reader.readLine()).equals("#####")) {
                String[] parts = line.split("\\*\\*\\*");
                if (parts.length != 2) {
                    throw new IOException("Invalid meal" + line);
                }
                Meal meal = Meal.fromFileFormat(parts[0]);
                int cnt = Integer.parseInt(parts[1]);
                report.incrementMealCount(meal, cnt);
            }

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\*\\*\\*");
                if (parts.length != 2) {
                    throw new IOException("Invalid meal" + line);
                }
                User user = User.fromFileFormat(parts[0]);
                int cnt = Integer.parseInt(parts[1]);
                report.incrementUserCount(user, cnt);
            }

            return report;
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
            return new Report(0, 0.0);
        }
    }
}
