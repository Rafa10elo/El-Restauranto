package Model;

import java.io.*;
import java.util.ArrayList;

public class Payments {
    private ArrayList<Payment> payments = new ArrayList<>();

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public Payment findPaymentById(String paymentId) {
        for (Payment payment : payments) {
            if (payment.getPaymentId().equals(paymentId)) {
                return payment;
            }
        }
        return null;
    }

    public void writerThread(){

        new Thread(()->{
            saveToFile();
        });

    }
    public void readerThread(){

        new Thread(()->{
            loadFromFile();
        });

    }

    public void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("Payments.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Payment payment = Payment.fromFileFormat(line);
                if (payment != null) {
                    payments.add(payment);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Payments.txt"))) {
            for (Payment payment : payments) {
                bw.write(payment.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean checkTheCreditCard(String cardNumber, double amount) {
        if (cardNumber.matches("\\d{16}")) {
            return true;
        }
        return false;
    }
}