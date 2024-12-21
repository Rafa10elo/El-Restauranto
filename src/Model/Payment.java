package Model;

import java.io.*;
import java.util.ArrayList;

public class Payment {
    private static int counter = 0;
    private String paymentId;
    private double amount;
    private String method;
    private String status;

    public Payment( double amount, String method, String status) {
        this.paymentId = generatePaymentId();
        this.amount = amount;
        this.method = method;
        this.status = status;
    }

    public Payment(String paymentId, double amount, String method, String status) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }

    private String generatePaymentId() {
        counter++;
        saveCounterToFile();
        return String.format("%08d", counter);
    }

    public String getPaymentId() {
        return paymentId;
    }


    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toFileFormat() {
        return paymentId + "***" + amount + "***" + method + "***" + status;
    }

    public static Payment fromFileFormat(String str) {
        try {
            String[] parts = str.split("\\*\\*\\*");
            return new Payment(parts[0],  Double.parseDouble(parts[1]),parts[2], parts[3]);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public void writerThread(){

        new Thread(()->{
            saveCounterToFile();
        });

    }
    public void readerThread(){

        new Thread(()->{
            loadCounterFromFile();
        });

    }

    private static void saveCounterToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("CounterOfIDS.txt"))) {
            writer.write(String.valueOf(counter));
        } catch (IOException e) {
            System.out.println( e);
        }
    }

    public static void loadCounterFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("CounterOfIDS.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                counter = Integer.parseInt(line);
            }
        } catch (IOException e) {
            System.out.println( e);
            counter = 0;
        }
    }
}
