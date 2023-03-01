import panel.Login;
import model.User;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Login login = new Login(null);
        User user= login.user;
        if (user != null){
            System.out.println("Succesful registration of: " + user.getName());

        } else {
            System.out.println("Registration canceled");
        }
    }
}