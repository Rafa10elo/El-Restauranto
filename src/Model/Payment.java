package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class Payment {
    private static int counter = 0;
    private String paymentId;
    private double amount;
    private String method;

    public Payment( double amount, String method ) {
        this.paymentId = generatePaymentId();
        this.amount = amount;
        this.method = method;
    }

    public Payment(String paymentId, double amount, String method ) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.method = method;
    }

    private String generatePaymentId() {
        counter++;
        saveCounterToFile();
        return String.format(Locale.ENGLISH,"%08d", counter);
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

    public String toFileFormat() {
        return paymentId + "***" + amount + "***" + method;
    }

    public static Payment fromFileFormat(String str) {
        try {
            String[] parts = str.split("\\*\\*\\*");
            return new Payment(parts[0],  Double.parseDouble(parts[1]),parts[2]);
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

    public static void saveCounterToFile() {
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
