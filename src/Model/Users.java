package Model;

import java.io.*;
import java.util.ArrayList;

public class Users {
    ArrayList<User> users = new ArrayList<>();


    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean deleteUser(String username) {
        return users.removeIf(user -> user.getUserName().equals(username));
    }

    public boolean modifyUser(String username, User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(username)) {
                users.set(i, updatedUser);
                return true;
            }
        }
        return false;
    }


    public User findUser(String username){
        for (User user : users)
        if (username.equals(user.getUserName()))
            return user;


        return null;
    }
   public boolean isCorrectPassword(User user , String password){
        if (user.getPassword().equals(password))
            return true;

            return false;
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
        try (BufferedReader br = new BufferedReader(new FileReader("Model.Users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                User user = User.fromFileFormat(line);
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Model.Users.txt"))) {
            for (User user : users) {
                bw.write(user.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
