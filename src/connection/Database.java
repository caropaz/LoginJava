package connection;

import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Database {

    public User addUserToDatabase(String name, String email, String password){

        User user = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/registro_login";
        final String USERNAME = "root";
        final String PASSWORD = "root.";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            // connected to database succesfully

            Statement stnt = conn.createStatement();
            String sql = "INSERT INTO users (name, email, password)" + "VALUE (?,?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);

            // Insert row into the table
            int addedRows = preparedStatement.executeUpdate();
            if(addedRows>0){
                user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
            }

            // close connection
            stnt.close();
            conn.close();;

        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
