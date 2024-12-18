import View.LoginAndRegistrationFrame;
import View.MainFrame;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Main {
    File file = new File("");

    public static void main(String[] args) {

        SwingUtilities.invokeLater(MainFrame :: new);
        LoginAndRegistrationFrame loginAndRegistrationFrame = new LoginAndRegistrationFrame();
        loginAndRegistrationFrame.setVisible(true);

    }
}