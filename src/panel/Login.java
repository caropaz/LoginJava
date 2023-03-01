package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import connection.Database;
import model.User;

public class Login extends  JDialog{
    private JPanel login;
    private JTextField tfName;
    private JTextField tfEmail;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPasswordField tfPassword;
    private JPasswordField tFConfirmPass;

    private Database db;
    public User user;

    public  Login(JFrame frame){
        super(frame);
        db = new Database();
        setTitle("Register User");
        setContentPane(login);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(frame);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void registerUser() {
        String name =  tfName.getText(), email = tfEmail.getText(), pass = tfPassword.getText(), passConfirm = tFConfirmPass.getText();

        if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || passConfirm.isEmpty()){
            JOptionPane.showMessageDialog( this, "Please, enter all fields", "Try Again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!pass.equals(passConfirm)){
            JOptionPane.showMessageDialog( this, "Confirm password doest not match", "Try Again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        user= db.addUserToDatabase(name, email, pass);
        if (user != null){
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Failed to register new user", "Try Again", JOptionPane.ERROR_MESSAGE);
        }
    }

}
