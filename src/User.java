import java.util.ArrayList;

public class User {
    private String userName;
    private String password;
    private boolean isEmployee;
    ArrayList<Order> orders;
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    User fromFileFormat(String str){
        String[] userStrings= str.split("***");


    }
}
